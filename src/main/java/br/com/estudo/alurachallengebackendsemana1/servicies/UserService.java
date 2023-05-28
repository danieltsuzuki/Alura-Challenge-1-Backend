package br.com.estudo.alurachallengebackendsemana1.servicies;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.User;
import br.com.estudo.alurachallengebackendsemana1.dtos.user.UserDTO;
import br.com.estudo.alurachallengebackendsemana1.dtos.user.UserDTOInsert;
import br.com.estudo.alurachallengebackendsemana1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    public User save(UserDTO userDTO){
        User existingUser = repository.findByUsername(userDTO.getUsername());

        if (existingUser != null){
            throw new RuntimeException("The user already exists!");
        }

        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());

        User user = new User(userDTO.getUsername(), encryptedPassword);

        return repository.save(user);
    }
}
