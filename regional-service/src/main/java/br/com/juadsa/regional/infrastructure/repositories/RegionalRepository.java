package br.com.juadsa.regional.infrastructure.repositories;

import br.com.juadsa.regional.application.dto.NovaRegionalDTO;
import br.com.juadsa.regional.application.dto.RegionalDTO;
import br.com.juadsa.regional.application.interfaces.datasource.IRegionalDataSource;
import br.com.juadsa.regional.infrastructure.persistence.entity.RegionalEntity;
import br.com.juadsa.regional.infrastructure.persistence.repository.RegionalJPARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegionalRepository implements IRegionalDataSource {

    private final RegionalJPARepository regionalJPARepository;

    @Override
    public RegionalDTO incluir(NovaRegionalDTO novaRegionalDTO) {
        RegionalEntity regionalEntity = toEntity(novaRegionalDTO);

        RegionalEntity novaRegionalEntity = regionalJPARepository.save(regionalEntity);

        return toDTO(novaRegionalEntity);
    }

    @Override
    public void remover(String id) {
        Optional<RegionalEntity> regionalEntity = regionalJPARepository.findById(id);

        regionalEntity.ifPresent(regionalJPARepository::delete);
    }

    @Override
    public RegionalDTO alterar(String id, RegionalDTO regionalDTO) {
        RegionalEntity regionalEntity = RegionalEntity.builder()
                .id(id)
                .nome(regionalDTO.nome())
                .sigla(regionalDTO.sigla())
                .ordem(regionalDTO.ordem())
                .descricao(regionalDTO.descricao())
                .build();

        RegionalEntity novaRegionalEntity = regionalJPARepository.save(regionalEntity);

        return toDTO(novaRegionalEntity);
    }

    @Override
    public RegionalDTO buscarRegionalPorSigla(String sigla) {
        return regionalJPARepository.findBySigla(sigla)
                .map(this::toDTO)
                .orElse(null);
    }

    @Override
    public RegionalDTO buscarRegionalPorNome(String nome) {
        /*
        Optional<RegionalEntity> regionalEntity = regionalJPARepository.findByNome(nome);

        if (regionalEntity.isPresent()) {
            RegionalEntity item = regionalEntity.get();

            return new RegionalDTO(item.getId(), item.getNome(), item.getSigla(), item.getDescricao(), item.getOrdem());
        }

        return null;
         */

        return regionalJPARepository.findByNome(nome)
                .map(this::toDTO)
                .orElse(null);
    }

    @Override
    public RegionalDTO buscarRegionalPorId(String id) {
        Optional<RegionalEntity> regionalEntity = regionalJPARepository.findById(id);

        if (regionalEntity.isPresent()) {
            RegionalEntity item = regionalEntity.get();

            return new RegionalDTO(item.getId(), item.getNome(), item.getSigla(), item.getDescricao(), item.getOrdem());
        }

        return null;
    }

    @Override
    public List<RegionalDTO> buscarTodasRegionais() {
        /*
        List<RegionalEntity> regionalEntityList = regionalJPARepository.findAll();

        List<RegionalDTO> regionalDTOList = new ArrayList<>();

        regionalEntityList.forEach(regionalEntity -> regionalDTOList.add( new RegionalDTO(
                regionalEntity.getId(),
                regionalEntity.getNome(),
                regionalEntity.getSigla(),
                regionalEntity.getDescricao(),
                regionalEntity.getOrdem()
        )));

        return regionalDTOList;
         */

        return this.regionalJPARepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private RegionalDTO toDTO(RegionalEntity entity) {
        return new RegionalDTO(
                entity.getId(),
                entity.getNome(),
                entity.getSigla(),
                entity.getDescricao(),
                entity.getOrdem()
        );
    }

    private RegionalEntity toEntity(RegionalDTO dto) {
        return RegionalEntity.builder()
                .id(dto.id())
                .nome(dto.nome())
                .sigla(dto.sigla())
                .ordem(dto.ordem())
                .descricao(dto.descricao())
                .build();
    }
    private RegionalEntity toEntity(NovaRegionalDTO dto) {
        return RegionalEntity.builder()
                .nome(dto.nome())
                .sigla(dto.sigla())
                .ordem(dto.ordem())
                .descricao(dto.descricao())
                .build();
    }
}
