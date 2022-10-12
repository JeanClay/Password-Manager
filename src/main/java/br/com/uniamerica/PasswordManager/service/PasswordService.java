package br.com.uniamerica.PasswordManager.service;

import br.com.uniamerica.PasswordManager.entity.Password;
import br.com.uniamerica.PasswordManager.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    @Transactional
    public void insert(Password password) {
        this.passwordRepository.save(password);
    }

    public void delete(Long id) {
        this.passwordRepository.deleteById(id);
    }

    public List<Password> findAll() {
        return this.passwordRepository.findAll();
    }

    public Password findById(Long id) {
        Password senha = new Password();
        if(this.passwordRepository.findById(id).isPresent()) {
            senha = this.passwordRepository.findById(id).get();
            return senha;
        } else {
            return null;
        }
    }
}
