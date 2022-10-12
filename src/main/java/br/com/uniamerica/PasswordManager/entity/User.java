package br.com.uniamerica.PasswordManager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "users")
@NoArgsConstructor
@Getter
public class User {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @Getter
    @Setter
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    @Getter
    @Setter
    @Column(name = "login", nullable = false, unique = true)
    public String login;

    @Getter
    @Setter
    @Column(name = "senha", nullable = false)
    public String senha;
}
