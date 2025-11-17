package com.example.OnlyKick.service;

import com.example.OnlyKick.model.Inventario;
import com.example.OnlyKick.model.ProductosVenta;
import com.example.OnlyKick.model.Talla;
import com.example.OnlyKick.repository.InventarioRepository;
import com.example.OnlyKick.repository.ProductosVentaRepository;
import com.example.OnlyKick.repository.TallaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("null")
public class TallaService {

    @Autowired
    private TallaRepository tallaRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductosVentaRepository productosVentaRepository;

    public List<Talla> findAll() {
        return tallaRepository.findAll();
    }

    public Talla findById(Integer id) {
        return tallaRepository.findById(id).orElse(null);
    }

    public Talla save(Talla talla) {
        return tallaRepository.save(talla);
    }

    //Eliminacion por casacada de tallas, borrando inventario y desvinculando productos venta
    public void deleteById(Integer id) {
        //Borra todas las entradas de Inventario que usan esta Talla
        List<Inventario> inventarios = inventarioRepository.findByTallaIdTalla(id);
        if (inventarios != null && !inventarios.isEmpty()) {
            inventarioRepository.deleteAll(inventarios);
        }

        //Desvincular el historial de ProductosVenta (no borrar el historial)
        List<ProductosVenta> ventasPasadas = productosVentaRepository.findByTallaIdTalla(id);
        if (ventasPasadas != null && !ventasPasadas.isEmpty()) {
            for (ProductosVenta venta : ventasPasadas) {
                venta.setTalla(null);
                productosVentaRepository.save(venta);
            }
        }

        //Borra la Talla
        tallaRepository.deleteById(id);
    }
}