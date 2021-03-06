package cn.xsshome.mvcdo.healthai.exception;

/**
 * 鉴权异常
 *
 * @author yangsongbo
 */
public class AuthException extends RuntimeException {

    public AuthException() {
        super();
    }


    public AuthException(String message) {
        super(message);
    }


    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }


    public AuthException(Throwable cause) {
        super(cause);
    }

}
