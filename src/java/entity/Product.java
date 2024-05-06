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
public class Product {

    private int id, category_id, quantity;
    private String name, description, image;
    private double price;
    private Date release_date;

    public Product() {
    }

    public Product(int category_id, int quantity, String name, String description, String image, double price, Date release_date) {
        this.category_id = category_id;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.release_date = release_date;
    }

    public Product(int id, int category_id, int quantity, String name, String description, String image, double price, Date release_date) {
        this.id = id;
        this.category_id = category_id;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", category_id=" + category_id + ", quantity=" + quantity + ", name=" + name + ", description=" + description + ", image=" + image + ", price=" + price + ", release_date=" + release_date + '}';
    }

}
