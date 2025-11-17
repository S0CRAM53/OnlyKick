package com.example.OnlyKick.service;

import com.example.OnlyKick.model.Marca;
import com.example.OnlyKick.model.Producto;
import com.example.OnlyKick.repository.MarcaRepository;
import com.example.OnlyKick.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("null")
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Marca findById(Integer id) {
        return marcaRepository.findById(id).orElse(null);
    }

    public Marca save(Marca marca) {
        return marcaRepository.save(marca);
    }

    public void deleteById(Integer id) {
        // Antes de eliminar la marca, desvinculamos los productos asociados
        List<Producto> productos = productoRepository.findByMarcaIdMarca(id);
        for (Producto producto : productos) {
            producto.setMarca(null);
            productoRepository.save(producto);
        }
        marcaRepository.deleteById(id);
    }
}