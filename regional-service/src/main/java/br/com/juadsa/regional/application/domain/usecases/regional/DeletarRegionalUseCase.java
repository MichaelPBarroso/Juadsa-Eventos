package br.com.juadsa.regional.application.domain.usecases.regional;

import br.com.juadsa.regional.application.domain.usecases.regional.handler.RegionalIdExistenteHandler;
import br.com.juadsa.regional.application.dto.RegionalInputHandler;
import br.com.juadsa.regional.application.interfaces.gateway.IRegionalGateway;

public class DeletarRegionalUseCase {

    private IRegionalGateway regionalGateway;

    private DeletarRegionalUseCase(IRegionalGateway regionalGateway) {
        this.regionalGateway = regionalGateway;
    }

    public static DeletarRegionalUseCase create(IRegionalGateway regionalGateway) {
        return new DeletarRegionalUseCase(regionalGateway);
    }

    public void run(String id){

        validaRegras(id);

        regionalGateway.remover(id);

    }

    private Boolean validaRegras(String id){
        RegionalInputHandler regionalInputHandler = new RegionalInputHandler(id, null);

        var regionalExistenteHandler = new RegionalIdExistenteHandler(regionalGateway);

        return regionalExistenteHandler.handler(regionalInputHandler);
    }

}
