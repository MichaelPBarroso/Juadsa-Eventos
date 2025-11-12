package br.com.juadsa.louvor.application.controller;

import br.com.juadsa.louvor.application.dto.LouvorDTO;
import br.com.juadsa.louvor.application.dto.NovoLouvorDTO;
import br.com.juadsa.louvor.application.interfaces.gateway.ILouvorDataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LouvorControllerTest {

    @DisplayName("Criar louvor")
    @Test
    void incluir() {
        String id = "1";
        String nome = "Musica 1";
        String cantor = "Cantor 1";
        String idRegional = "1";
        String letra = "Letra do louvor 1";

        ILouvorDataSource louvorDataSource = mock(ILouvorDataSource.class);

        when(louvorDataSource.incluir(any())).thenReturn(
                new LouvorDTO(id, nome, cantor, idRegional, letra)
        );

        NovoLouvorDTO novoLouvorDTO = new NovoLouvorDTO(id, nome, cantor, idRegional);

        LouvorController controller = LouvorController.create(louvorDataSource);

        LouvorDTO louvor = controller.incluir(novoLouvorDTO);

        assertNotNull(louvor);

        assertEquals(id, louvor.id());
        assertEquals(nome, louvor.nome());
        assertEquals(cantor, louvor.cantor());
        assertEquals(idRegional, louvor.idRegional());
        assertEquals(letra, louvor.letra());

    }
}