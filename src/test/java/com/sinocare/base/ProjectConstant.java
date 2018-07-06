package com.sinocare.base;

/**
 * 项目常量
 */
public final class ProjectConstant {
    public static final String BASE_PACKAGE = "com.sinocare.base";          //项目基础包名称，根据自己公司的项目修改
    public static final String BUSINESS_PACKAGE = ".sys";                  // 业务模块名

    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".po" + BUSINESS_PACKAGE;            //po所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao" + BUSINESS_PACKAGE;          //Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service" + BUSINESS_PACKAGE;     //Service所在包
    public static final String SERVICE_IMPL_PACKAGE = BASE_PACKAGE + ".service" + ".impl" + BUSINESS_PACKAGE;   //ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".web" + BUSINESS_PACKAGE;      //Controller所在包

    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.MyMapper";//Mapper插件基础接口的完全限定名
}
