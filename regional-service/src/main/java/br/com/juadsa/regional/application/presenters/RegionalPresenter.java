package br.com.juadsa.regional.application.presenters;

import br.com.juadsa.regional.application.domain.entities.Regional;
import br.com.juadsa.regional.application.dto.NovaRegionalDTO;
import br.com.juadsa.regional.application.dto.RegionalDTO;

public class RegionalPresenter {

    public static RegionalDTO toDTO(Regional regional) {
        return new RegionalDTO(regional.getId(), regional.getNome(), regional.getSigla(), regional.getDescricao(), regional.getOrdem());
    }

    public static NovaRegionalDTO toNovaDTO(Regional regional) {
        return new NovaRegionalDTO(regional.getNome(), regional.getSigla(), regional.getDescricao(), regional.getOrdem());
    }

    public static Regional toEntity(RegionalDTO regionalDTO) {
        return Regional.create(regionalDTO.id(), regionalDTO.nome(), regionalDTO.sigla(), regionalDTO.descricao(), regionalDTO.ordem());
    }
}
