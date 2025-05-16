package cz.jenprochazim.rpg_game.repository;

import cz.jenprochazim.rpg_game.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    Optional<LocationEntity> findByPAndR(int r, int p);
}
