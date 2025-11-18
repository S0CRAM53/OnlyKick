package com.example.OnlyKick.service;

import com.example.OnlyKick.model.Imagen;
import com.example.OnlyKick.model.Inventario;
import com.example.OnlyKick.model.Producto;
import com.example.OnlyKick.model.ProductosVenta;
import com.example.OnlyKick.repository.ImagenRepository;
import com.example.OnlyKick.repository.InventarioRepository;
import com.example.OnlyKick.repository.ProductoRepository;
import com.example.OnlyKick.repository.ProductosVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
@SuppressWarnings("null")
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private InventarioRepository inventarioRepository;
    
    @Autowired
    private ProductosVentaRepository productosVentaRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }


    public Producto partialUpdate(Integer id, Producto productoDetails) {
        Producto existing = findById(id);
        if (existing != null) {
            if (productoDetails.getNombreProducto() != null) {
                existing.setNombreProducto(productoDetails.getNombreProducto());
            }
            if (productoDetails.getDescripcion() != null) {
                existing.setDescripcion(productoDetails.getDescripcion());
            }
            if (productoDetails.getPrecioBase() != null) {
                existing.setPrecioBase(productoDetails.getPrecioBase());
            }
            if (productoDetails.getCategoria() != null) {
                existing.setCategoria(productoDetails.getCategoria());
            }
            if (productoDetails.getMarca() != null) {
                existing.setMarca(productoDetails.getMarca());
            }
            if (productoDetails.getMaterial() != null) {
                existing.setMaterial(productoDetails.getMaterial());
            }
            if (productoDetails.getGenero() != null) {
                existing.setGenero(productoDetails.getGenero());
            }
            
            return productoRepository.save(existing);
        }
        return null;
    }

    public void deleteById(Integer id) {
        List<Imagen> imagenes = imagenRepository.findByProductoIdProducto(id);
        if (imagenes != null && !imagenes.isEmpty()) {
            imagenRepository.deleteAll(imagenes);
        }
        List<Inventario> inventarios = inventarioRepository.findByProductoIdProducto(id);
        if (inventarios != null && !inventarios.isEmpty()) {
            inventarioRepository.deleteAll(inventarios);
        }
        List<ProductosVenta> pVentas = productosVentaRepository.findByProductoIdProducto(id);
         if (pVentas != null && !pVentas.isEmpty()) {
            for (ProductosVenta pVenta : pVentas) {
                pVenta.setProducto(null); 
                productosVentaRepository.save(pVenta);
            }
         }
        productoRepository.deleteById(id);
    }

    public List<Producto> findByCategoriaId(Integer idCategoria) {
        return productoRepository.findByCategoriaIdCategoria(idCategoria);
    }

    public List<Producto> findByMarcaId(Integer idMarca) {
        return productoRepository.findByMarcaIdMarca(idMarca);
    }
}