package com.perfumeria.Perfumeria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    public enum Categoria {
        VENDEDOR, ADMINISTRADOR
    }

    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPassword() { return password; }
    public Categoria getCategoria() { return categoria; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPassword(String password) { this.password = password; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}