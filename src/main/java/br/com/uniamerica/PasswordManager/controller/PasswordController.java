package br.com.uniamerica.PasswordManager.controller;

import br.com.uniamerica.PasswordManager.entity.Password;
import br.com.uniamerica.PasswordManager.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/passwords")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping("/{idPassword}")
    public ResponseEntity<Password> findById(
            @PathVariable("idPassword") Long idPassword){
        return ResponseEntity.ok().body(this.passwordService.findById(idPassword));
    }

    @GetMapping
    public ResponseEntity<List<Password>> findAll() {
        return ResponseEntity.ok().body(this.passwordService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Password password){
        try {
            this.passwordService.insert(password);
            return ResponseEntity.ok().body("Senha Cadastrada com Sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            this.passwordService.delete(id);
            return ResponseEntity.ok().body("cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Password password) {
        try {
            password.setId(id);
            this.passwordService.insert(password);
            return ResponseEntity.ok().body("cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }

}
