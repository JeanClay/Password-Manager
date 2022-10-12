package br.com.uniamerica.PasswordManager.service;

import br.com.uniamerica.PasswordManager.data.UserDataDetails;
import br.com.uniamerica.PasswordManager.entity.User;
import br.com.uniamerica.PasswordManager.repository.UserRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class DetailsUserService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public DetailsUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUsername(username);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("usuario não encontrado");
        }

        return new UserDataDetails(user);
    }
}