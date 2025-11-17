package com.example.OnlyKick.repository;

import com.example.OnlyKick.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
    //Metodo para filtrar productos por categoria y marca
    List<Producto> findByCategoriaIdCategoria(Integer idCategoria);
    List<Producto> findByMarcaIdMarca(Integer idMarca);
}