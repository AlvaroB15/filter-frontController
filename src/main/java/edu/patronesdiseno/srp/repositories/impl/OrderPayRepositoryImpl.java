package edu.patronesdiseno.srp.repositories.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.patronesdiseno.srp.models.Order;
import edu.patronesdiseno.srp.repositories.OrderPayRepository;

public class OrderPayRepositoryImpl implements OrderPayRepository {

    private static final String COLLECTION_NAME = "Orders";
    private final MongoCollection<Order> Orders;

    public OrderPayRepositoryImpl(MongoDatabase database) {
        this.Orders = database.getCollection(COLLECTION_NAME, Order.class);
    }

    @Override
    public void create(Order Order) {

    }

    @Override
    public Order find(String id) {
        return null;
    }

    @Override
    public void pay() {

    }
}
