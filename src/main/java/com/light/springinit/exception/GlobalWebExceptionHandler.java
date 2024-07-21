package com.light.springinit.exception;

import com.google.common.collect.Maps;
import com.light.springinit.common.result.Result;
import com.light.springinit.exception.errorcode.PostErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

/**
 * Controller 全局异常处理
 *
 * @author null&&
 * @Date 2024/7/16 22:22
 */
@ControllerAdvice
public class GlobalWebExceptionHandler {
    /**
     * 自定义方法参数校验异常处理器
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = Maps.newHashMapWithExpectedSize(1);
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    /**
     * 自定义参数类型转换异常处理器
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        Result result = new Result();
        result.setCode(PostErrorCode.POST_QUERY_PARAM_ERROR.getCode());
        result.setMessage(PostErrorCode.POST_QUERY_PARAM_ERROR.getMessage());
        result.setSuccess(false);
        return result;
    }

    /**
     * 自定义业务异常处理器
     *
     * @param bizException
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result exceptionHandler(BizException bizException) {
        Result result = new Result();
        result.setCode(bizException.getErrorCode().getCode());
        result.setMessage(bizException.getErrorCode().getMessage());
        result.setSuccess(false);
        return result;
    }

    /**
     * 自定义系统异常处理器
     *
     * @param systemException
     * @return
     */
    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result systemExceptionHandler(SystemException systemException) {
        Result result = new Result();
        result.setCode(systemException.getErrorCode().getCode());
        result.setMessage(systemException.getErrorCode().getMessage());
        result.setSuccess(false);
        return result;
    }
}
