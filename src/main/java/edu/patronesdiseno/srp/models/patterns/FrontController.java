package edu.patronesdiseno.srp.models.patterns;

import edu.patronesdiseno.srp.controllers.impl.OrderControllerImpl;
import io.javalin.http.Context;

public class FrontController {

    private Dispatcher dispatcher;

    public FrontController(){
        dispatcher = new Dispatcher();
    }

    private boolean isAuthenticUser(String request){
        System.out.println(request);
        if(request.contains("STRONG_TOKEN")){
            System.out.println("User is authenticated successfully.");
            return true;
        }
        return false;
    }

    private void trackRequest(String request){
        System.out.println("Track request -> " + request);
    }

    public void dispatchRequest(Context context){
        //log each request
        trackRequest(context.queryString());

        //authenticate the user
        if(isAuthenticUser(context.queryString())){
            dispatcher.dispatch(context);
        }
    }
}
