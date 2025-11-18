package com.example.OnlyKick.service;

import com.example.OnlyKick.model.Direcciones;
import com.example.OnlyKick.model.Venta;
import com.example.OnlyKick.repository.DireccionesRepository;
import com.example.OnlyKick.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("null")
public class DireccionesService {

    @Autowired
    private DireccionesRepository direccionesRepository;
    
    @Autowired
    private VentaRepository ventaRepository;

    public List<Direcciones> findAll() {
        return direccionesRepository.findAll();
    }

    public Direcciones findById(Integer id) {
        return direccionesRepository.findById(id).orElse(null);
    }

    public List<Direcciones> findByUsuarioId(Integer idUsuario) {
        return direccionesRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public Direcciones save(Direcciones direccion) {
        return direccionesRepository.save(direccion);
    }

    //Eliminacion por cascada para borrar una direccion, desvinculando su historial de ventas asociadas
    public void deleteById(Integer id) {
        //Desvincula el historial de ventas asociadas a esta direccion
        List<Venta> ventas = ventaRepository.findByDireccionIdDireccion(id);
        if (ventas != null && !ventas.isEmpty()) {
            for (Venta venta : ventas) {
                venta.setDireccion(null);
                ventaRepository.save(venta);
            }
        }

        //Borrar la Direccion
        direccionesRepository.deleteById(id);
    }
}