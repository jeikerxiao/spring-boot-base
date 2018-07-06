package com.sinocare.base.core.exception;

import com.sinocare.base.core.result.Result;
import com.sinocare.base.core.result.ResultCode;
import com.sinocare.base.core.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionHandle {

    // 业务异常
    @ExceptionHandler(ServiceException.class)
    public Result serviceException(ServiceException ex) {
        return ResultUtil.failure(ex.getCode(), ex.getMessage());
    }

    //运行时异常 1000
    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(RuntimeException ex) {
        ex.printStackTrace();
        return ResultUtil.failure(ResultCode.SERVER_RUNTIME);
    }

    //空指针异常 1001
    @ExceptionHandler(NullPointerException.class)
    public Result nullPointerExceptionHandler(NullPointerException ex) {
        ex.printStackTrace();
        return ResultUtil.failure(ResultCode.SERVER_NULL_POINTER);
    }
    //类型转换异常 1002
    @ExceptionHandler(ClassCastException.class)
    public Result classCastExceptionHandler(ClassCastException ex) {
        ex.printStackTrace();
        return ResultUtil.failure(ResultCode.SERVER_CLASS_CAST);
    }

    //IO异常 1003
    @ExceptionHandler(IOException.class)
    public Result iOExceptionHandler(IOException ex) {
        ex.printStackTrace();
        return ResultUtil.failure(ResultCode.SERVER_IO);
    }
    //未知方法异常 1004
    @ExceptionHandler(NoSuchMethodException.class)
    public Result noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        ex.printStackTrace();
        return ResultUtil.failure(ResultCode.SERVER_NO_SUCH_METHOD);
    }

    //数组越界异常 1005
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public Result indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        ex.printStackTrace();
        return ResultUtil.failure(ResultCode.SERVER_INDEX_OUT_OF_BOUNDS);
    }

    // 参数错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Result requestNotReadable(HttpMessageNotReadableException ex){
        ex.printStackTrace();
        return ResultUtil.failure(ResultCode.WEB_PARAMETER_ERROR);
    }

    // 无操作权限
    @ExceptionHandler({UnauthorizedException.class})
    public Result unauthorizedException(UnauthorizedException ex){
        ex.printStackTrace();
        return ResultUtil.failure(ResultCode.TOKEN_NO_PERMISSION);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleIllegalParamException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String tips = "参数不合法";
        if (errors.size() > 0) {
            tips = errors.get(0).getDefaultMessage();
        }
        Result result = ResultUtil.failure(ResultCode.WEB_PARAMETER_ERROR);
        result.setMessage(tips);
        return result;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return ResultUtil.failure(ResultCode.WEB_NOT_SUPPORTED);
    }

    @ExceptionHandler(value = Exception.class)
    public Result handler(Exception e) {
        if (e instanceof NoHandlerFoundException) {
            return ResultUtil.failure(ResultCode.WEB_NOT_FOUND);
        } else {
            log.info("[系统异常] {}", e);
            return ResultUtil.failure(ResultCode.HTTP_SERVER_ERROR);
        }
    }
}
