package edu.patronesdiseno.srp.models.patterns;

import edu.patronesdiseno.srp.models.Order;
import edu.patronesdiseno.srp.models.interfaces.ITransporte;

public class FastOrder extends Order {

    //private ITransporte moto = new Moto(123213.0, 331.0);

    @Override
    public Double calculaTiempoLlegada(){
        //return transporte.calculaTiempo();
        return 0.0;
    }
}
