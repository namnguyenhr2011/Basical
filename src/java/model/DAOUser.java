/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.User;
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
public class DAOUser extends DBConnect {

    public int addUser(User obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[user]\n"
                + "           ([fullname]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[email]\n"
                + "           ,[role_id])\n"
                + "     VALUES(?,?,?,?,2)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getFullname());
            pre.setString(2, obj.getUsername());
            pre.setString(3, obj.getPassword());
            pre.setString(4, obj.getEmail());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public User login(String user, String pass) {
        String sql = "select * from [user] where username = ? and password = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt(1);
                String fullName = rs.getString(2);
                String userName = rs.getString(3);
                String passWord = rs.getString(4);
                String email = rs.getString(5);
                int role = rs.getInt(6);
                return new User(userId, fullName, userName, passWord, email, role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User checkUserExist(String username) {
        String sql = "select * from [user] where username = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt(1);
                String fullName = rs.getString(2);
                String userName = rs.getString(3);
                String passWord = rs.getString(4);
                String email = rs.getString(5);
                int role = rs.getInt(6);
                return new User(userId, fullName, userName, passWord, email, role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Vector<User> getAll(String sql) {
        Vector<User> vector = new Vector<>();
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int userId = rs.getInt(1);
                String fullName = rs.getString(2);
                String userName = rs.getString(3);
                String passWord = rs.getString(4);
                String email = rs.getString(5);
                int role = rs.getInt(6);
                User u = new User(userId, fullName, userName, passWord, email, role);
                vector.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;

    }

    public User getUserById(int userId) {
        ResultSet rs = null;
        String sql = "SELECT * FROM [user] WHERE [id] = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, userId);
            rs = stm.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setFullname(rs.getString("fullname"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setRole_id(rs.getInt("role_id"));
                System.out.println(u);
                return u;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        DAOUser dao = new DAOUser();
        User u = dao.getUserById(1);
        

//        int n = dao.addUser(new User("abc", "abc", "abc", "abc@gmail.com", 2));
//        if (n > 0) {
//            System.out.println("inserted");
//        }

    }
}
