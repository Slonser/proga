package org.lab5.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
    String mnemonic();

    String message() default "";

    String description();

    String[] args() default {};
}
