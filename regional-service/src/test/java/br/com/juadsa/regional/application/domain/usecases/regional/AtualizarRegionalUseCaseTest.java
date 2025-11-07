package br.com.juadsa.regional.application.domain.usecases.regional;

import br.com.juadsa.regional.application.domain.entities.Regional;
import br.com.juadsa.regional.application.dto.NovaRegionalDTO;
import br.com.juadsa.regional.application.dto.RegionalDTO;
import br.com.juadsa.regional.application.interfaces.gateway.IRegionalGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AtualizarRegionalUseCaseTest {

    @DisplayName("Atualizar regional com sucesso")
    @Test
    void atualizarRegional() {
        String id = "1";
        String nome = "Regional 3";
        String sigla = "R3";
        String descrica = "Regional 3 - Composta pelos campos: Capão, Embu, Independencia, PSA, Taboão";
        Integer ordem = 1;

        IRegionalGateway regionalGateway = mock(IRegionalGateway.class);

        when(regionalGateway.buscarRegionalPorSigla(anyString())).thenReturn(null);
        Regional regionalCadastro = Regional.create(id, nome, sigla, descrica, ordem);

        when(regionalGateway.incluir(any())).thenReturn(
                regionalCadastro
        );

        CriarRegionalUseCase criarRegionalUseCase = CriarRegionalUseCase.create(regionalGateway);

        Regional regionalCriada = criarRegionalUseCase.run(new NovaRegionalDTO(nome, sigla, descrica, ordem));

        String novaDescricao = "Regional 3 - Teste";
        regionalCriada.setDescricao(novaDescricao);

        when(regionalGateway.alterar(any())).thenReturn(
                regionalCriada
        );
        when(regionalGateway.buscarRegionalPorId(anyString())).thenReturn(
                Optional.of(regionalCadastro)
        );

        AtualizarRegionalUseCase atualizarRegionalUseCase = AtualizarRegionalUseCase.create(regionalGateway);

        Regional regionalAtualizada = atualizarRegionalUseCase.run(new RegionalDTO(regionalCriada.getId(), regionalCriada.getNome(), regionalCriada.getSigla(), novaDescricao, regionalCriada.getOrdem()));

        assertNotNull(regionalAtualizada);

        assertEquals(regionalCriada.getId(), regionalAtualizada.getId());
        assertEquals(regionalCriada.getNome(), regionalAtualizada.getNome());
        assertEquals(regionalCriada.getSigla(), regionalAtualizada.getSigla());
        assertEquals(regionalCriada.getDescricao(), regionalAtualizada.getDescricao());
        assertEquals(regionalCriada.getOrdem(), regionalAtualizada.getOrdem());

    }

}