package com.ace.trade.common.exception;

/**
 * Created by carl.yu on 2017/2/21.
 */
public class AceOrderException extends RuntimeException {
    public AceOrderException() {
        super();
    }

    public AceOrderException(String message) {
        super(message);
    }

    public AceOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public AceOrderException(Throwable cause) {
        super(cause);
    }
}
