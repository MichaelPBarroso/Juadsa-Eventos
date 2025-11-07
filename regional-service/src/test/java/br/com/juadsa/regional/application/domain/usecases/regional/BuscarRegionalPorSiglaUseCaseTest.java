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

class BuscarRegionalPorSiglaUseCaseTest {

    @DisplayName("Busca regional por sigla com sucesso")
    @Test
    void buscaRegionalPorSigla() {
        String id = "1";
        String nome = "Regional 3";
        String sigla = "R3";
        String descrica = "Regional 3 - Composta pelos campos: Capão, Embu, Independencia, PSA, Taboão";
        Integer ordem = 1;

        IRegionalGateway regionalGateway = mock(IRegionalGateway.class);

        when(regionalGateway.buscarRegionalPorSigla(anyString())).thenReturn(Optional.of(Regional.create(id, nome, sigla, descrica, ordem)));

        BuscarRegionalPorSiglaUseCase buscarRegionalPorSiglaUseCase = BuscarRegionalPorSiglaUseCase.create(regionalGateway);

        Regional regional = buscarRegionalPorSiglaUseCase.run(sigla);

        assertNotNull(regional);

        assertEquals(id, regional.getId());
        assertEquals(nome, regional.getNome());
        assertEquals(sigla, regional.getSigla());
        assertEquals(descrica, regional.getDescricao());
        assertEquals(ordem, regional.getOrdem());
    }

    @DisplayName("Busca regional por sigla com sucesso")
    @Test
    void buscaRegionalPorSiglaEmpty() {
        String sigla = "R3";

        IRegionalGateway regionalGateway = mock(IRegionalGateway.class);

        when(regionalGateway.buscarRegionalPorSigla(anyString())).thenReturn(Optional.empty());

        BuscarRegionalPorSiglaUseCase buscarRegionalPorSiglaUseCase = BuscarRegionalPorSiglaUseCase.create(regionalGateway);

        RegionalNaoExistenteException regionalNaoExistenteException = assertThrows(RegionalNaoExistenteException.class, () -> {
            buscarRegionalPorSiglaUseCase.run(sigla);
        });

        assertEquals("Regional não existe", regionalNaoExistenteException.getMessage());
    }


}