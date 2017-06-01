package com.ace.trade.common.exception;

/**
 * <B>描述: </B><br/>
 * <B>作者: </B>carl.yu<br/>
 * <B>时间: </B>2017/2/20<br/>
 * <B>版本: </B>v1.0.0 <br/>
 * <B>历史: </B> (版本 作者 时间 注释) <br/>
 */
public class AceMQException extends Exception {

    public AceMQException() {
        super();
    }

    public AceMQException(String message) {
        super(message);
    }

    public AceMQException(String message, Throwable cause) {
        super(message, cause);
    }

    public AceMQException(Throwable cause) {
        super(cause);
    }

}
