package com.example.OnlyKick.service;

import com.example.OnlyKick.model.EstadoVenta;
import com.example.OnlyKick.model.Venta;
import com.example.OnlyKick.repository.EstadoVentaRepository;
import com.example.OnlyKick.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("null")
public class EstadoVentaService {

    @Autowired
    private EstadoVentaRepository estadoVentaRepository;

    @Autowired
    private VentaRepository ventaRepository;

    public List<EstadoVenta> findAll() {
        return estadoVentaRepository.findAll();
    }

    public EstadoVenta findById(Integer id) {
        return estadoVentaRepository.findById(id).orElse(null);
    }

    public EstadoVenta save(EstadoVenta estadoVenta) {
        return estadoVentaRepository.save(estadoVenta);
    }

    public void deleteById(Integer id) {
        //antes de eliminar el estado de venta, desvincularlo de las ventas que lo usan
        List<Venta> ventas = ventaRepository.findByEstadoVentaIdEstado(id);
        if (ventas != null && !ventas.isEmpty()) {
            for (Venta venta : ventas) {
                venta.setEstadoVenta(null);
                ventaRepository.save(venta);
            }
        }
        estadoVentaRepository.deleteById(id);
    }
}