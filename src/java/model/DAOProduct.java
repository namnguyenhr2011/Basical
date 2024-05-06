/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Product;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WanaW
 */
public class DAOProduct extends DBConnect {

    public int addProduct(Product obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[product]\n"
                + "           ([name]\n"
                + "           ,[category_id]\n"
                + "           ,[description]\n"
                + "           ,[price]\n"
                + "           ,[quantity]\n"
                + "           ,[image]\n"
                + "           ,[release_date])\n"
                + "     VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getName());
            pre.setInt(2, obj.getCategory_id());
            pre.setString(3, obj.getDescription());
            pre.setDouble(4, obj.getPrice());
            pre.setInt(5, obj.getQuantity());
            pre.setString(6, obj.getImage());
            pre.setDate(7, obj.getRelease_date());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void updateProduct(Product obj, String pid) {
        String sql = "UPDATE [dbo].[product]\n"
                + "   SET [name] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[release_date] = ?\n"
                + " WHERE [id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getName());
            pre.setString(2, obj.getDescription());
            pre.setDouble(3, obj.getPrice());
            pre.setInt(4, obj.getQuantity());
            pre.setDate(5, obj.getRelease_date());
            pre.setString(6, pid);
            pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<Product> getProductByCategory(String cid) {
        Vector<Product> vector = new Vector<>();
        String sql = "SELECT * FROM product\n"
                + "WHERE category_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int prodId = rs.getInt(1);
                String prodName = rs.getString(2);
                int categoryId = rs.getInt(3);
                String description = rs.getString(4);
                double price = rs.getDouble(5);
                int stockQuantity = rs.getInt(6);
                String image = rs.getString(7);
                Date release_date = rs.getDate(8);
                Product p = new Product(prodId, categoryId, stockQuantity, prodName, description, image, price, release_date);
                vector.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Product getProductByID(String id) {
        String sql = "SELECT * FROM product\n"
                + "WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int prodId = rs.getInt(1);
                String prodName = rs.getString(2);
                int categoryId = rs.getInt(3);
                String description = rs.getString(4);
                double price = rs.getDouble(5);
                int stockQuantity = rs.getInt(6);
                String image = rs.getString(7);
                Date release_date = rs.getDate(8);
                return new Product(prodId, categoryId, stockQuantity, prodName, description, image, price, release_date);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Vector<Product> getAll(String sql) {
        Vector<Product> vector = new Vector<>();
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int prodId = rs.getInt(1);
                String prodName = rs.getString(2);
                int categoryId = rs.getInt(3);
                String description = rs.getString(4);
                double price = rs.getDouble(5);
                int stockQuantity = rs.getInt(6);
                String image = rs.getString(7);
                Date release_date = rs.getDate(8);
                Product p = new Product(prodId, categoryId, stockQuantity, prodName, description, image, price, release_date);
                vector.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Product> searchProduct(String name) {
        Vector<Product> vector = new Vector<Product>();
        String sql = "select * from product where name like '%" + name + "%'";
        vector = getAll(sql);
        return vector;
    }

    public int removeProduct(int id) {
        int n = 0;
        String sql = "delete from product where id = '" + id + "' and "
                + " ('" + id + "' not in (select distinct id from order_detail))";
        try {
            Statement st = conn.createStatement();
            n = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();

        Vector<Product> vector = dao.getProductByCategory("1");
        for (Product products : vector) {
            System.out.println(products);
        }

//        int n = dao.addProduct(new Products(21, 1,
//                10, "a", "b", "c", 12.34));
//        if (n > 0) {
//            System.out.println("inseted");
//        }
//        int n = dao.removeProduct(1);
//        if (n > 0) {
//            System.out.println("deleted");
//        }
    }
}
