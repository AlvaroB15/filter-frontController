package edu.patronesdiseno.srp.repositories;

import edu.patronesdiseno.srp.models.Order;

public interface OrderPayRepository {

    void create(Order Order);

    Order find(String id);

    void pay();
}
