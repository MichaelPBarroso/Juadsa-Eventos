package br.com.juadsa.regional.application.domain.usecases.regional;

import br.com.juadsa.regional.application.domain.entities.Regional;
import br.com.juadsa.regional.application.interfaces.gateway.IRegionalGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BuscarTodasRegionaisUseCaseTest {

    @DisplayName("Buscar todas regionais com sucesso")
    @Test
    void buscarTodasRegionais() {
        String id = "1";
        String nome = "Regional 3";
        String sigla = "R3";
        String descrica = "Regional 3 - Composta pelos campos: Capão, Embu, Independencia, PSA, Taboão";
        Integer ordem = 1;

        IRegionalGateway regionalGateway = mock(IRegionalGateway.class);

        List<Regional> regionaisMock = new ArrayList<>();
        regionaisMock.add(Regional.create(id, nome, sigla, descrica, ordem));

        when(regionalGateway.buscarTodasRegionais()).thenReturn(regionaisMock);

        BuscarTodasRegionaisUseCase buscarTodasRegionaisUseCase = BuscarTodasRegionaisUseCase.create(regionalGateway);

        List<Regional> regionaisList = buscarTodasRegionaisUseCase.run();

        assertNotNull(regionaisList);

        assertEquals(regionaisMock.size(), regionaisList.size());
        assertEquals(regionaisMock.getFirst(), regionaisList.getFirst());
    }

}