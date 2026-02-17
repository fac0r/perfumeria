// UsuarioService.java
package com.perfumeria.Perfumeria.service;

import com.perfumeria.Perfumeria.model.Usuario;
import com.perfumeria.Perfumeria.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> login(String nombre, String password) {
        return usuarioRepository.findByNombreAndPassword(nombre, password);
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}