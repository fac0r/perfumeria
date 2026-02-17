package com.perfumeria.Perfumeria.repository;

import com.perfumeria.Perfumeria.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Buscar por nombre (ignorando mayusculas/minusculas)
    List<Producto> findByProductoContainingIgnoreCase(String nombre);

    // Buscar solo productos con stock disponible
    List<Producto> findByStockGreaterThan(int cantidad);
}