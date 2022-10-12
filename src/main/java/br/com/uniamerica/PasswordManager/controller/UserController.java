package br.com.uniamerica.PasswordManager.controller;

import br.com.uniamerica.PasswordManager.entity.User;
import br.com.uniamerica.PasswordManager.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    public UserController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    //processo de login
    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String username, @RequestParam String senha) {


        Optional<User> usuario = this.userService.findByUsername(username);

        //verifica se encontrou o usuario
        if(usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        boolean valid = false;

        User user = new User();
        valid = passwordEncoder.matches(senha, user.getSenha());

        int status = (valid) ? HttpStatus.OK.value() : HttpStatus.UNAUTHORIZED.value();

        return ResponseEntity.status(status).body(valid);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> insert(@RequestBody User user) {
        try {
            user.setSenha(passwordEncoder.encode(user.getSenha()));
            this.userService.saveUsuario(user);
            return ResponseEntity.ok().body("cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User user) {
        try {
            user.setId(id);
            this.userService.saveUsuario(user);
            return ResponseEntity.ok().body("atualizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        try {
            this.userService.delete(id);
            return ResponseEntity.ok().body("deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok().body(this.userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        if(this.userService.findById(id) == null) {
            return ResponseEntity.badRequest().body("usuario nao encontrado");
        } else {
            return ResponseEntity.ok().body(this.userService.findById(id));
        }
    }
}