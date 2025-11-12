package br.com.juadsa.louvor.application.interfaces.gateway;

import br.com.juadsa.louvor.application.domain.entities.Louvor;

import java.util.Optional;

public interface ILouvorGateway {

    Optional<Louvor> buscarLouvorRegional(String nome, String idRegional);

    Optional<Louvor> buscarLouvorPorId(String id);

    Louvor incluir(Louvor louvor);

}
