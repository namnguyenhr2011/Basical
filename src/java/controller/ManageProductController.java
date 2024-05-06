/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Vector;
import model.DAOCategory;
import model.DAOProduct;

/**
 *
 * @author WanaW
 */
@WebServlet(name = "ManageProductController", urlPatterns = {"/manageproduct"})
public class ManageProductController extends HttpServlet {

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
        DAOProduct dao = new DAOProduct();
        Vector<Product> products;
        String service = req.getParameter("service");
        req.setAttribute("manageproduct", "Yes");
        if (service == null) {
            service = "listAll";
        }

        if (service.equals("listAll")) {
            products = dao.getAll("select * from product");
            req.setAttribute("data", products);
            req.setAttribute("showSearchProduct", "Yes");
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        }

        if (service.equals("requestUpdate")) {
            String productId = req.getParameter("productId");
            Product product = (new DAOProduct()).getProductByID(productId);
            req.setAttribute("productUpdate", product);
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        }

        if (service.equals("sendUpdateDetail")) {

            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            double price = Double.parseDouble(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            Date release_date = Date.valueOf(req.getParameter("release_date"));

            Product product = (new DAOProduct()).getProductByID(id);

            //set new value for product
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setRelease_date(release_date);

            (new DAOProduct()).updateProduct(product, id);
            req.setAttribute("UpdateDone", "Update information for Product (ID = " + id + ") done!\nClick Product Manager to see all changes");
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        }

        if (service.equals("requestInsert")) {
            Vector<Category> categorys = (new DAOCategory()).getAll("select * from category");

            req.setAttribute("insertProduct", "insertProduct");
            req.setAttribute("allBrands", categorys);
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        }

        if (service.equals("sendInsertDetail")) {
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            String description = req.getParameter("description");
            String image_url = req.getParameter("image");
            int brandId = Integer.parseInt(req.getParameter("category_id"));
            Date release_date = Date.valueOf(req.getParameter("release_date"));

            Product product = new Product(brandId, quantity, name, description, image_url, price, release_date);
            int gerenatedProductId = (new DAOProduct()).addProduct(product);
            req.setAttribute("InsertDone", "Insert a new Product success\nClick Product Manager to see all changes");
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        }

        if (service.equals("searchByKeywords")) {
            String keywords = req.getParameter("keywords");

            products = (new DAOProduct()).searchProduct(keywords);

            if (products == null || products.isEmpty()) {
                req.setAttribute("notFoundProduct", "Your keywords do not match with any Product Name");
                products = (new DAOProduct()).getAll("select * from product");
            }

            req.setAttribute("data", products);
            req.setAttribute("keywords", keywords);
            req.setAttribute("showSearchProduct", "Yes");
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        }

        if (service.equals("requestDelete")) {
            String productId_raw = req.getParameter("productId");
            int productId = Integer.parseInt(productId_raw);

            int n = (new DAOProduct()).removeProduct(productId);
            if (n == 1) {
                req.setAttribute("deleteDone", "Delete Product (Id = " + productId + ") done!");
            } else {
                req.setAttribute("deleteDone", "Failed to delete Product (Id  = " + productId + ") because this product is asociated with an order.");
            }

            products = (new DAOProduct()).getAll("select * from product");

            req.setAttribute("showSearchProduct", "Yes");
            req.setAttribute("data", products);
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
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
