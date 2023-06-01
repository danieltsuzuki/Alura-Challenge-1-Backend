package br.com.estudo.alurachallengebackendsemana1.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserDTOInsert {

    private String message;

    public UserDTOInsert(String userName){
        this.message = "Successfully registered " + userName;
    }
}
