/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author WanaW
 */
public class OrderDetail {

    private int prod_quantity;
    private Order order;
    private Product product;
    private double price;

    public OrderDetail() {
    }

    public OrderDetail(int prod_quantity, Order order, Product product, double price) {
        this.prod_quantity = prod_quantity;
        this.order = order;
        this.product = product;
        this.price = price;
    }

    public int getProd_quantity() {
        return prod_quantity;
    }

    public void setProd_quantity(int prod_quantity) {
        this.prod_quantity = prod_quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "prod_quantity=" + prod_quantity + ", order=" + order + ", product=" + product + ", price=" + price + '}';
    }

}
