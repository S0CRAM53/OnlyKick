package com.example.OnlyKick.repository;

import com.example.OnlyKick.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
    
    // Metodo para buscar el stock de una variante especifica de un producto
    Optional<Inventario> findByProductoIdProductoAndTallaIdTallaAndColorIdColor(Integer idProducto, Integer idTalla, Integer idColor);

    //Metodo para encontrar todas las variantes de un producto
    List<Inventario> findByProductoIdProducto(Integer idProducto);
}