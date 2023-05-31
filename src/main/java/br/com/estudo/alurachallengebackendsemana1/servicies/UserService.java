package br.com.estudo.alurachallengebackendsemana1.servicies;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.User;
import br.com.estudo.alurachallengebackendsemana1.dtos.user.UserDTO;
import br.com.estudo.alurachallengebackendsemana1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    public User save(UserDTO userDTO){
        var existingUser = repository.findByUsername(userDTO.getUsername());

        if (existingUser != null){
            throw new UsernameNotFoundException("The user already exists!");
        }

        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());

        User user = new User(userDTO.getUsername(), encryptedPassword);

        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }
}
