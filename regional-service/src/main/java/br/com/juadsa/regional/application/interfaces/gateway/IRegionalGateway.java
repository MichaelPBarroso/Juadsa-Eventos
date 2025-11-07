package br.com.juadsa.regional.application.interfaces.gateway;

import br.com.juadsa.regional.application.domain.entities.Regional;

import java.util.List;
import java.util.Optional;

public interface IRegionalGateway {

    Optional<Regional> buscarRegionalPorId(String id);

    Optional<Regional> buscarRegionalPorSigla(String sigla);

    Optional<Regional> buscarRegionalPorNome(String nome);

    List<Regional> buscarTodasRegionais();

    Regional incluir(Regional novaRegional);

    Regional alterar(Regional regional);

    void remover(String id);

}
