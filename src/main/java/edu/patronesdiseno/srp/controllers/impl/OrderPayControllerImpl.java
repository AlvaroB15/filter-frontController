package edu.patronesdiseno.srp.controllers.impl;

import edu.patronesdiseno.srp.config.Paths;
import edu.patronesdiseno.srp.controllers.OrderPayController;
import edu.patronesdiseno.srp.models.Order;
import edu.patronesdiseno.srp.models.patterns.*;
import edu.patronesdiseno.srp.repositories.OrderPayRepository;
import io.javalin.http.Context;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

public class OrderPayControllerImpl implements OrderPayController {

    private OrderPayRepository orderPayRepository;

    public OrderPayControllerImpl(OrderPayRepository orderPayRepository){
        this.orderPayRepository = orderPayRepository;
    }

    @Override
    public void create(@NotNull Context context) {
        Order order = context.bodyAsClass(Order.class);
        System.out.println("Order to Pay: " + order);

        //if (Product.getId() != null) {
        //    throw new BadRequestResponse(String.format("Unable to create a new post with existing id: %s", Product));
        //}

        //OrderRepository.create(order);
        context.status(HttpStatus.CREATED_201)
                .header(HttpHeader.LOCATION.name(), Paths.formatPostLocation(order.getId().toString()));
    }

    @Override
    public void find(@NotNull Context context) {

    }

    @Override
    public void pay(@NotNull Context context) {
        System.out.println("Pay controller");

        FilterManager filterManager = new FilterManager(new PasarelaTarget());
        filterManager.setFilter(new StructurePasarelaFilter());
        filterManager.setFilter(new SecurityFilter());

        filterManager.filterRequest(context.body());
    }
}
