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
public class Bill {

    private int id;
    private Date created_date;
    private String status;
    private Order order;
    private User user;

    public Bill() {
    }

    public Bill(int id, Date created_date, String status, Order orders, User users) {
        this.id = id;
        this.created_date = created_date;
        this.status = status;
        this.order = orders;
        this.user = users;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Bill{" + "id=" + id + ", created_date=" + created_date + ", status=" + status + ", order=" + order + ", user=" + user + '}';
    }

}
