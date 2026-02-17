package com.perfumeria.Perfumeria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String producto;

    @Column(name = "precio_mayorista")
    private Double precioMayorista;

    @Column(name = "precio_sugerido")
    private Double precioSugerido;

    private Integer stock;

    // Getters
    public Long getId() { return id; }
    public String getProducto() { return producto; }
    public Double getPrecioMayorista() { return precioMayorista; }
    public Double getPrecioSugerido() { return precioSugerido; }
    public Integer getStock() { return stock; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setProducto(String producto) { this.producto = producto; }
    public void setPrecioMayorista(Double precioMayorista) { this.precioMayorista = precioMayorista; }
    public void setPrecioSugerido(Double precioSugerido) { this.precioSugerido = precioSugerido; }
    public void setStock(Integer stock) { this.stock = stock; }
}