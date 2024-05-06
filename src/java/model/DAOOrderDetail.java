/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CartItem;
import entity.Order;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WanaW
 */
public class DAOOrderDetail extends DBConnect {

    public void insertOrderDetail(Order order, CartItem cartItem) {
        String sql = "INSERT INTO [dbo].[order_detail]\n"
                + "           ([prod_quantity]\n"
                + "           ,[prod_id]\n"
                + "           ,[order_id]\n"
                + "           ,[price])\n"
                + "     VALUES(?,?,?,?)";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, cartItem.getQuantity());
            stm.setInt(2, cartItem.getProduct().getId());
            stm.setInt(3, order.getId());
            stm.setDouble(4, cartItem.getProduct().getPrice());
            stm.executeUpdate();
            System.out.println("Insert OK");
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetail.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
