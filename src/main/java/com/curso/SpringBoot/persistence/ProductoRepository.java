package com.curso.SpringBoot.persistence;

import com.curso.SpringBoot.persistence.crud.ProductoCrudRepository;
import com.curso.SpringBoot.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    public Optional<Producto> getProducto(int idProducto){
        return productoCrudRepository.findById((long) idProducto);
    }

    public Producto saveProducto(Producto producto){
        return  productoCrudRepository.save(producto);
    }

    public void deleteProducto(int id){
        productoCrudRepository.deleteById((long) id);
    }

}
