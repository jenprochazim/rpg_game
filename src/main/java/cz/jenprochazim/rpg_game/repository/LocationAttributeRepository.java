package cz.jenprochazim.rpg_game.repository;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationAttributeDTO;
import cz.jenprochazim.rpg_game.entity.LocationAttributeEntity;
import cz.jenprochazim.rpg_game.entity.LocationEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationAttributeRepository extends JpaRepository<LocationAttributeEntity, Long> {
    List<LocationAttributeEntity> findByLocation(LocationEntity location);

    boolean existsByNameAndLocation(@NotBlank String name, @NotNull LocationEntity location);
}
