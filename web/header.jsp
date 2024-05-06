<%-- 
    Document   : header
    Created on : Mar 18, 2024, 9:54:08 PM
    Author     : WanaW
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.User, entity.CartItem, entity.Product, java.text.DecimalFormat" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BASICAL</title>
    <link rel="icon" type="image/png" href="images/icons/logo.png" />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/linearicons-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/MagnificPopup/magnific-popup.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body class="animsition">

    <!-- Header -->
    <header class="header-v4">
        <!-- Header desktop -->
        <div class="container-menu-desktop">
            <!-- Topbar -->
            <div class="top-bar">
                <div class="content-topbar flex-sb-m h-full container">
                    <div class="left-top-bar">
                        Nguyen Van Nam | HE176654
                    </div>

                    <div class="right-top-bar flex-w h-full">
                        <c:if test="${sessionScope.users != null}">
                            <a href="#" class="flex-c-m trans-04 p-lr-25">
                                Welcome ${users.username}
                            </a>
                            <a href="logout" class="flex-c-m trans-04 p-lr-25">
                                Logout
                            </a>
                        </c:if>
                        <c:if test="${sessionScope.users == null}">
                            <a href="login.jsp" class="flex-c-m trans-04 p-lr-25">
                                Login
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="wrap-menu-desktop how-shadow1">
                <nav class="limiter-menu-desktop container">

                    <!-- Logo desktop -->
                    <a href="home" class="logo">
                        <img src="images/icons/logo.png" alt="IMG-LOGO">
                    </a>

                    <!-- Menu desktop -->
                    <div class="menu-desktop">
                        <ul class="main-menu">
                            <li>
                                <a href="home">Home</a>
                            </li>

                            <li>
                                <a href="product">Product</a>
                            </li>

                            <li>
                                <a href="about">About</a>
                            </li>

                            <li>
                                <a href="contact">Contact</a>
                            </li>

                            <c:if test="${users.role_id == 1}">
                                <li>
                                    <a href="admin.jsp">Admin Manager</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>

                    <!-- Icon header -->
                    <%
                        int numberProductsInCart = 0;
                        java.util.Enumeration ens = session.getAttributeNames();
                        while (ens.hasMoreElements()) {
                            String id = ens.nextElement().toString();
                            if (!id.equals("users") && !id.equals("fullname") && !id.equals("numberProductsInCart")) {
                                CartItem cartItem = (CartItem) session.getAttribute(id); 
                                Product product = cartItem.getProduct();
                                int quantity = cartItem.getQuantity();
                                numberProductsInCart += quantity;
                            }
                        }
                        session.setAttribute("numberProductsInCart", numberProductsInCart);
                    %>
                    <div class="wrap-icon-header flex-w flex-r-m">
                        <div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti" data-notify="${numberProductsInCart}">
                            <a href="shoping-cart.jsp" style="color: black"><i class="zmdi zmdi-shopping-cart"></i></a>
                        </div>
                    </div>
                </nav>
            </div>
        </div>

    </header>