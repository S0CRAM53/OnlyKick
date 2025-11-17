package com.example.OnlyKick.service;

import com.example.OnlyKick.model.Color;
import com.example.OnlyKick.model.Inventario;
import com.example.OnlyKick.model.ProductosVenta;
import com.example.OnlyKick.repository.ColorRepository;
import com.example.OnlyKick.repository.InventarioRepository;
import com.example.OnlyKick.repository.ProductosVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("null")
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductosVentaRepository productosVentaRepository;

    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    public Color findById(Integer id) {
        return colorRepository.findById(id).orElse(null);
    }

    public Color save(Color color) {
        return colorRepository.save(color);
    }

    //Eliminacion por cascada de colores, borrando inventario y desvinculando productos venta
    public void deleteById(Integer id) {
        //Borra todas las entradas de Inventario que usan este Color
        List<Inventario> inventarios = inventarioRepository.findByColorIdColor(id);
        if (inventarios != null && !inventarios.isEmpty()) {
            inventarioRepository.deleteAll(inventarios);
        }

        //Desvincular el historial de ProductosVenta (no borrar historial)
        List<ProductosVenta> ventasPasadas = productosVentaRepository.findByColorIdColor(id);
        if (ventasPasadas != null && !ventasPasadas.isEmpty()) {
            for (ProductosVenta venta : ventasPasadas) {
                venta.setColor(null); // Pone la FK a null
                productosVentaRepository.save(venta);
            }
        }
        //borrar el Color
        colorRepository.deleteById(id);
    }
}