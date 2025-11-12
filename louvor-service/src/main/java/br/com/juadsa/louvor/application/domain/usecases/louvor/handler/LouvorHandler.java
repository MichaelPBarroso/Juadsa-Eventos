package br.com.juadsa.louvor.application.domain.usecases.louvor.handler;

import br.com.juadsa.louvor.application.dto.LouvorInputHandler;

public abstract class LouvorHandler {

    protected LouvorHandler next;

    public LouvorHandler setNext(final LouvorHandler next) {
        this.next = next;
        return next;
    }

    public abstract Boolean handler(LouvorInputHandler louvorInputHandler);
}
