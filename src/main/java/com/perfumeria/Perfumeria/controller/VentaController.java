// VentaController.java
package com.perfumeria.Perfumeria.controller;

import com.perfumeria.Perfumeria.model.Venta;
import com.perfumeria.Perfumeria.model.Producto;
import com.perfumeria.Perfumeria.service.VentaService;
import com.perfumeria.Perfumeria.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    private final VentaService ventaService;
    private final ProductoService productoService;

    public VentaController(VentaService ventaService, ProductoService productoService) {
        this.ventaService = ventaService;
        this.productoService = productoService;
    }

    // Obtener todas las ventas (solo admin)
    @GetMapping
    public List<Venta> obtenerTodas() {
        return ventaService.obtenerTodas();
    }

    // Obtener ventas por vendedor
    @GetMapping("/vendedor/{nombre}")
    public List<Venta> obtenerPorVendedor(@PathVariable String nombre) {
        return ventaService.obtenerPorVendedor(nombre);
    }

    // Realizar venta: descuenta stock y registra la venta
    @PostMapping
    public ResponseEntity<?> realizarVenta(@RequestBody Map<String, Object> body) {
        try {
            String vendedor = (String) body.get("vendedor");
            String cliente = (String) body.get("cliente");
            Map<String, Integer> carrito = (Map<String, Integer>) body.get("carrito");

            // Descontar stock y calcular total
            List<Long> ids = carrito.keySet().stream().map(Long::parseLong).toList();
            List<Integer> cantidades = carrito.values().stream().toList();
            productoService.realizarVenta(ids, cantidades);

            // Calcular total
            double total = 0;
            for (Map.Entry<String, Integer> entry : carrito.entrySet()) {
                Long id = Long.parseLong(entry.getKey());
                int cant = entry.getValue();
                Producto p = productoService.obtenerPorId(id).orElseThrow();
                total += (p.getPrecioMayorista() != null ? p.getPrecioMayorista() : 0) * cant;
            }

            // Registrar venta
            Venta venta = new Venta();
            venta.setVendedor(vendedor);
            venta.setCliente(cliente);
            venta.setTotal(total);
            ventaService.registrarVenta(venta);

            return ResponseEntity.ok(Map.of("mensaje", "Venta realizada con éxito", "total", total));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}