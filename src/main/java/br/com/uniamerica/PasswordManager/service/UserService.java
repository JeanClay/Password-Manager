package br.com.uniamerica.PasswordManager.service;

import br.com.uniamerica.PasswordManager.entity.User;
import br.com.uniamerica.PasswordManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveUsuario(User user) {
        this.userRepository.save(user);
    }

    @Transactional
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Transactional
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    @Transactional
    public User findById(Long id) {
        User user = new User();
        if(this.userRepository.findById(id).isPresent())
            user = this.userRepository.findById(id).get();
        return user;
    }
    @Transactional
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}