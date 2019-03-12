package com.bootstrap.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface Autowired {
    String value() default "";
}
