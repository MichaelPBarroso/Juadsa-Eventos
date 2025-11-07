package br.com.juadsa.regional.application.domain.usecases.regional.handler;

import br.com.juadsa.regional.application.dto.RegionalInputHandler;

public abstract class RegionalHandler {

    protected RegionalHandler next;

    public RegionalHandler setNext(final RegionalHandler next) {
        this.next = next;
        return next;
    }

    public abstract Boolean handler(RegionalInputHandler regionalInputHandler);
}
