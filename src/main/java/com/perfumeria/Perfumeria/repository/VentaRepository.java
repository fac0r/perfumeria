// VentaRepository.java
package com.perfumeria.Perfumeria.repository;

import com.perfumeria.Perfumeria.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByVendedor(String vendedor);
}