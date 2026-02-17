package com.perfumeria.Perfumeria.service;

import com.perfumeria.Perfumeria.model.Producto;
import com.perfumeria.Perfumeria.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    // Singleton: Spring inyecta una sola instancia via constructor
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // Buscar por id
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Buscar por nombre
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByProductoContainingIgnoreCase(nombre);
    }

    // Obtener solo productos con stock disponible
    public List<Producto> obtenerConStock() {
        return productoRepository.findByStockGreaterThan(0);
    }

    // Guardar o actualizar producto
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    // Eliminar producto
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    // Realizar venta: descuenta stock de cada producto vendido
    // Recibe una lista de pares (id, cantidad)
    public void realizarVenta(List<Long> ids, List<Integer> cantidades) {
        for (int i = 0; i < ids.size(); i++) {
            Producto producto = productoRepository.findById(ids.get(i))
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            int stockActual = producto.getStock() != null ? producto.getStock() : 0;
            int cantidadVendida = cantidades.get(i);

            if (cantidadVendida > stockActual) {
                throw new RuntimeException("Stock insuficiente para: " + producto.getProducto());
            }

            producto.setStock(stockActual - cantidadVendida);
            productoRepository.save(producto);
        }
    }
}