package com.example.OnlyKick.service;

import com.example.OnlyKick.model.Material;
import com.example.OnlyKick.model.Producto;
import com.example.OnlyKick.repository.MaterialRepository;
import com.example.OnlyKick.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("null")
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public Material findById(Integer id) {
        return materialRepository.findById(id).orElse(null);
    }

    public Material save(Material material) {
        return materialRepository.save(material);
    }

    //Eliminacion por cascada de materiales, desvinculando los productos asociados
    public void deleteById(Integer id) {
        //Busca todos los productos que usan este material
        List<Producto> productos = productoRepository.findByMaterialIdMaterial(id);
        
        //Desvincula el material de los productos asociados
        for (Producto producto : productos) {
            producto.setMaterial(null);
            productoRepository.save(producto);
        }

        materialRepository.deleteById(id);
    }
}