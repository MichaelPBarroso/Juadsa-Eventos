package br.com.juadsa.regional.application.domain.usecases.regional;

import br.com.juadsa.regional.application.domain.entities.Regional;
import br.com.juadsa.regional.application.dto.NovaRegionalDTO;
import br.com.juadsa.regional.application.exceptions.regional.RegionalNomeExistenteException;
import br.com.juadsa.regional.application.exceptions.regional.RegionalSiglaExistenteException;
import br.com.juadsa.regional.application.interfaces.gateway.IRegionalGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CriarRegionalUseCaseTest {

    @DisplayName("Cadastro regional com sucesso")
    @Test
    void criarRegional() {
        String nome = "Regional 3";
        String sigla = "R3";
        String descrica = "Regional 3 - Composta pelos campos: Capão, Embu, Independencia, PSA, Taboão";
        Integer ordem = 1;

        IRegionalGateway regionalGateway = mock(IRegionalGateway.class);

        when(regionalGateway.buscarRegionalPorSigla(anyString())).thenReturn(null);
        when(regionalGateway.incluir(any())).thenReturn(
                Regional.create(nome, sigla, descrica, ordem)
        );

        CriarRegionalUseCase criarRegionalUseCase = CriarRegionalUseCase.create(regionalGateway);

        Regional regional = criarRegionalUseCase.run(new NovaRegionalDTO(nome, sigla, descrica, ordem));

        assertNotNull(regional);

        assertEquals(nome, regional.getNome());
        assertEquals(sigla, regional.getSigla());
        assertEquals(descrica, regional.getDescricao());
        assertEquals(ordem, regional.getOrdem());
    }


    @DisplayName("Cadastro regional sigla ja existente")
    @Test
    void criarRegionalSiglaExistente() {
        String nome = "Regional 3";
        String sigla = "R3";
        String descrica = "Regional 3 - Composta pelos campos: Capão, Embu, Independencia, PSA, Taboão";
        Integer ordem = 1;

        String nomeExistente = "Regional 2";

        IRegionalGateway regionalGateway = mock(IRegionalGateway.class);

        when(regionalGateway.buscarRegionalPorSigla(anyString())).thenReturn(
                Optional.of(Regional.create(nomeExistente, sigla, descrica, ordem))
        );

        when(regionalGateway.incluir(any())).thenReturn(
                Regional.create(nome, sigla, descrica, ordem)
        );

        CriarRegionalUseCase criarRegionalUseCase = CriarRegionalUseCase.create(regionalGateway);

        RegionalSiglaExistenteException regionalExistenteException = assertThrows(RegionalSiglaExistenteException.class, () -> {
            criarRegionalUseCase.run(new NovaRegionalDTO(nome, sigla, descrica, ordem));
        });

        assertEquals("Regional com a sigla já existe", regionalExistenteException.getMessage());

    }

    @DisplayName("Cadastro regional Nome ja existente")
    @Test
    void criarRegionalNomeExistente() {
        String nome = "Regional 3";
        String sigla = "R3";
        String descrica = "Regional 3 - Composta pelos campos: Capão, Embu, Independencia, PSA, Taboão";
        Integer ordem = 1;

        String siglaExistente = "R2";

        IRegionalGateway regionalGateway = mock(IRegionalGateway.class);

        when(regionalGateway.buscarRegionalPorNome(anyString())).thenReturn(
                Optional.of(Regional.create(nome, siglaExistente, descrica, ordem))
        );

        when(regionalGateway.incluir(any())).thenReturn(
                Regional.create(nome, sigla, descrica, ordem)
        );

        CriarRegionalUseCase criarRegionalUseCase = CriarRegionalUseCase.create(regionalGateway);

        RegionalNomeExistenteException regionalExistenteException = assertThrows(RegionalNomeExistenteException.class, () -> {
            criarRegionalUseCase.run(new NovaRegionalDTO(nome, sigla, descrica, ordem));
        });

        assertEquals("Regional com o nome já existe", regionalExistenteException.getMessage());

    }


}