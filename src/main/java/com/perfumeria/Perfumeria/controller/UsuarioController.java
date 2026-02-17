// UsuarioController.java
package com.perfumeria.Perfumeria.controller;

import com.perfumeria.Perfumeria.model.Usuario;
import com.perfumeria.Perfumeria.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String nombre = credenciales.get("nombre");
        String password = credenciales.get("password");

        return usuarioService.login(nombre, password)
                .map(u -> ResponseEntity.ok(Map.of(
                        "id", u.getId(),
                        "nombre", u.getNombre(),
                        "categoria", u.getCategoria().toString()
                )))
                .orElse(ResponseEntity.status(401).build());
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.guardar(usuario));
    }
}