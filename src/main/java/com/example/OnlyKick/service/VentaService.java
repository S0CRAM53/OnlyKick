package com.example.OnlyKick.service;

import com.example.OnlyKick.model.Inventario;
import com.example.OnlyKick.model.Producto;
import com.example.OnlyKick.model.ProductosVenta;
import com.example.OnlyKick.model.Venta;
import com.example.OnlyKick.repository.InventarioRepository;
import com.example.OnlyKick.repository.ProductoRepository;
import com.example.OnlyKick.repository.ProductosVentaRepository;
import com.example.OnlyKick.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@SuppressWarnings("null")
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductosVentaRepository productosVentaRepository;
    
    @Autowired
    private InventarioRepository inventarioRepository;
    
    @Autowired
    private ProductoRepository productoRepository;

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    public Venta findById(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public List<Venta> findByUsuarioId(Integer idUsuario) {
        return ventaRepository.findByUsuarioIdUsuario(idUsuario);
    }

    //procesarVenta es el metodo encargado de procesar una venta completa y asegura la integridad del stock y los totales
    public Venta procesarVenta(Venta venta, Set<ProductosVenta> productos) throws Exception {
        
        BigDecimal totalCalculado = BigDecimal.ZERO;

        //Verificar stock y calcular total
        for (ProductosVenta pv : productos) {
            //Busca el stock de esta variante
            Optional<Inventario> stockOpt = inventarioRepository.findByProductoIdProductoAndTallaIdTallaAndColorIdColor(
                pv.getProducto().getId_producto(),
                pv.getTalla().getId_talla(),
                pv.getColor().getId_color()
            );

            if (!stockOpt.isPresent() || stockOpt.get().getStock() < pv.getCantidad()) {
                // Si no hay stock, lanzamos una excepción y @Transactional revierte todo
                throw new Exception("No hay stock suficiente para el producto: " + pv.getProducto().getNombreProducto());
            }

            //Obtener el precio base del producto
            Producto p = productoRepository.findById(pv.getProducto().getId_producto()).orElseThrow();
            pv.setPrecioUnitario(p.getPrecioBase()); // Guardamos el precio al momento de la venta
            
            //Sumar al total
            totalCalculado = totalCalculado.add(
                p.getPrecioBase().multiply(new BigDecimal(pv.getCantidad()))
            );
        }

        //Guardar la Venta principal
        venta.setTotalVenta(totalCalculado);
        Venta ventaGuardada = ventaRepository.save(venta); // Obtenemos el ID de la venta

        //Guardar los ProductosVenta y actualizar el stock
        for (ProductosVenta pv : productos) {
            // Vincular el producto a la venta
            pv.setVenta(ventaGuardada);
            productosVentaRepository.save(pv);

            //Restar el stock
            Inventario inventario = inventarioRepository.findByProductoIdProductoAndTallaIdTallaAndColorIdColor(
                pv.getProducto().getId_producto(),
                pv.getTalla().getId_talla(),
                pv.getColor().getId_color()
            ).get(); // Sabemos que existe por la comprobación anterior

            inventario.setStock(inventario.getStock() - pv.getCantidad());
            inventarioRepository.save(inventario);
        }
        
        return ventaGuardada;
    }

    //Eliminacion por id de venta, primero elimina los productosventas asociados y luego la venta
    public void deleteById(Integer id) {
        //borra los productos asociados a la venta
        List<ProductosVenta> productosVenta = productosVentaRepository.findByVentaIdVenta(id);
        if (productosVenta != null && !productosVenta.isEmpty()) {
            productosVentaRepository.deleteAll(productosVenta);
        }
        
        //Borrar la Venta
        ventaRepository.deleteById(id);
    }
}