package com.curso.SpringBoot.persistence;

import com.curso.SpringBoot.domain.Purchase;
import com.curso.SpringBoot.domain.repository.PurchaseRepository;
import com.curso.SpringBoot.persistence.crud.CompraCrudRepository;
import com.curso.SpringBoot.persistence.entity.Compra;
import com.curso.SpringBoot.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements com.curso.SpringBoot.domain.repository.PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId).map(
          compras -> purchaseMapper.toPurchases(compras)
        );
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }
}
