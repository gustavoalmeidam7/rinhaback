package com.gustavo.rinhaback.domain.user;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/*
 * 
 * Database representation for table users
 * 
 * UUID id PK UNIQUE NOT NULL,   id
 * apelido varchar[32] NOT NULL, username
 * nome varchar[100] NOT NULL,   full name
 * nascimento date NOT NULL,     birthday
 * stack varchar[32] ARRAY       user stack
 * 
 */
@Table(name="users")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String apelido;
    private String nome;
    private Date nascimento;
    private String[] stack;
    
    @Column(name = "userindex")
    @JsonIgnore
    private String userIndex;

    @PrePersist
    @PreUpdate
    private void generateUserIndex() {
        StringBuilder userIndexSB = new StringBuilder();

        userIndexSB
            .append(this.getApelido())
            .append(this.getNome());

        for (String stackItem : this.getStack()) {
            userIndexSB.append(stackItem);
        }

        this.setUserIndex(userIndexSB.toString());
    }
}
