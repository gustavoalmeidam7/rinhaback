package com.gustavo.rinhaback.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.rinhaback.domain.user.*;
import com.gustavo.rinhaback.services.UserService;
import com.gustavo.rinhaback.validator.UserDTOValidator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping
@RestController
public class UserController {
    private final UserService service;
    private final UserDTOValidator userDTOValidator;

    @GetMapping("/todos-pessoas")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/pessoas")
    public ResponseEntity<?> createUser(@Validated @RequestBody UserDTO user, BindingResult bindingResult) {
        userDTOValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            Map<String, Object> errors = new HashMap<String, Object>();

            for (ObjectError objectError : bindingResult.getAllErrors()) {
                errors.put("Mensagem", objectError.getCode());
            }

            return ResponseEntity.badRequest().body(errors);
        }

        User userEntity = service.save(user);

        return ResponseEntity
               .status(201)
               .header("Location", "/pessoas/" + userEntity.getId())
               .body(UserDTO.fromEntity(userEntity));
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> userEntity = service.getById(id);

        if (!userEntity.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userEntity.get());
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<User>> getUsersByTerm(@RequestParam String t) {
        List<User> entities = service.getByTerm(t);

        return ResponseEntity.ok(entities);
    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity<?> getNumberUsers() {
        return ResponseEntity.status(200).body(service.getNumberUsers());
    }

    @DeleteMapping("/deletar-todos-pessoas")
    public ResponseEntity<Map<String, Object>> deleteAllUsers() {
        Map<String, Object> map = new HashMap<String, Object>();
        
        service.deleteAll();
        map.put("Message", "Todos usuários deletados com sucesso!");

        return ResponseEntity.ok(map);
    }
}
