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
public class Order {

    private int id;
    private Date created_date;
    private User user;

    public Order() {
    }

    public Order(Date created_date, User user) {
        this.created_date = created_date;
        this.user = user;
    }

    public Order(int id, Date created_date, User user) {
        this.id = id;
        this.created_date = created_date;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", created_date=" + created_date + ", user=" + user + '}';
    }

}
