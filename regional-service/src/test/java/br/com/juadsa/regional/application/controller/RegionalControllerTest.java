package br.com.juadsa.regional.application.controller;

import br.com.juadsa.regional.application.dto.NovaRegionalDTO;
import br.com.juadsa.regional.application.dto.RegionalDTO;
import br.com.juadsa.regional.application.interfaces.datasource.IRegionalDataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegionalControllerTest {

    @DisplayName("Criar Regional")
    @Test
    void create() {
        String id = "1";
        String nome = "Regional 3";
        String sigla = "R3";
        String descrica = "Regional 3 - Composta pelos campos: Capão, Embu, Independencia, PSA, Taboão";
        Integer ordem = 1;

        IRegionalDataSource regionalDataSource = mock(IRegionalDataSource.class);

        when(regionalDataSource.incluir(any())).thenReturn(
                new RegionalDTO(id, nome, sigla, descrica, ordem)
        );

        NovaRegionalDTO novaRegionalDTO = new NovaRegionalDTO(nome, sigla, descrica, ordem);

        RegionalController regionalController = RegionalController.create(regionalDataSource);

        RegionalDTO regional = regionalController.incluir(novaRegionalDTO);

        assertNotNull(regional);

        assertEquals(nome, regional.nome());
        assertEquals(sigla, regional.sigla());
        assertEquals(descrica, regional.descricao());
        assertEquals(ordem, regional.ordem());

    }

    @Test
    void incluir() {
    }

    @Test
    void remover() {
    }

    @Test
    void atualizar() {
    }

    @Test
    void buscarRegionalPorSigla() {
    }
}