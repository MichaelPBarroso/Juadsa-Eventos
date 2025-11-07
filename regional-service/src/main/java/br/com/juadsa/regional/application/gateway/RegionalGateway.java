package br.com.juadsa.regional.application.gateway;

import br.com.juadsa.regional.application.domain.entities.Regional;
import br.com.juadsa.regional.application.dto.NovaRegionalDTO;
import br.com.juadsa.regional.application.dto.RegionalDTO;
import br.com.juadsa.regional.application.interfaces.datasource.IRegionalDataSource;
import br.com.juadsa.regional.application.interfaces.gateway.IRegionalGateway;
import br.com.juadsa.regional.application.presenters.RegionalPresenter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class RegionalGateway implements IRegionalGateway {

    private final IRegionalDataSource regionalDataSource;

    private RegionalGateway (IRegionalDataSource regionalDataSource) {
        this.regionalDataSource = regionalDataSource;
    }

    public static IRegionalGateway create(IRegionalDataSource regionalDataSource) {
        return new RegionalGateway(regionalDataSource);
    }

    @Override
    public Optional<Regional> buscarRegionalPorId(String id) {
        RegionalDTO regionalDTO = this.regionalDataSource.buscarRegionalPorId(id);

        if(regionalDTO == null) {
            return Optional.empty();
        }

        return Optional.of(RegionalPresenter.toEntity(regionalDTO));
    }

    @Override
    public Optional<Regional> buscarRegionalPorSigla(String sigla) {
        RegionalDTO regionalDTO = this.regionalDataSource.buscarRegionalPorSigla(sigla);

        if(regionalDTO == null) {
            return Optional.empty();
        }

        return Optional.of(RegionalPresenter.toEntity(regionalDTO));
    }

    @Override
    public Optional<Regional> buscarRegionalPorNome(String nome) {

        RegionalDTO regionalDTO = this.regionalDataSource.buscarRegionalPorNome(nome);

        if(regionalDTO == null) {
            return Optional.empty();
        }

        return Optional.of(RegionalPresenter.toEntity(regionalDTO));
    }

    @Override
    public List<Regional> buscarTodasRegionais() {
        List<RegionalDTO> regionalDTOS = this.regionalDataSource.buscarTodasRegionais();

        Stream<Regional> regionalStream = regionalDTOS.stream().map(RegionalPresenter::toEntity);

        return regionalStream.toList();
    }

    @Override
    public Regional incluir(Regional novaRegional) {
        NovaRegionalDTO novaRegionalDTO = RegionalPresenter.toNovaDTO(novaRegional);

        return RegionalPresenter.toEntity(this.regionalDataSource.incluir(novaRegionalDTO));
    }

    @Override
    public Regional alterar(Regional regional) {
        RegionalDTO regionalDTO = RegionalPresenter.toDTO(regional);

        return RegionalPresenter.toEntity(this.regionalDataSource.alterar(regional.getId(), regionalDTO));
    }

    @Override
    public void remover(String id) {
        this.regionalDataSource.remover(id);
    }
}
