package edu.patronesdiseno.srp.models.interfaces;

import edu.patronesdiseno.srp.models.*;

public abstract class IOrderItem {
    //Order order = null;
    //Product product = null;
    protected String idProduct = null;
    protected Integer quantity = null;
    protected Double price = null;

    public IOrderItem(){}

    public abstract Double calculatePrice();

    public abstract Order getOrder();

    public abstract void setOrder(Order order);

    //public Product getProduct();

    public abstract String getIdProduct();

    public abstract Integer getQuantity();

    //public void setQuantity();

    public abstract Double getPrice();

    public String toString(){
        return idProduct;
    }

}