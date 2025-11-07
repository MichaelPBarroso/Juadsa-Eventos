package br.com.juadsa.regional.infrastructure.api;

import br.com.juadsa.regional.application.controller.RegionalController;
import br.com.juadsa.regional.application.dto.NovaRegionalDTO;
import br.com.juadsa.regional.application.dto.RegionalDTO;
import br.com.juadsa.regional.infrastructure.api.json.NovaRegionalJson;
import br.com.juadsa.regional.infrastructure.api.json.RegionalJson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RegionalApiController implements RegionalApi {

    private final RegionalController regionalController;

    @Override
    public ResponseEntity<RegionalJson> createRegional(NovaRegionalJson regional) {
        NovaRegionalDTO novaRegionalDTO = new NovaRegionalDTO(regional.getNome(), regional.getSigla(), regional.getDescricao(), regional.getOrdem());

        RegionalDTO regionalDTO = regionalController.incluir(novaRegionalDTO);

        return ResponseEntity.ok(toJson(regionalDTO));
    }

    @Override
    public ResponseEntity<List<RegionalJson>> getRegionais() {
        List<RegionalDTO> regionalDTOS = regionalController.buscarRegionais();

        return ResponseEntity.ok(
                regionalDTOS.stream().map(this::toJson).collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<RegionalJson> getRegionalBySigla(String sigla) {
        RegionalDTO regionalDTO = regionalController.buscarRegionalPorSigla(sigla);

        return ResponseEntity.ok(toJson(regionalDTO));
    }

    @Override
    public ResponseEntity<Void> removeRegional(String id) {
        regionalController.remover(id);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<RegionalJson> updateRegional(String id, RegionalJson body) {
        RegionalDTO dto = toDto(id, body);

        RegionalDTO regionalDTO = regionalController.atualizar(dto);

        return ResponseEntity.ok(toJson(regionalDTO));
    }

    private RegionalJson toJson(RegionalDTO regionalDTO){
        RegionalJson regionalJson = new RegionalJson();

        regionalJson.setId(regionalDTO.id());
        regionalJson.setNome(regionalDTO.nome());
        regionalJson.setSigla(regionalDTO.sigla());
        regionalJson.setDescricao(regionalDTO.descricao());
        regionalJson.setOrdem(regionalDTO.ordem());

        return regionalJson;
    }

    private RegionalDTO toDto(RegionalJson regional){
        return new RegionalDTO(
                regional.getId(),
                regional.getNome(),
                regional.getSigla(),
                regional.getDescricao(),
                regional.getOrdem()
        );
    }
    private RegionalDTO toDto(String id, RegionalJson regional){
        return new RegionalDTO(
                id,
                regional.getNome(),
                regional.getSigla(),
                regional.getDescricao(),
                regional.getOrdem()
        );
    }
}
