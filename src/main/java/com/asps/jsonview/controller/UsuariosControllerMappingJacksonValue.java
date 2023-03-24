package com.asps.jsonview.controller;

import com.asps.jsonview.entity.Usuario;
import com.asps.jsonview.repository.UsuarioRepository;
import com.asps.jsonview.view.UsuarioView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios-mapping-jackson")
@RequiredArgsConstructor
public class UsuariosControllerMappingJacksonValue {

    private final UsuarioRepository usuarioRepository;

    @GetMapping
    public MappingJacksonValue consultarTodosResumido(@RequestParam(required = false) String projecao){
        List<Usuario> usuarios = usuarioRepository.findAll();

        final var usuariosWrapper = new MappingJacksonValue(usuarios);

        if("apenas-nome".equals(projecao)){
            usuariosWrapper.setSerializationView(UsuarioView.ApenasNome.class);
        }else if("resumo".equals(projecao)){
            usuariosWrapper.setSerializationView(UsuarioView.Resumo.class);
        }

        return usuariosWrapper;
    }
}