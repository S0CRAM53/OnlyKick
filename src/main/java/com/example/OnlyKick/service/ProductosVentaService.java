package com.example.OnlyKick.service;

import com.example.OnlyKick.model.ProductosVenta;
import com.example.OnlyKick.repository.ProductosVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("null")
public class ProductosVentaService {

    @Autowired
    private ProductosVentaRepository productosVentaRepository;

    public List<ProductosVenta> findAll() {
        return productosVentaRepository.findAll();
    }
    
    //Busca los productos venta asociados a una venta especifica por su id
    public List<ProductosVenta> findByVentaId(Integer idVenta) {
        return productosVentaRepository.findByVentaIdVenta(idVenta);
    }

    public ProductosVenta findById(Integer id) {
        return productosVentaRepository.findById(id).orElse(null);
    }
    
    // No se recomienda tener un 'save' o 'delete' público aquí.
    // Esas operaciones deben ser manejadas exclusivamente por 'VentaService'
    // para asegurar la integridad del stock y los totales.
}