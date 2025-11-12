package br.com.juadsa.louvor.application.domain.usecases.louvor.handler;

import br.com.juadsa.louvor.application.domain.entities.Louvor;
import br.com.juadsa.louvor.application.dto.LouvorInputHandler;
import br.com.juadsa.louvor.application.exceptions.louvor.LouvorNaoExistenteException;
import br.com.juadsa.louvor.application.interfaces.gateway.ILouvorGateway;

import java.util.Optional;

public class LouvorIdExistenteHandler extends LouvorHandler{

    private final ILouvorGateway louvorGateway;

    public LouvorIdExistenteHandler(ILouvorGateway louvorGateway) {
        this.louvorGateway = louvorGateway;
    }

    @Override
    public Boolean handler(LouvorInputHandler louvorInputHandler) {

        Optional<Louvor> louvor = this.louvorGateway.buscarLouvorPorId(louvorInputHandler.id());

        if(louvor == null || louvor.isEmpty()) {
            throw new LouvorNaoExistenteException();
        }

        if(next != null) {
            return next.handler(louvorInputHandler);
        }

        return true;
    }

}
