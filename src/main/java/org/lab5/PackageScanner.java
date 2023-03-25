package org.lab5;

import org.lab5.annotations.CommandInfo;
import org.lab5.annotations.CustomConfig;
import org.lab5.annotations.DataModel;
import org.lab5.annotations.FromEnviroment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

import static org.lab5.scheme.Schema.modelSchemeGenerator;

public class PackageScanner {
    public static Set<Class<?>> commands;
    static String packageName;
    static Set<Class<?>> classes;
    static Set<Class<?>> models;
    static Set<Class<?>> configs;

    public void scanPackage(String packageName) {
        PackageScanner.packageName = packageName;
        InputStream stream = getClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
        PackageScanner.classes = getPackageClasses(packageName);
        PackageScanner.models = filterByAnnotation(classes, DataModel.class);
        PackageScanner.configs = filterByAnnotation(classes, CustomConfig.class);
        PackageScanner.commands = filterByAnnotation(classes, CommandInfo.class);
        setFromEnvironment(configs);
        modelSchemeGenerator(models);
        //commandManager.registerCommands((Set<Class<? extends Command>>) localCommands);
    }

    private ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }


    /**
     * Scans the given package and returns a set of all public classes in that package.
     *
     * @param packageName The name of the package to scan.
     * @return A set of all public classes in the given package.
     * @throws RuntimeException If an error occurs while scanning the package.
     */
    private Set<Class<?>> getPackageClasses(String packageName) {
        String path = packageName.replaceAll("[.]", "/");
        try {
            List<String> classNames = new ArrayList<>();
            Enumeration<URL> resources = getClassLoader().getResources(path);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                String file = resource.getFile();
                if (resource.getProtocol().equals("jar")) {
                    JarURLConnection connection = (JarURLConnection) resource.openConnection();
                    JarFile jarFile = connection.getJarFile();
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        String name = entries.nextElement().getName();
                        if (name.startsWith(path) && name.endsWith(".class")) {
                            classNames.add(name.replace("/", ".").replace(".class", ""));
                        }
                    }
                } else if (resource.getProtocol().equals("file")) {
                    File folder = new File(file);
                    File[] files = folder.listFiles();
                    assert files != null;
                    for (File f : files) {
                        if (f.isFile() && f.getName().endsWith(".class")) {
                            classNames.add(packageName + "." + f.getName().replace(".class", ""));
                        } else if (f.isDirectory()) {
                            String subPackageName = packageName + "." + f.getName();
                            classNames.addAll(getPackageClasses(subPackageName).stream().filter(clazz -> Modifier.isPublic(clazz.getModifiers())) // Filter only public classes
                                    .map(Class::getName)
                                    .toList());
                        }
                    }
                }
            }
            return classNames.stream()
                    .map(className -> {
                        try {
                            return Class.forName(className);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException("Failed to load class: " + className, e);
                        }
                    })
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException("Failed to scan package: " + packageName, e);
        }
    }

    /**
     * Filters the set of classes by the presence of a given annotation.
     *
     * @param classes         the set of classes to filter
     * @param annotationClass the class object representing the annotation to search for
     * @return a filtered set of classes that have the given annotation
     */
    private <T extends Annotation> Set<Class<?>> filterByAnnotation(Set<Class<?>> classes, Class<T> annotationClass) {
        return classes.stream()
                .filter(c -> c.isAnnotationPresent(annotationClass))
                .collect(Collectors.toSet());
    }

    public void setFromEnvironment(Set<Class<?>> configs) {
        for (var config : configs) {
            for (var field : config.getDeclaredFields()) {
                if (field.isAnnotationPresent(FromEnviroment.class)) {
                    String key = field.getAnnotation(FromEnviroment.class).key();
                    String value = System.getenv(key);
                    setStaticField(config, field.getName(), value);
                }
            }
        }
    }


    private void setStaticField(Class<?> clazz, String fieldName, String value) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(null, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set field value: " + fieldName, e);
        }
    }

}
