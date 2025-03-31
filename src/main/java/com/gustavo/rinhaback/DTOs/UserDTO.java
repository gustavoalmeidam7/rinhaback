package com.gustavo.rinhaback.DTOs;

import java.util.Date;

import com.gustavo.rinhaback.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/*
 * 
 * DTO for table users
 * 
 * apelido varchar[32] NOT NULL, username
 * nome varchar[100] NOT NULL,   full name
 * nascimento date NOT NULL,     birthday
 * stack varchar[32] ARRAY       user stack
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String apelido;
    private String nome;
    private Date nascimento;
    private String[] stack;

    public static UserDTO fromEntity(User userEntity) {
        return UserDTO.builder()
                      .apelido(userEntity.getApelido())
                      .nome(userEntity.getNome())
                      .nascimento(userEntity.getNascimento())
                      .stack(userEntity.getStack())
                      .build();
    }

    public User toEntity() {
        return User.builder()
                   .apelido(this.getApelido())
                   .nome(this.getNome())
                   .nascimento(this.getNascimento())
                   .stack(this.getStack())
                   .build();
    }
}
