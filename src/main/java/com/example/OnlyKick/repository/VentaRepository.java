package com.example.OnlyKick.repository;

import com.example.OnlyKick.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    
    //Metodo para ver el historial de compras de un usuario
    List<Venta> findByUsuarioIdUsuario(Integer idUsuario);

    //Metodo para buscar ventas por direccion
    List<Venta> findByDireccionIdDireccion(Integer idDireccion);

    //Metodo para buscar ventas por estado
    List<Venta> findByEstadoVentaIdEstado(Integer idEstado);

    //Metodo para buscar ventas por metodo de pago
    List<Venta> findByMetodoPagoIdMetodoPago(Integer idMetodoPago);

    //Metodo para buscar ventas por metodo de envio
    List<Venta> findByMetodoEnvioIdMetodoEnvio(Integer idMetodoEnvio);
}