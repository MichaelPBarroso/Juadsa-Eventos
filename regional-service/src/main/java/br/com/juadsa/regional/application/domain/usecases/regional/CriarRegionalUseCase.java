package br.com.juadsa.regional.application.domain.usecases.regional;

import br.com.juadsa.regional.application.domain.entities.Regional;
import br.com.juadsa.regional.application.domain.usecases.regional.handler.RegionalNomeNaoExistenteHadler;
import br.com.juadsa.regional.application.domain.usecases.regional.handler.RegionalSiglaNaoExistenteHadler;
import br.com.juadsa.regional.application.dto.NovaRegionalDTO;
import br.com.juadsa.regional.application.dto.RegionalInputHandler;
import br.com.juadsa.regional.application.interfaces.gateway.IRegionalGateway;

public class CriarRegionalUseCase {

    private IRegionalGateway regionalGateway;

    private CriarRegionalUseCase(IRegionalGateway regionalGateway) {
        this.regionalGateway = regionalGateway;
    }

    public static CriarRegionalUseCase create(IRegionalGateway regionalGateway) {
        return new CriarRegionalUseCase(regionalGateway);
    }

    public Regional run(NovaRegionalDTO novaRegionalDTO){

        Regional regional = Regional.create(novaRegionalDTO.nome(), novaRegionalDTO.sigla(), novaRegionalDTO.descricao(), novaRegionalDTO.ordem());

        validaRegras(regional);

        return regionalGateway.incluir(regional);
    }

    private Boolean validaRegras(Regional regional){
        RegionalInputHandler regionalInputHandler = new RegionalInputHandler(null, regional);

        var regionalSiglaNaoExistenteHadler = new RegionalSiglaNaoExistenteHadler(regionalGateway);
        var regionalNomeNaoExistenteHadler = new RegionalNomeNaoExistenteHadler(regionalGateway);

        regionalSiglaNaoExistenteHadler.setNext(regionalNomeNaoExistenteHadler);

        return regionalSiglaNaoExistenteHadler.handler(regionalInputHandler);
    }
}
