package cz.jenprochazim.rpg_game.repository;

import cz.jenprochazim.rpg_game.entity.LocationAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationAttributeRepository extends JpaRepository<LocationAttributeEntity, Long> {
}
