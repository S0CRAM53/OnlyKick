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

    //Eliminacion por cascada de productos, borrando imagenes, inventario y desvinculando productos venta
    public void deleteById(Integer id) {
        //Borra Imágenes asociadas
        List<Imagen> imagenes = imagenRepository.findByProductoIdProducto(id);
        if (imagenes != null && !imagenes.isEmpty()) {
            imagenRepository.deleteAll(imagenes);
        }

        //Borra Inventario asociado
        List<Inventario> inventarios = inventarioRepository.findByProductoIdProducto(id);
        if (inventarios != null && !inventarios.isEmpty()) {
            inventarioRepository.deleteAll(inventarios);
        }

        //Desvincular historial de ProductosVenta
        List<ProductosVenta> pVentas = productosVentaRepository.findByProductoIdProducto(id);
         if (pVentas != null && !pVentas.isEmpty()) {
            for (ProductosVenta pVenta : pVentas) {
                pVenta.setProducto(null); // Desvincula el producto de la venta
                productosVentaRepository.save(pVenta);
            }
         }
        
        //borra el Producto
        productoRepository.deleteById(id);
    }

    //Métodos de filtrado
    public List<Producto> findByCategoriaId(Integer idCategoria) {
        return productoRepository.findByCategoriaIdCategoria(idCategoria);
    }

    public List<Producto> findByMarcaId(Integer idMarca) {
        return productoRepository.findByMarcaIdMarca(idMarca);
    }
}