package org.lab5.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ModelField {
    String name() default "";

    boolean auto() default false;

    String prompt() default "";
}
