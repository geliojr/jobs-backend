package com.ghost.perfilProfissional.services.exceptions;

public class MethodArgumentTypeMismatchException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public MethodArgumentTypeMismatchException(String msg){
        super(msg);
    }

    public MethodArgumentTypeMismatchException(String msg, Throwable cause){
        super(msg, cause);
    }
}
