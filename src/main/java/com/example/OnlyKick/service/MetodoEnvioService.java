package com.example.OnlyKick.service;

import com.example.OnlyKick.model.MetodoEnvio;
import com.example.OnlyKick.model.Venta;
import com.example.OnlyKick.repository.MetodoEnvioRepository;
import com.example.OnlyKick.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("null")
public class MetodoEnvioService {

    @Autowired
    private MetodoEnvioRepository metodoEnvioRepository;
    
    @Autowired
    private VentaRepository ventaRepository;

    public List<MetodoEnvio> findAll() {
        return metodoEnvioRepository.findAll();
    }

    public MetodoEnvio findById(Integer id) {
        return metodoEnvioRepository.findById(id).orElse(null);
    }

    public MetodoEnvio save(MetodoEnvio metodoEnvio) {
        return metodoEnvioRepository.save(metodoEnvio);
    }

    public void deleteById(Integer id) {
        //desvincula el metodo de envio de las ventas que lo usan antes de eliminarlo
        List<Venta> ventas = ventaRepository.findByMetodoEnvioIdMetodoEnvio(id);
        if (ventas != null && !ventas.isEmpty()) {
            for (Venta venta : ventas) {
                venta.setMetodoEnvio(null);
                ventaRepository.save(venta);
            }
        }
        metodoEnvioRepository.deleteById(id);
    }
}