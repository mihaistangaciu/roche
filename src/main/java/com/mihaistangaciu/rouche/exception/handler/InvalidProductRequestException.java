package com.mihaistangaciu.rouche.exception.handler;

public class InvalidProductRequestException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public InvalidProductRequestException() {
        super();
    }

    public InvalidProductRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidProductRequestException(final String message) {
        super(message);
    }

    public InvalidProductRequestException(final Throwable cause) {
        super(cause);
    }
}
