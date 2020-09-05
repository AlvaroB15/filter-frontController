package edu.patronesdiseno.srp.models.patterns;

public class StructurePasarelaFilter implements Filter {
    @Override
    public String execute(String request) {
        System.out.println("Checking request structure: " + request);
        // N transformaciones
        //request += "&validado"
        return request;
    }
}
