package com.asps.jsonview.controller;

import com.asps.jsonview.entity.Usuario;
import com.asps.jsonview.repository.UsuarioRepository;
import com.asps.jsonview.service.UsuarioService;
import com.asps.jsonview.view.UsuarioView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
@RequiredArgsConstructor
public class UsuariosController {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> consultarTodos(){
        return usuarioRepository.findAll();
    }

    @JsonView(UsuarioView.Resumo.class)
    @GetMapping(params = "projecao=resumo")
    public List<Usuario> consultarTodosResumido(){
        return usuarioRepository.findAll();
    }

    @JsonView(UsuarioView.ApenasNome.class)
    @GetMapping(params = "projecao=apenas-nome")
    public List<Usuario> consultarTodosApenasNome(){
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario incluir(@RequestBody Usuario usuario){
        return usuarioService.incluirOuAtualizar(usuario);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") String cpf){
        final var usuarioOptional = usuarioRepository.findById(cpf);

        usuarioOptional.ifPresent(usuarioRepository::delete);
    }

    @GetMapping("/{usuarioId}")
    public Usuario consultarUsuarioPorId(@PathVariable("usuarioId") String usuarioId){
        return usuarioService.consultarPorId(usuarioId);
    }
}