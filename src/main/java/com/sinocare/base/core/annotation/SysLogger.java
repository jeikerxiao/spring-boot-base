package com.sinocare.base.core.annotation;

import java.lang.annotation.*;

/**
 * Description: spring-boot-base
 * Created by jeikerxiao on 2018/6/28 上午11:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogger {
    String value() default "";
}
