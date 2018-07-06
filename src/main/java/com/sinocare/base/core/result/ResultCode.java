package com.sinocare.base.core.result;

public enum ResultCode {

    SUCCESS(0, "成功"),
    FAILURE(1, "失败"),

    HTTP_BAD_REQUEST(400, "[HTTP]Bad Request!"),
    HTTP_NOT_AUTHORIZATION(401, "[HTTP]NotAuthorization"),
    HTTP_METHOD_NOT_ALLOWED(405, "[HTTP]Method Not Allowed"),
    HTTP_NOT_ACCEPTABLE(406, "[HTTP]Not Acceptable"),
    HTTP_SERVER_ERROR(500, "[HTTP]Internal Server Error"),

    SERVER_RUNTIME(1000, "[服务器]运行时异常"),
    SERVER_NULL_POINTER(1001, "[服务器]空值异常"),
    SERVER_CLASS_CAST(1002, "[服务器]数据类型转换异常"),
    SERVER_IO(1003, "[服务器]IO异常"),
    SERVER_NO_SUCH_METHOD(1004, "[服务器]未知方法异常"),
    SERVER_INDEX_OUT_OF_BOUNDS(1005, "[服务器]数组越界异常"),
    SERVER_NETWORK(1006, "[服务器]网络异常"),

    WEB_PARAMETER_ERROR(2000, "[WEB]参数错误"),
    WEB_NOT_FOUND(2001, "[WEB]接口不存在"),
    WEB_NOT_SUPPORTED(2002, "[WEB]请求方式不支持"),

    USER_UNREGISTERED(1010, "用户未注册"),
    USER_REGISTERED(1011, "用户已注册"),
    USER_USERNAME_PASSWORD_ERROR(1012, "用户名或密码错误"),
    USER_SUSPEND_ACCOUNT(1013, "用户帐号冻结"),
    USER_OLDPASSWORD_ERROR(1014, "用户原密码不正确"),
    USER_ROLE_ERROR(1015, "新增用户所选角色，不是本人创建"),
    USER_NEW_ROLE_ERROR(1016, "新增角色的权限，已超出你的权限范围"),

    TOKEN_GENERATOR_ERROR(1020, "Token生成失败"),
    TOKEN_INEXISTENCE(1021, "Token不存在"),
    TOKEN_INVALID(1022, "Token失效"),
    TOKEN_NO_PERMISSION(1022, "Token无操作权限"),

    ;

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
