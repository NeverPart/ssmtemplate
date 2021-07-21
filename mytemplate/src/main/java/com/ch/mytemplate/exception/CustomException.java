package com.ch.mytemplate.exception;

/**
 * @className: CustomException
 * @Auther: ch
 * @Date: 2021/7/1 12:50
 * @Description: 自定义异常
 */
public class CustomException extends RuntimeException {
    public CustomException() {
        super();
    }
    public CustomException(String msg){
        super(msg);
    }
}
