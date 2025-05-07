package cz.jenprochazim.rpg_game.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    @NotBlank (message = "Uzivatelske jmeno nemuze byt prazdne")
    private String userName;

    @Column(nullable = false)
    @NotBlank (message = "Heslo nemuze byt prazdne")
    private String password;

    @Column(nullable = false, unique = true)
    @NotBlank (message = "Email nemuze byt prazdny")
    @Email (message = "Nespravny format")
    private String email;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

}
