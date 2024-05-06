/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Category;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WanaW
 */
public class DAOCategory extends DBConnect {

    public int addCategory(Category obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[category]\n"
                + "           ,[name])\n"
                + "     VALUES(?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getName());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Category> getAll(String sql) {
        Vector<Category> vector = new Vector<>();
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int categoryID = rs.getInt(1);
                String categoryName = rs.getString(2);
                Category c = new Category(categoryID, categoryName);
                vector.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int removeCategory(int id) {
        int n = 0;
        String sql = "delete from category where id = '" + id + "'";
        try {
            Statement st = conn.createStatement();
            n = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        DAOCategory dao = new DAOCategory();

        Vector<Category> vector = dao.getAll("select * from category");
        for (Category category : vector) {
            System.out.println(category);
        }

//        int n = dao.addCategory(new Category(10, "Coat"));
//        if (n > 0) {
//            System.out.println("inseted");
//        }
//        int n = dao.removeCategory(10);
//        if (n > 0) {
//            System.out.println("deleted");
//        }
    }
}
