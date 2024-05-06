/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.BillDetail;
import entity.Order;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WanaW
 */
public class DAOBill extends DBConnect {

    public int insertBill(Order order, User user, String status) {
        ResultSet generatedKeys = null;
        int insertedId = -1; // Giá trị mặc định nếu không có ID được chèn

        String sql = "INSERT INTO [dbo].[bill]\n"
                + "           ([created_date]\n"
                + "           ,[status]\n"
                + "           ,[order_id]\n"
                + "           ,[user_id])\n"
                + "     VALUES(?,?,?,?)";
        try {
            PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setDate(1, order.getCreated_date());
            stm.setString(2, status);
            stm.setInt(3, order.getId());
            stm.setInt(4, user.getId());
            int affectedRows = stm.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = stm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    insertedId = generatedKeys.getInt(1); // Lấy giá trị ID của bản ghi vừa chèn
                }
            }

            System.out.println("Insert OK");
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return insertedId;
    }

    public Vector<BillDetail> showBillDetail(int billId) {
        Vector<BillDetail> billDetails = new Vector<>();

        String sql = "SELECT b.id, u.fullname, b.created_date, p.[name], p.[image],\n"
                + "		od.prod_quantity, p.price, (p.price * od.prod_quantity) AS sub_total\n"
                + "FROM bill b\n"
                + "JOIN [user] u ON u.id = b.[user_id]\n"
                + "JOIN order_detail od ON od.order_id = b.order_id\n"
                + "JOIN product p ON p.id = b.id"
                + "WHERE b.id = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, billId);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                BillDetail billDetail = new BillDetail();
                billDetail.setId(rs.getInt("id"));
                billDetail.setUsername(rs.getString("username"));
                billDetail.setCreated_date(rs.getDate("created_date"));
                billDetail.setProd_name(rs.getString("prod_name"));
                billDetail.setImage(rs.getString("image"));
                billDetail.setProd_quantity(rs.getInt("prod_quantity"));
                billDetail.setPrice(rs.getDouble("price"));
                billDetail.setSub_total(rs.getDouble("sub_total"));

                billDetails.add(billDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return billDetails;
    }
}
