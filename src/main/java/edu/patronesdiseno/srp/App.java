package edu.patronesdiseno.srp;

import edu.patronesdiseno.srp.controllers.impl.OrderPayControllerImpl;
import edu.patronesdiseno.srp.models.patterns.FrontController;
import edu.patronesdiseno.srp.repositories.impl.*;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;

import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;
import edu.patronesdiseno.srp.config.DBConnectionManager;
import edu.patronesdiseno.srp.controllers.impl.CustomerControllerImpl;
import edu.patronesdiseno.srp.controllers.impl.OrderControllerImpl;
import edu.patronesdiseno.srp.controllers.impl.ProductControllerImpl;

import java.util.Map;

public class App {

    private final DBConnectionManager manager;
    private final CustomerControllerImpl customerController;
    private final OrderControllerImpl orderController;
    private final ProductControllerImpl productController;
    private final OrderPayControllerImpl orderPayController;

    public App() {
        this.manager = new DBConnectionManager();
        
        CustomerRepositoryImpl customerRepositoryImpl = new CustomerRepositoryImpl(this.manager.getDatabase());
        this.customerController = new CustomerControllerImpl(customerRepositoryImpl);

        OrderRepositoryImpl orderRepositoryImpl = new OrderRepositoryImpl(this.manager.getDatabase());
        this.orderController = new OrderControllerImpl(orderRepositoryImpl);

        //ProductRepositoryImpl productRepositoryImpl = new ProductRepositoryImpl(this.manager.getDatabase());
        ProductRepositoryImplProxy productRepositoryImpl = new ProductRepositoryImplProxy(this.manager.getDatabase()); 
        this.productController = new ProductControllerImpl(productRepositoryImpl);

        OrderPayRepositoryImpl orderPayRepositoryImpl = new OrderPayRepositoryImpl(this.manager.getDatabase());
        this.orderPayController = new OrderPayControllerImpl(orderPayRepositoryImpl);
    }

    public static void main(String[] args) {
        new App().startup();
    }

    public void startup() {

        Info applicationInfo = new Info()
            .version("1.0")
            .description("Demo API");
        OpenApiOptions openApi = new OpenApiOptions(applicationInfo)
            .path("/api")
            .swagger(new SwaggerOptions("/api-ui")); // endpoint for swagger-ui
        Javalin server = Javalin.create(
            config -> {
                config.registerPlugin(new OpenApiPlugin(openApi));
            }
        ).start(7000);

        server.get("/hello", ctx -> ctx.html("Hello, Javalin!"));

        server.get("api/customer/:id", this.customerController::find);
        server.delete("api/customer/:id", this.customerController::delete);
        server.get("api/customers", this.customerController::findAll);
        server.post("api/customer", this.customerController::create);

        server.get("api/product/:id", this.productController::find);
        server.delete("api/product/:id", this.productController::delete);
        server.get("api/products", this.productController::findAll);
        server.post("api/product", this.productController::create);


        // front controller
        Handler validateOrderHandler = context -> {
            FrontController frontController = new FrontController();
            frontController.dispatchRequest(context);
        };
        server.get("api/order/:id", validateOrderHandler);
        server.delete("api/order/:id", validateOrderHandler);
        server.get("api/orders", validateOrderHandler);
        server.post("api/order", validateOrderHandler);


        // intercepting filter
        server.post("api/pay/order/", this.orderPayController::pay);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            this.manager.closeDatabase();
            server.stop();
        }));


    }
}