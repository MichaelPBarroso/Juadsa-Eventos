package br.com.juadsa.regional.application.controller;

import br.com.juadsa.regional.application.domain.entities.Regional;
import br.com.juadsa.regional.application.domain.usecases.regional.AtualizarRegionalUseCase;
import br.com.juadsa.regional.application.domain.usecases.regional.BuscarRegionalPorSiglaUseCase;
import br.com.juadsa.regional.application.domain.usecases.regional.CriarRegionalUseCase;
import br.com.juadsa.regional.application.domain.usecases.regional.DeletarRegionalUseCase;
import br.com.juadsa.regional.application.dto.NovaRegionalDTO;
import br.com.juadsa.regional.application.dto.RegionalDTO;
import br.com.juadsa.regional.application.gateway.RegionalGateway;
import br.com.juadsa.regional.application.interfaces.datasource.IRegionalDataSource;
import br.com.juadsa.regional.application.interfaces.gateway.IRegionalGateway;
import br.com.juadsa.regional.application.presenters.RegionalPresenter;

import java.util.List;
import java.util.stream.Collectors;

public class RegionalController {

    private final IRegionalDataSource regionalDataSource;

    private RegionalController(IRegionalDataSource regionalDataSource) {
        this.regionalDataSource = regionalDataSource;
    }

    public static RegionalController create(IRegionalDataSource regionalDataSource) {
        return new RegionalController(regionalDataSource);
    }

    public RegionalDTO incluir(NovaRegionalDTO novaRegionalDTO) {
        IRegionalGateway regionalGateway = RegionalGateway.create(regionalDataSource);

        CriarRegionalUseCase criarRegionalUseCase = CriarRegionalUseCase.create(regionalGateway);
        Regional regional = criarRegionalUseCase.run(novaRegionalDTO);

        return RegionalPresenter.toDTO(regional);
    }

    public void remover(String id){
        IRegionalGateway regionalGateway = RegionalGateway.create(regionalDataSource);

        DeletarRegionalUseCase.create(regionalGateway).run(id);
    }

    public RegionalDTO atualizar(RegionalDTO regionalDTO) {
        IRegionalGateway regionalGateway = RegionalGateway.create(regionalDataSource);

        AtualizarRegionalUseCase atualizarRegionalUseCase = AtualizarRegionalUseCase.create(regionalGateway);
        Regional regional = atualizarRegionalUseCase.run(regionalDTO);

        return RegionalPresenter.toDTO(regional);
    }

    public RegionalDTO buscarRegionalPorSigla(String sigla) {
        IRegionalGateway regionalGateway = RegionalGateway.create(regionalDataSource);

        BuscarRegionalPorSiglaUseCase buscarRegionalPorSiglaUseCase = BuscarRegionalPorSiglaUseCase.create(regionalGateway);
        Regional regional = buscarRegionalPorSiglaUseCase.run(sigla);

        return RegionalPresenter.toDTO(regional);
    }

    public List<RegionalDTO> buscarRegionais() {
        IRegionalGateway regionalGateway = RegionalGateway.create(regionalDataSource);

        List<Regional> regionais = regionalGateway.buscarTodasRegionais();

        return regionais.stream()
                .map(RegionalPresenter::toDTO)
                .collect(Collectors.toList());
    }
}
