package br.com.juadsa.regional.application.domain.usecases.regional.handler;

import br.com.juadsa.regional.application.domain.entities.Regional;
import br.com.juadsa.regional.application.dto.RegionalInputHandler;
import br.com.juadsa.regional.application.exceptions.regional.RegionalNomeExistenteException;
import br.com.juadsa.regional.application.interfaces.gateway.IRegionalGateway;

import java.util.Optional;

public class RegionalNomeNaoExistenteHadler extends RegionalHandler{

    private final IRegionalGateway regionalGateway;

    public RegionalNomeNaoExistenteHadler(final IRegionalGateway regionalGateway) {
        this.regionalGateway = regionalGateway;
    }

    @Override
    public Boolean handler(RegionalInputHandler regionalInputHandler) {

        Optional<Regional> regionalExistente = this.regionalGateway.buscarRegionalPorNome(regionalInputHandler.regional().getNome());

        if(regionalExistente != null && regionalExistente.isPresent()) {
            throw new RegionalNomeExistenteException();
        }

        if(next != null){
            return next.handler(regionalInputHandler);
        } else {
            return true;
        }
    }
}
