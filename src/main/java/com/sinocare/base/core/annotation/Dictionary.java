package com.sinocare.base.core.annotation;

import com.sinocare.base.core.enumeration.DictionaryField;

import java.lang.annotation.*;

/**
 * Description: 字典注解
 * Created by jeikerxiao on 2018/7/6 上午10:54
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dictionary {

    String code() default "";

    String targetField() default "";

    DictionaryField source() default DictionaryField.DATA_DICTIONARY_NAME;
}
