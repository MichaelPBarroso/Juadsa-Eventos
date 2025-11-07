package br.com.juadsa.regional.infrastructure.persistence.repository;

import br.com.juadsa.regional.infrastructure.persistence.entity.RegionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionalJPARepository extends JpaRepository<RegionalEntity, String> {

    Optional<RegionalEntity> findBySigla(String regionalCode);

    Optional<RegionalEntity> findByNome(String regionalCode);
}
