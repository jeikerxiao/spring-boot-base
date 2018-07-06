package com.sinocare.base.core.enumeration;

/**
 * Description: 字典枚举
 * Created by jeikerxiao on 2018/7/6 上午10:56
 */
public enum DictionaryField {

    DATA_DICTIONARY_ID(String.class, "dataDictionaryId"),
    DATA_DICTIONARY_NAME(String.class, "dataDictionaryName"),
    CODE(String.class, "code"),
    DESCRIPTION(String.class, "description"),
    DATA_DICTIONARY_TYPE(String.class, "dataDictionaryType"),
    IS_SYSTEM(Integer.class, "isSystem"),
    PARENT_ID(String.class, "parentId");

    private Class<?> type;
    private String value;

    public Class<?> getType() {
        return this.type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private DictionaryField(Class<?> type, String value) {
        this.type = type;
        this.value = value;
    }
}
