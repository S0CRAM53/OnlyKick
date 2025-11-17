package com.example.OnlyKick.repository;

import com.example.OnlyKick.model.ProductosVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductosVentaRepository extends JpaRepository<ProductosVenta, Integer> {
    
    //Metodo para ver todos los producto asociados a una venta
    List<ProductosVenta> findByVentaIdVenta(Integer idVenta);
}