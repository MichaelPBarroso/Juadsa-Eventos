package br.com.juadsa.louvor.application.gateway;

import br.com.juadsa.louvor.application.domain.entities.Louvor;
import br.com.juadsa.louvor.application.interfaces.gateway.ILouvorDataSource;
import br.com.juadsa.louvor.application.interfaces.gateway.ILouvorGateway;
import br.com.juadsa.louvor.application.presenters.LouvorPresenter;

import java.util.Optional;

public class LouvorGateway implements ILouvorGateway {

    private final ILouvorDataSource louvorDataSource;

    private LouvorGateway(ILouvorDataSource louvorDataSource) {
        this.louvorDataSource = louvorDataSource;
    }

    public static LouvorGateway create(ILouvorDataSource louvorDataSource) {
        return new LouvorGateway(louvorDataSource);
    }

    @Override
    public Optional<Louvor> buscarLouvorRegional(String nome, String idRegional) {
        return Optional.empty();
    }

    @Override
    public Optional<Louvor> buscarLouvorPorId(String id) {
        return Optional.empty();
    }

    @Override
    public Louvor incluir(Louvor louvor) {
        return LouvorPresenter.toDomain(louvorDataSource.incluir(LouvorPresenter.toNovoDTO(louvor)));
    }
}
