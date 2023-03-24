package com.asps.jsonview.entity;

import com.asps.jsonview.view.UsuarioView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "usuarios")
public class Usuario {

    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @JsonView({UsuarioView.Resumo.class})
    private String cpf;

    @JsonView({UsuarioView.Resumo.class, UsuarioView.ApenasNome.class})
    private String nome;
    private String email;
    private String senha;
}
