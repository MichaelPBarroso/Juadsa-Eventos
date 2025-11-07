package br.com.juadsa.regional.application.interfaces.datasource;

import br.com.juadsa.regional.application.dto.NovaRegionalDTO;
import br.com.juadsa.regional.application.dto.RegionalDTO;

import java.util.List;

public interface IRegionalDataSource {

    RegionalDTO incluir(NovaRegionalDTO novaRegionalDTO);

    void remover(String id);

    RegionalDTO alterar(String id, RegionalDTO regionalDTO);

    RegionalDTO buscarRegionalPorSigla(String sigla);

    RegionalDTO buscarRegionalPorNome(String nome);

    RegionalDTO buscarRegionalPorId(String id);

    List<RegionalDTO> buscarTodasRegionais();
}
