package com.example.OnlyKick.service;

import com.example.OnlyKick.model.Genero;
import com.example.OnlyKick.model.Producto;
import com.example.OnlyKick.repository.GeneroRepository;
import com.example.OnlyKick.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("null")
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Genero> findAll() {
        return generoRepository.findAll();
    }

    public Genero findById(Integer id) {
        return generoRepository.findById(id).orElse(null);
    }

    public Genero save(Genero genero) {
        return generoRepository.save(genero);
    }

    //Eliminacion por cascada de generos, desvinculando los productos asociados
    public void deleteById(Integer id) {
        //Busca todos los productos que usan este genero
        List<Producto> productos = productoRepository.findByGeneroIdGenero(id);
        
        //Desvincula el genero de los productos asociados
        for (Producto producto : productos) {
            producto.setGenero(null);
            productoRepository.save(producto);
        }
        //Borra el genero
        generoRepository.deleteById(id);
    }
}