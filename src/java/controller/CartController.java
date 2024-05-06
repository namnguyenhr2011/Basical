/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.BillDetail;
import entity.CartItem;
import entity.Order;
import entity.Product;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Enumeration;
import java.util.Vector;
import model.DAOBill;
import model.DAOOrderDetail;
import model.DAOOrder;
import model.DAOProduct;

/**
 *
 * @author WanaW
 */
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession(true);
        String service = req.getParameter("service");

        User users = (User) session.getAttribute("users");
        if (users == null) {
            req.setAttribute("error", "Please log in before adding products to cart!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            if (service == null) {
                resp.sendRedirect("shoping-cart.jsp");
            } else {

                if (service.equals("showCart")) {
                    resp.sendRedirect("shoping-cart.jsp");
                }

                if (service.equals("addToCart")) {
                    String pid = req.getParameter("prod_id");
                    Product products = (new DAOProduct()).getProductByID(pid);
                    if (session.getAttribute(pid.toString()) == null) {
                        CartItem cartItem = new CartItem(products, 1);
                        session.setAttribute(pid.toString(), cartItem);
                    } else {
                        int newQuantity = ((CartItem) session.getAttribute(pid.toString())).getQuantity() + 1;
                        CartItem cartItem = new CartItem(products, newQuantity);
                        session.setAttribute(pid.toString(), cartItem);
                    }
                    resp.sendRedirect("product");
                }

                if (service.equals("removeItem")) {
                    String id = req.getParameter("id");
                    session.removeAttribute(id);
                    resp.sendRedirect("shoping-cart.jsp");
                }

                if (service.equals("removeAll")) {
                    Enumeration en = session.getAttributeNames();
                    while (en.hasMoreElements()) {
                        String id = en.nextElement().toString();
                        if (!id.equals("users") && !id.equals("fullname") && !id.equals("numberProductsInCart")) {
                            session.removeAttribute(id);
                        }
                    }
                    resp.sendRedirect("shoping-cart.jsp");

                }

                if (service.equals("update")) {
                    Enumeration em = session.getAttributeNames();

                    while (em.hasMoreElements()) {
                        String id = em.nextElement().toString(); //get key
                        if (!id.equals("users") && !id.equals("fullname") && !id.equals("numberProductsInCart")) {
                            int quantity = Integer.parseInt(req.getParameter("p" + id));
                            CartItem cartItem = (CartItem) session.getAttribute(id);
                            cartItem.setQuantity(quantity);
                            session.setAttribute(id, cartItem);
                        }
                    }
                    resp.sendRedirect("shoping-cart.jsp");
                }

                if (service.equals("checkout")) {
                    java.util.Date date = new java.util.Date();
                    Date currentDate = new Date(date.getTime());
                    //insert order
                    Order order = new Order(currentDate, users);
                    int orderId = (new DAOOrder()).insertOrder(order, users);
                    //insert order detail
                    Enumeration em = session.getAttributeNames();

                    while (em.hasMoreElements()) {
                        String id = em.nextElement().toString(); //get key

                        if (!id.equals("users") && !id.equals("fullname") && !id.equals("numberProductsInCart")) {
                            CartItem cartItem = (CartItem) session.getAttribute(id);
                            (new DAOOrderDetail()).insertOrderDetail((new DAOOrder()).getOrdersById(orderId), cartItem);
                        }
                    }

                    //insert bill
                    int billId = (new DAOBill()).insertBill((new DAOOrder()).getOrdersById(orderId), users, "wait");

                    //remove all products in cart
                    Enumeration en = session.getAttributeNames();
                    while (en.hasMoreElements()) {
                        String id = en.nextElement().toString();
                        if (!id.equals("users") && !id.equals("fullname") && !id.equals("numberProductsInCart")) {
                            session.removeAttribute(id);
                        }
                    }

                    req.setAttribute("checkOutDone", "checkOutDone");
                    req.setAttribute("BillId", billId);
                    req.getRequestDispatcher("shoping-cart.jsp").forward(req, resp);
                }

                if (service.equals("showBill")) {
                    int billId = Integer.parseInt(req.getParameter("billId"));

                    Vector<BillDetail> billDetails = (new DAOBill()).showBillDetail(billId);
                    req.setAttribute("billDetails", billDetails);
                    req.setAttribute("showBill", "showBill");
                    req.getRequestDispatcher("shoping-cart.jsp").forward(req, resp);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
