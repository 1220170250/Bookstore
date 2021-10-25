package com.qinkai.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 专门处理异常的类
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    public ModelAndView handleException(Exception exception){

        ModelAndView m =new ModelAndView("error/error500");
        m.addObject("ex",exception);
        return m;
    }
}
