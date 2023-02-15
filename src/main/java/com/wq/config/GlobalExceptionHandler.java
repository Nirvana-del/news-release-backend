package com.wq.config;

import com.wq.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    //打印log
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    //.......MissingServletRequestParameterException ex
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result<String> handleHttpMessageNotReadableException(){
        return Result.error("token不存在或已过期 ，请重新登陆");
    }
}
