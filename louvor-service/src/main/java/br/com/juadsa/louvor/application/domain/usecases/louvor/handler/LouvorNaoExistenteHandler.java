package br.com.juadsa.louvor.application.domain.usecases.louvor.handler;

import br.com.juadsa.louvor.application.domain.entities.Louvor;
import br.com.juadsa.louvor.application.dto.LouvorInputHandler;
import br.com.juadsa.louvor.application.exceptions.louvor.LouvorExistenteNaRegionalException;
import br.com.juadsa.louvor.application.interfaces.gateway.ILouvorGateway;

import java.util.Optional;

public class LouvorNaoExistenteHandler extends LouvorHandler {

    private final ILouvorGateway louvorGateway;

    public LouvorNaoExistenteHandler(ILouvorGateway louvorGateway) {
        this.louvorGateway = louvorGateway;
    }

    @Override
    public Boolean handler(LouvorInputHandler louvorInputHandler) {

        Optional<Louvor> louvorExistente = this.louvorGateway.buscarLouvorRegional(louvorInputHandler.louvor().getNome(), louvorInputHandler.louvor().getIdRegional());

        if(louvorExistente != null && louvorExistente.isPresent()){
            throw new LouvorExistenteNaRegionalException();
        }

        if(next != null){
            return next.handler(louvorInputHandler);
        }

        return true;
    }
}
