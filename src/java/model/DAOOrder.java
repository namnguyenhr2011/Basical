/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Order;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WanaW
 */
public class DAOOrder extends DBConnect {

    public int insertOrder(Order order, User user) {
        int generatedId = -1; // Giá trị mặc định nếu không có id được tạo
        String sql = "INSERT INTO [dbo].[order]\n"
                + "           ([created_date]\n"
                + "           ,[user_id])\n"
                + "     VALUES(?,?)";
        try {
            PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setDate(1, order.getCreated_date());
            stm.setInt(2, user.getId());
            stm.executeUpdate();

            // Lấy kết quả id được tạo ra
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
            System.out.println("Insert OK");
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return generatedId;
    }

    public Order getOrdersById(int orderId) {
        String sql = "SELECT * FROM [order] WHERE id = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, orderId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id"), user_id = rs.getInt("user_id");
                User user = (new DAOUser()).getUserById(user_id);
                java.sql.Date created_date = rs.getDate("created_date");

                return new Order(id, created_date, user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        Order order = new Order();
        java.util.Date date = new java.util.Date();
        DAOOrder dao = new DAOOrder();
        dao.getOrdersById(2);
    }
}
