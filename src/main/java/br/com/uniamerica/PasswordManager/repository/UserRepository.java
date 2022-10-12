package br.com.uniamerica.PasswordManager.repository;

import br.com.uniamerica.PasswordManager.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    public Optional<User> findByUsername(String userName);
}
