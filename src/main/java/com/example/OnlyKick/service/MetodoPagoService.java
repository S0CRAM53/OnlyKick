package com.example.OnlyKick.service;

import com.example.OnlyKick.model.MetodoPago;
import com.example.OnlyKick.model.Venta;
import com.example.OnlyKick.repository.MetodoPagoRepository;
import com.example.OnlyKick.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("null")
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;
    
    @Autowired
    private VentaRepository ventaRepository;

    public List<MetodoPago> findAll() {
        return metodoPagoRepository.findAll();
    }

    public MetodoPago findById(Integer id) {
        return metodoPagoRepository.findById(id).orElse(null);
    }

    public MetodoPago save(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    public void deleteById(Integer id) {
        //desvincula el metodo de pago de las ventas que lo usan antes de eliminarlo
        List<Venta> ventas = ventaRepository.findByMetodoPagoIdMetodoPago(id);
        if (ventas != null && !ventas.isEmpty()) {
            for (Venta venta : ventas) {
                venta.setMetodoPago(null);
                ventaRepository.save(venta);
            }
        }
        metodoPagoRepository.deleteById(id);
    }
}