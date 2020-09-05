package edu.patronesdiseno.srp.models.patterns;

import edu.patronesdiseno.srp.config.DBConnectionManager;
import edu.patronesdiseno.srp.controllers.impl.OrderControllerImpl;
import edu.patronesdiseno.srp.controllers.impl.ProductControllerImpl;
import edu.patronesdiseno.srp.repositories.impl.OrderRepositoryImpl;
import edu.patronesdiseno.srp.repositories.impl.ProductRepositoryImplProxy;
import io.javalin.http.Context;

import java.util.Map;

public class Dispatcher {
    private DBConnectionManager manager;
    private ProductControllerImpl productController;
    private OrderControllerImpl orderController;

    public Dispatcher(){
        this.manager = new DBConnectionManager();
        OrderRepositoryImpl orderRepositoryImpl = new OrderRepositoryImpl(this.manager.getDatabase());
        this.orderController = new OrderControllerImpl(orderRepositoryImpl);

        //ProductRepositoryImpl productRepositoryImpl = new ProductRepositoryImpl(this.manager.getDatabase());
        ProductRepositoryImplProxy productRepositoryImpl = new ProductRepositoryImplProxy(this.manager.getDatabase());
        this.productController = new ProductControllerImpl(productRepositoryImpl);

    }

    public void dispatch(Context context){

        String requestType = context.attributeMap().get("jetty-request").toString();

        if(requestType.contains("GET") && context.url().contains("orders")) {
            orderController.findAll(context);
        }else{
            if(requestType.contains("GET") && context.url().contains("order"))
                orderController.find(context);
        }
        if(requestType.contains("POST")) {
            orderController.create(context);
        }
        if(requestType.contains("DELETE")) {
            orderController.delete(context);
        }

    }
}