package com.example.OnlyKick.repository;

import com.example.OnlyKick.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Integer> {
    
    // Metodo para buscar todas las imagenes de un producto
    List<Imagen> findByProductoIdProducto(Integer idProducto);
}