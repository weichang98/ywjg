package com.hjy.common.handler;

import com.hjy.common.domin.FebsResponse;
import com.hjy.common.exception.Code.Code;
import com.hjy.common.exception.FebsException;
import com.hjy.common.exception.LimitAccessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.NotAcceptableStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wx
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler({FebsException.class,Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public FebsResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息：", e);
        String message=e instanceof FebsException?e.getMessage(): Code.C500.getDesc();
        return new FebsResponse().message(message).code(Code.C500.getCode().toString()).status(ResponseStat.ERROR.getText());

    }
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public FebsResponse handleHttpRequestMethodNotSupportedException(Exception e) {
        log.error("HTTP请求方式不被支持：", e);
        return new FebsResponse().message(Code.C405.getDesc()).code(Code.C405.getCode().toString()).status(ResponseStat.ERROR.getText());
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public FebsResponse handleNoHandlerFoundException(Exception e) {
        log.error("HTTP请求内容未找到：", e);
        return new FebsResponse().message(Code.C404.getDesc()).code(Code.C404.getCode().toString()).status(ResponseStat.ERROR.getText());
    }


//    /**
//     * 统一处理请求参数校验(实体对象传参,form data方式,request body方式)
//     *
//     * @param e BindException,MethodArgumentNotValidException,HttpMessageNotReadableException,ConstraintViolationException
//     * @return FebsResponse
//     */
//    @ExceptionHandler({BindException.class,MethodArgumentNotValidException.class,HttpMessageNotReadableException.class,ConstraintViolationException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public FebsResponse validExceptionHandler(Exception e) {
//        StringBuilder message = new StringBuilder();
//        if(e instanceof BindException|| e instanceof MethodArgumentNotValidException){
//            List<FieldError> fieldErrors = e instanceof BindException?((BindException)e).getBindingResult().getFieldErrors():((MethodArgumentNotValidException)e).getBindingResult().getFieldErrors();
//            for (FieldError error : fieldErrors) {
//                message.append(error.getField()).append(error.getDefaultMessage()).append(StringPool.COMMA);
//            }
//            message = new StringBuilder(message.substring(0, message.length() - 1));
//        }else if(e instanceof HttpMessageNotReadableException){
//            message = new StringBuilder(e.getMessage().substring(0, e.getMessage().indexOf(StringPool.COLON)));
//        }else if(e instanceof ConstraintViolationException){
//            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException)e).getConstraintViolations();
//            for (ConstraintViolation<?> violation : violations) {
//                Path path = violation.getPropertyPath();
//                String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), StringPool.DOT);
//                message.append(pathArr[1]).append(violation.getMessage()).append(StringPool.COMMA);
//            }
//            message = new StringBuilder(message.substring(0, message.length() - 1));
//        }else{
//            message.append(e.getMessage());
//        }
//
//        return new FebsResponse().message(message.toString()).code(Code.C400.getCode().toString()).status(ResponseStat.ERROR.getText());
//
//    }
//
    @ExceptionHandler(value = LimitAccessException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public FebsResponse handleLimitAccessException(LimitAccessException e) {
        log.warn(e.getMessage());
        return new FebsResponse().message(e.getMessage()).code(Code.C429.getCode().toString()).status(ResponseStat.ERROR.getText());
    }
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public FebsResponse handleHttpMediaTypeNotSupportedException(Exception e) {
        log.warn(e.getMessage());
        return new FebsResponse().message(Code.C415.getDesc()).code(Code.C415.getCode().toString()).status(ResponseStat.ERROR.getText());
    }

    @ExceptionHandler({NotAcceptableStatusException.class, HttpMediaTypeNotAcceptableException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public FebsResponse handleNotAcceptableException(Exception e) {
        log.warn(e.getMessage());
        return new FebsResponse().message(Code.C406.getDesc()).code(Code.C406.getCode().toString()).status(ResponseStat.ERROR.getText());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public FebsResponse handleUnauthorizedException(Exception e) {
        log.error("权限不足，{}", e.getMessage());
        return new FebsResponse().message(Code.C401.getDesc()).code(Code.C401.getCode().toString()).status(ResponseStat.ERROR.getText());
    }
    @ExceptionHandler(value = AuthorizationException.class)
    public Map<String, String> handleException(AuthorizationException e) {
        //e.printStackTrace();
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "400");
        //获取错误中中括号的内容
        String message = e.getMessage();
        String msg=message.substring(message.indexOf("[")+1,message.indexOf("]"));
        //判断是角色错误还是权限错误
        if (message.contains("role")) {
            result.put("msg", "对不起，您没有" + msg + "角色");
        } else if (message.contains("permission")) {
            result.put("msg", "对不起，您没有" + msg + "权限");
        } else {
            result.put("msg", "对不起，您的权限有误");
        }
        return result;
    }
}
