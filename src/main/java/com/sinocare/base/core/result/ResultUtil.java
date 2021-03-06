package com.sinocare.base.core.result;

public class ResultUtil {

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> failure() {
        return failure(ResultCode.FAILURE);
    }

    public static <T> Result<T> failure(ResultCode msg) {
        Result<T> result = new Result<>();
        result.setCode(msg.getCode());
        result.setMessage(msg.getMessage());
        result.setData(null);
        return result;
    }

    public static <T> Result<T> failure(String msg) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.FAILURE.getCode());
        result.setMessage(msg);
        result.setData(null);
        return result;
    }

    public static <T> Result<T> failure(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }
}
