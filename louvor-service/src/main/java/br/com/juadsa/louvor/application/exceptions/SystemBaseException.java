package br.com.juadsa.louvor.application.exceptions;

public abstract class SystemBaseException extends RuntimeException {
    private static final long serialVersionUID = -4124039907622555808L;

    public abstract String getCode();
    public abstract Integer getHttpStatus();

    @Override
    public abstract String getMessage();
}
