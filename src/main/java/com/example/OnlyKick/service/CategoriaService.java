package com.example.OnlyKick.service;

import com.example.OnlyKick.model.Categoria;
import com.example.OnlyKick.model.Producto;
import com.example.OnlyKick.repository.CategoriaRepository;
import com.example.OnlyKick.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("null")
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deleteById(Integer id) {
        // Antes de eliminar la categoria, desvinculamos los productos asociados
        List<Producto> productos = productoRepository.findByCategoriaIdCategoria(id);
        for (Producto producto : productos) {
            producto.setCategoria(null);
            productoRepository.save(producto);
        }
        categoriaRepository.deleteById(id);
    }
}