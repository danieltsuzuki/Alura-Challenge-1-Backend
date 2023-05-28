package br.com.estudo.alurachallengebackendsemana1.dtos.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotEmpty
    @Length(min = 3, max = 20)
    private String username;
    @NotEmpty
    @Length(min = 8, max = 64)
    private String password;
}
