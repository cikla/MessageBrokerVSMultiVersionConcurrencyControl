package com.cikla.balanceWithMVCC.exception;

public class ConcurrentException extends Exception{
    public ConcurrentException(String message) {
        super(message);
    }

}
