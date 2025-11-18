package com.example.OnlyKick.service;

import com.example.OnlyKick.model.Imagen;
import com.example.OnlyKick.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
@SuppressWarnings("null")
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    public List<Imagen> findAll() {
        return imagenRepository.findAll();
    }

    public Imagen findById(Integer id) {
        return imagenRepository.findById(id).orElse(null);
    }

    //Busca las imagenes asociadas a un producto especifico por su id
    public List<Imagen> findByProductoId(Integer idProducto) {
        return imagenRepository.findByProductoIdProducto(idProducto);
    }

    public Imagen save(Imagen imagen) {
        return imagenRepository.save(imagen);
    }

    public void deleteById(Integer id) {
        //Eliminacion por id de imagen
        imagenRepository.deleteById(id);
    }
}