package br.com.juadsa.louvor.application.presenters;

import br.com.juadsa.louvor.application.domain.entities.Louvor;
import br.com.juadsa.louvor.application.dto.LouvorDTO;
import br.com.juadsa.louvor.application.dto.NovoLouvorDTO;

public class LouvorPresenter {

    public static LouvorDTO toDTO(Louvor louvor){
        return new LouvorDTO(louvor.getId(), louvor.getNome(), louvor.getCantor(), louvor.getIdRegional(), louvor.getLetra());
    }

    public static NovoLouvorDTO toNovoDTO(Louvor louvor){
        return new NovoLouvorDTO(louvor.getNome(), louvor.getCantor(),  louvor.getIdRegional(), louvor.getLetra());
    }

    public static Louvor toDomain(LouvorDTO louvorDTO){
        return Louvor.create(louvorDTO.id(), louvorDTO.nome(), louvorDTO.cantor(),  louvorDTO.idRegional(), louvorDTO.letra());
    }
}
