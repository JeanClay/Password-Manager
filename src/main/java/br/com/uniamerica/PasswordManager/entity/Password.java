package br.com.uniamerica.PasswordManager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "passwords")
@NoArgsConstructor
public class Password {

    @Id
    @Column(name = "id", nullable = false,unique = true)
    @Getter
    @Setter
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    @Getter
    @Setter
    @Column(name = "senha", nullable = false)
    public String senha;

    @Getter
    @Setter
    @Column(name = "descricao", nullable = false)
    public String descricao;

    @Getter
    @Setter
    @Column(name = "url", nullable = false)
    public String url;
}
