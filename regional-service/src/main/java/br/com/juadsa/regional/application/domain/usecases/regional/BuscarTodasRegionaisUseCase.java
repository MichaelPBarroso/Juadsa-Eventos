package br.com.juadsa.regional.application.domain.usecases.regional;

import br.com.juadsa.regional.application.domain.entities.Regional;
import br.com.juadsa.regional.application.interfaces.gateway.IRegionalGateway;

import java.util.List;

public class BuscarTodasRegionaisUseCase {

    private IRegionalGateway regionalGateway;

    private BuscarTodasRegionaisUseCase(IRegionalGateway regionalGateway) {
        this.regionalGateway = regionalGateway;
    }

    public static BuscarTodasRegionaisUseCase create(IRegionalGateway regionalGateway) {
        return new BuscarTodasRegionaisUseCase(regionalGateway);
    }

    public List<Regional> run() {
        return regionalGateway.buscarTodasRegionais();
    }

}
