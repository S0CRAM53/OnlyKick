package com.example.OnlyKick.repository;

import com.example.OnlyKick.model.ProductosVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductosVentaRepository extends JpaRepository<ProductosVenta, Integer> {
    
    //Metodo para ver todos los producto asociados a una venta
    List<ProductosVenta> findByVentaIdVenta(Integer idVenta);

    //Metodo para encontrar todas las ventas pasadas de una talla especifica
    List<ProductosVenta> findByTallaIdTalla(Integer idTalla);

    //Metodo para encontrar todas las ventas pasadas de un color especifico
    List<ProductosVenta> findByColorIdColor(Integer idColor);

    //Metodo para encontrar todas las ventas pasadas de un producto epecifico
    List<ProductosVenta> findByProductoIdProducto(Integer idProducto);
}