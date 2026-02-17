// VentaService.java
package com.perfumeria.Perfumeria.service;

import com.perfumeria.Perfumeria.model.Venta;
import com.perfumeria.Perfumeria.repository.VentaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public Venta registrarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public List<Venta> obtenerTodas() {
        return ventaRepository.findAll();
    }

    public List<Venta> obtenerPorVendedor(String vendedor) {
        return ventaRepository.findByVendedor(vendedor);
    }
}