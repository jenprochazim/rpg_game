package cz.jenprochazim.rpg_game.repository;

import cz.jenprochazim.rpg_game.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);

    boolean existsByEmail(String email);
}
