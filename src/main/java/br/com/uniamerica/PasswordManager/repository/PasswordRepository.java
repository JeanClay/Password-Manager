package br.com.uniamerica.PasswordManager.repository;

import br.com.uniamerica.PasswordManager.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long>{

}
