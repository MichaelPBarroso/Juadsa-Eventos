package br.com.juadsa.louvor.application.domain.usecases.louvor;

import br.com.juadsa.louvor.application.domain.entities.Louvor;
import br.com.juadsa.louvor.application.dto.NovoLouvorDTO;
import br.com.juadsa.louvor.application.exceptions.louvor.LouvorExistenteNaRegionalException;
import br.com.juadsa.louvor.application.interfaces.gateway.ILouvorGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CriarUseCaseTest {

    @DisplayName("Cadastro louvor com sucesso")
    @Test
    void criarLouvor(){
        String nome = "Musica 1";
        String cantor = "Cantor 1";
        String idRegional = "1";
        String letra = "Letra do louvor 1";

        ILouvorGateway louvorGateway = mock(ILouvorGateway.class);

        when(louvorGateway.buscarLouvorRegional(anyString(), anyString())).thenReturn(null);
        when(louvorGateway.incluir(any())).thenReturn(
                Louvor.create(nome, cantor, idRegional, letra)
        );

        CriarUseCase criarUseCase = CriarUseCase.create(louvorGateway);

        Louvor louvor = criarUseCase.run(new NovoLouvorDTO(nome, cantor, idRegional, letra));

        assertNotNull(louvor);

        assertEquals(nome, louvor.getNome());
        assertEquals(cantor, louvor.getCantor());
        assertEquals(idRegional, louvor.getIdRegional());
        assertEquals(letra, louvor.getLetra());
    }

    @DisplayName("Cadastro louvor que já existe na regional")
    @Test
    void criarLouvorExistenteRegional(){
        String nome = "Musica 1";
        String cantor = "Cantor 1";
        String idRegional = "1";
        String letra = "Letra do louvor 1";

        ILouvorGateway louvorGateway = mock(ILouvorGateway.class);

        Louvor louvorBase = Louvor.create(nome, cantor, idRegional, letra);

        when(louvorGateway.buscarLouvorRegional(anyString(), anyString())).thenReturn(
                Optional.of(louvorBase)
        );

        when(louvorGateway.incluir(any())).thenReturn(
                louvorBase
        );

        CriarUseCase criarUseCase = CriarUseCase.create(louvorGateway);

        LouvorExistenteNaRegionalException louvorExistenteNaRegionalException = assertThrows(LouvorExistenteNaRegionalException.class, () -> {
            criarUseCase.run(new NovoLouvorDTO(nome, cantor, idRegional, letra));
        });

        assertEquals("Louvor Já Está Cadastrado",  louvorExistenteNaRegionalException.getMessage());

    }

}