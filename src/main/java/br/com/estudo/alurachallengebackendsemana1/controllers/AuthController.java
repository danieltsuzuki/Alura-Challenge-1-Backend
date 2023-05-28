package br.com.estudo.alurachallengebackendsemana1.controllers;

import br.com.estudo.alurachallengebackendsemana1.dtos.user.UserDTO;
import br.com.estudo.alurachallengebackendsemana1.dtos.user.UserDTOInsert;
import br.com.estudo.alurachallengebackendsemana1.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/signup")
    @Transactional
    public ResponseEntity signup(@RequestBody UserDTO userDTO){
        service.save(userDTO);
        UserDTOInsert response = new UserDTOInsert(userDTO.getUsername());
        return ResponseEntity.ok(response);
    }
}
