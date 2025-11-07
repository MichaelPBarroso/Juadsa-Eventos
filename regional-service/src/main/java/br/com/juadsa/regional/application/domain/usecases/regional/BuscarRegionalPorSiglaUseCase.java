package br.com.juadsa.regional.application.domain.usecases.regional;

import br.com.juadsa.regional.application.domain.entities.Regional;
import br.com.juadsa.regional.application.exceptions.regional.RegionalNaoExistenteException;
import br.com.juadsa.regional.application.interfaces.gateway.IRegionalGateway;

public class BuscarRegionalPorSiglaUseCase {

    private IRegionalGateway regionalGateway;

    private BuscarRegionalPorSiglaUseCase(IRegionalGateway regionalGateway) {
        this.regionalGateway = regionalGateway;
    }

    public static BuscarRegionalPorSiglaUseCase create(IRegionalGateway regionalGateway) {
        return new BuscarRegionalPorSiglaUseCase(regionalGateway);
    }

    public Regional run(String sigla) {
        return regionalGateway.buscarRegionalPorSigla(sigla).orElseThrow(RegionalNaoExistenteException::new);

    }

}
