package br.com.juadsa.regional.application.domain.usecases.regional;

import br.com.juadsa.regional.application.domain.entities.Regional;
import br.com.juadsa.regional.application.exceptions.regional.RegionalNaoExistenteException;
import br.com.juadsa.regional.application.interfaces.gateway.IRegionalGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeletarRegionalUseCaseTest {

    @DisplayName("Remover regional com sucesso")
    @Test
    void deletarRegional(){
        String id = "1";
        String nome = "Regional 3";
        String sigla = "R3";
        String descrica = "Regional 3 - Composta pelos campos: Capão, Embu, Independencia, PSA, Taboão";
        Integer ordem = 1;

        IRegionalGateway regionalGateway = mock(IRegionalGateway.class);

        when(regionalGateway.buscarRegionalPorId(anyString())).thenReturn(Optional.of(Regional.create(id, nome, sigla, descrica, ordem)));

        DeletarRegionalUseCase deletarRegionalUseCase = DeletarRegionalUseCase.create(regionalGateway);

        assertDoesNotThrow(() -> {
            deletarRegionalUseCase.run(id);
        });
    }


    @DisplayName("Remover regional nao existente")
    @Test
    void deletarRegionalNaoExistente(){
        String id = "1";

        IRegionalGateway regionalGateway = mock(IRegionalGateway.class);

        when(regionalGateway.buscarRegionalPorId(anyString())).thenReturn(null);

        DeletarRegionalUseCase deletarRegionalUseCase = DeletarRegionalUseCase.create(regionalGateway);

        RegionalNaoExistenteException regionalNaoExistenteException = assertThrows(RegionalNaoExistenteException.class, () -> {
            deletarRegionalUseCase.run(id);
        });

        assertEquals(regionalNaoExistenteException.getMessage(), "Regional não existe");
    }
}