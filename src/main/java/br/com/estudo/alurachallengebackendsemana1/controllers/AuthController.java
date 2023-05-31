package br.com.estudo.alurachallengebackendsemana1.controllers;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.User;
import br.com.estudo.alurachallengebackendsemana1.dtos.user.UserDTO;
import br.com.estudo.alurachallengebackendsemana1.dtos.user.UserDTOInsert;
import br.com.estudo.alurachallengebackendsemana1.servicies.UserService;
import br.com.estudo.alurachallengebackendsemana1.utils.security.TokenJWT;
import br.com.estudo.alurachallengebackendsemana1.utils.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping(value = "/signup")
    @Transactional
    public ResponseEntity signup(@RequestBody @Valid UserDTO userDTO){
        service.save(userDTO);
        UserDTOInsert response = new UserDTOInsert(userDTO.getUsername());
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody @Valid UserDTO userDTO){
        var authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWT(tokenJWT));
    }

}
