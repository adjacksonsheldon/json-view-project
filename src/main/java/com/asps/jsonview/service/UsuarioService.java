package com.asps.jsonview.service;

import com.asps.jsonview.entity.Usuario;
import com.asps.jsonview.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario incluirOuAtualizar(Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario consultarPorId(String usuarioId){
        return usuarioRepository.findById(usuarioId).orElseThrow();
    }
}