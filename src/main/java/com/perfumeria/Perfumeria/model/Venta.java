package com.perfumeria.Perfumeria.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String vendedor;

    @Column(nullable = false)
    private String cliente;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDateTime.now();
    }

    // Getters
    public Long getId() { return id; }
    public String getVendedor() { return vendedor; }
    public String getCliente() { return cliente; }
    public Double getTotal() { return total; }
    public LocalDateTime getFecha() { return fecha; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setVendedor(String vendedor) { this.vendedor = vendedor; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public void setTotal(Double total) { this.total = total; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}