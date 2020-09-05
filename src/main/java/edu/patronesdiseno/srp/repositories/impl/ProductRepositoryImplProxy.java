package edu.patronesdiseno.srp.repositories.impl;

import java.util.List;

import com.mongodb.client.MongoDatabase;

import edu.patronesdiseno.srp.models.Product;
import edu.patronesdiseno.srp.repositories.ProductRepository;

public class ProductRepositoryImplProxy implements ProductRepository {

    private ProductRepositoryImpl productRepoImpl;

    public ProductRepositoryImplProxy(MongoDatabase database) {
        this.productRepoImpl = new ProductRepositoryImpl(database);
    }

    @Override
    public void create(Product product) {
        System.out.println("Proxy create product ");
        this.productRepoImpl.create(product);
        System.out.println("Proxy create product END");
    }

    @Override
    public void delete(String id) {
        System.out.println("Proxy delete product ");
        this.productRepoImpl.delete(id);
        System.out.println("Proxy delete product END");
    }

    @Override
    public Product find(String id) {
        System.out.println("Proxy find product ");
        return this.productRepoImpl.find(id);
    }

    @Override
    public List<Product> findAll() {
        System.out.println("Proxy find all products ");
        return this.productRepoImpl.findAll();
    }

    @Override
    public Product update(Product post, String id) {
        System.out.println("Proxy update products ");
        return this.productRepoImpl.update(post, id);
    }
}