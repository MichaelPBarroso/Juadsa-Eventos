package br.com.juadsa.louvor.application.interfaces.gateway;

import br.com.juadsa.louvor.application.dto.LouvorDTO;
import br.com.juadsa.louvor.application.dto.NovoLouvorDTO;

public interface ILouvorDataSource {

    LouvorDTO incluir(NovoLouvorDTO novoLouvorDTO);

    LouvorDTO buscarLouvorPorRegional(String nome, String idRegional);

    LouvorDTO buscarLouvorPorNome(String nome);

    LouvorDTO buscarLouvorPorID(String id);

    LouvorDTO alterar(String id, LouvorDTO louvorDTO);

    void remover(String id);

}
