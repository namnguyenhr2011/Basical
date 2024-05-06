/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author WanaW
 */
public class BillDetail {

    private int id, prod_quantity;
    private String username, prod_name, image;
    private Date created_date;
    private double price, sub_total;

    public BillDetail() {
    }

    public BillDetail(int id, int prod_quantity, String username, String prod_name, String image, Date created_date, double price, double subTotal) {
        this.id = id;
        this.prod_quantity = prod_quantity;
        this.username = username;
        this.prod_name = prod_name;
        this.image = image;
        this.created_date = created_date;
        this.price = price;
        this.sub_total = sub_total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProd_quantity() {
        return prod_quantity;
    }

    public void setProd_quantity(int prod_quantity) {
        this.prod_quantity = prod_quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    @Override
    public String toString() {
        return "BillDetail{" + "id=" + id + ", prod_quantity=" + prod_quantity + ", username=" + username + ", prod_name=" + prod_name + ", image=" + image + ", created_date=" + created_date + ", price=" + price + ", sub_total=" + sub_total + '}';
    }

}
