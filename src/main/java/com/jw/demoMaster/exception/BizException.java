package com.jw.demoMaster.exception;

/**
 * created on 2018/3/6
 *
 * @author guest
 */
public class BizException extends RuntimeException {

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
