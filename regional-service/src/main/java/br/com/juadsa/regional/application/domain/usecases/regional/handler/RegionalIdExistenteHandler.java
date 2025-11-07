package br.com.juadsa.regional.application.domain.usecases.regional.handler;

import br.com.juadsa.regional.application.domain.entities.Regional;
import br.com.juadsa.regional.application.dto.RegionalInputHandler;
import br.com.juadsa.regional.application.exceptions.regional.RegionalNaoExistenteException;
import br.com.juadsa.regional.application.interfaces.gateway.IRegionalGateway;

import java.util.Optional;

public class RegionalIdExistenteHandler extends RegionalHandler {

    private final IRegionalGateway regionalGateway;

    public RegionalIdExistenteHandler(final IRegionalGateway regionalGateway) {
        this.regionalGateway = regionalGateway;
    }

    @Override
    public Boolean handler(RegionalInputHandler regionalInputHandler) {

        Optional<Regional> regionalExistente = this.regionalGateway.buscarRegionalPorId(regionalInputHandler.id());

        if(regionalExistente == null || regionalExistente.isEmpty()) {
            throw new RegionalNaoExistenteException();
        }

        if(next != null){
            return next.handler(regionalInputHandler);
        } else {
            return true;
        }
    }
}
