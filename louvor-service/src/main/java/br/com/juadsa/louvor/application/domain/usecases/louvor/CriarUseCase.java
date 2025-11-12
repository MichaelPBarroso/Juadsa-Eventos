package br.com.juadsa.louvor.application.domain.usecases.louvor;

import br.com.juadsa.louvor.application.domain.entities.Louvor;
import br.com.juadsa.louvor.application.domain.usecases.louvor.handler.LouvorNaoExistenteHandler;
import br.com.juadsa.louvor.application.dto.LouvorInputHandler;
import br.com.juadsa.louvor.application.dto.NovoLouvorDTO;
import br.com.juadsa.louvor.application.interfaces.gateway.ILouvorGateway;

public class CriarUseCase {

    private ILouvorGateway louvorGateway;

    private CriarUseCase(ILouvorGateway louvorGateway){
        this.louvorGateway = louvorGateway;
    }

    public static CriarUseCase create(ILouvorGateway louvorGateway){
        return new CriarUseCase(louvorGateway);
    }

    public Louvor run(NovoLouvorDTO novoLouvorDTO){
        Louvor louvor = Louvor.create(novoLouvorDTO.nome(), novoLouvorDTO.cantor(), novoLouvorDTO.idRegional(), novoLouvorDTO.letra());

        validarRegras(louvor);

        return louvorGateway.incluir(louvor);
    }

    private Boolean validarRegras(Louvor louvor){
        LouvorInputHandler louvorInputHandler = new LouvorInputHandler(null, louvor);

        var louvorNaoExistenteHandler = new LouvorNaoExistenteHandler(louvorGateway);

        return louvorNaoExistenteHandler.handler(louvorInputHandler);
    }

}
