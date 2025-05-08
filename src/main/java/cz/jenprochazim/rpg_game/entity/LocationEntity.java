package cz.jenprochazim.rpg_game.entity;

import cz.jenprochazim.rpg_game.entity.enums.TerrainType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "locations")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String describtion;

    @Enumerated (EnumType.STRING)
    private TerrainType terrainType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private UserEntity creator;

    private String note;

    private Integer r;

    private Integer p;
}
