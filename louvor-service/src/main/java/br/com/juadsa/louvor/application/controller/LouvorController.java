package br.com.juadsa.louvor.application.controller;

import br.com.juadsa.louvor.application.domain.entities.Louvor;
import br.com.juadsa.louvor.application.domain.usecases.louvor.CriarUseCase;
import br.com.juadsa.louvor.application.dto.LouvorDTO;
import br.com.juadsa.louvor.application.dto.NovoLouvorDTO;
import br.com.juadsa.louvor.application.gateway.LouvorGateway;
import br.com.juadsa.louvor.application.interfaces.gateway.ILouvorDataSource;
import br.com.juadsa.louvor.application.interfaces.gateway.ILouvorGateway;
import br.com.juadsa.louvor.application.presenters.LouvorPresenter;

public class LouvorController {

    private final ILouvorDataSource louvorDataSource;

    private  LouvorController(ILouvorDataSource louvorDataSource) {
        this.louvorDataSource = louvorDataSource;
    }

    public static LouvorController create(ILouvorDataSource louvorDataSource) {
        return new LouvorController(louvorDataSource);
    }

    public LouvorDTO incluir(NovoLouvorDTO novoLouvorDTO) {
        ILouvorGateway louvorGateway = LouvorGateway.create(louvorDataSource);

        CriarUseCase criarUseCase = CriarUseCase.create(louvorGateway);
        Louvor louvor = criarUseCase.run(novoLouvorDTO);

        return LouvorPresenter.toDTO(louvor);
    }


}
