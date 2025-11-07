package br.com.juadsa.regional.application.domain.usecases.regional;

import br.com.juadsa.regional.application.domain.entities.Regional;
import br.com.juadsa.regional.application.domain.usecases.regional.handler.RegionalIdExistenteHandler;
import br.com.juadsa.regional.application.dto.RegionalDTO;
import br.com.juadsa.regional.application.dto.RegionalInputHandler;
import br.com.juadsa.regional.application.interfaces.gateway.IRegionalGateway;

public class AtualizarRegionalUseCase {

    private IRegionalGateway regionalGateway;

    private AtualizarRegionalUseCase(IRegionalGateway regionalGateway) {
        this.regionalGateway = regionalGateway;
    }

    public static AtualizarRegionalUseCase create(IRegionalGateway regionalGateway) {
        return new AtualizarRegionalUseCase(regionalGateway);
    }

    public Regional run(RegionalDTO regionalDTO) {
        Regional regional = Regional.create(regionalDTO.id(), regionalDTO.nome(), regionalDTO.sigla(), regionalDTO.descricao(), regionalDTO.ordem());

        validaRegras(regional);

        return regionalGateway.alterar(regional);
    }


    private Boolean validaRegras(Regional regional) {
        RegionalInputHandler regionalInputHandler = new RegionalInputHandler(regional.getId(), regional);

        var regionalExistenteHandler = new RegionalIdExistenteHandler(regionalGateway);

        return regionalExistenteHandler.handler(regionalInputHandler);
    }
}
