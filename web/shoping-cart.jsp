<%-- 
    Document   : shoping-cart
    Created on : Mar 8, 2024, 12:47:23 PM
    Author     : WanaW
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.CartItem, entity.Product, java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"></jsp:include>

        <!-- breadcrumb -->
        <div class="container">
            <div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
                <a href="product" class="stext-109 cl8 hov-cl1 trans-04">
                    Product
                    <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
                </a>

                <span class="stext-109 cl4">
                    Shopping Cart
                </span>
            </div>
        </div>


        <!-- Shoping Cart -->
    <c:if test="${showBill eq null}">
        <form class="bg0 p-t-75 p-b-85" action="cart" id="add-cart">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
                        <div class="m-l-25 m-r--38 m-lr-0-xl">
                            <div class="wrap-table-shopping-cart">
                                <table class="table-shopping-cart">
                                    <tr class="table_head">
                                        <th class="column-1">Product</th>
                                        <th class="column-2"></th>
                                        <th class="column-3">Price</th>
                                        <th class="column-4">Quantity</th>
                                        <th class="column-5">Total</th>
                                    </tr>
                                    <%
                                            double total_raw = 0;
                                            java.util.Enumeration enms = session.getAttributeNames();

                                            while (enms.hasMoreElements()) {

                                                String id = enms.nextElement().toString();

                                                if (!id.equals("users") && !id.equals("fullname") && !id.equals("numberProductsInCart")) {
                                                    CartItem cartItem = (CartItem) session.getAttribute(id); 
                                                    Product p = cartItem.getProduct();
                                                    int quantity = cartItem.getQuantity();
                                    %>
                                    <input type="hidden" name="service" value="update"/>
                                    <tr class="table_row">
                                        <td class="column-1">
                                            <a href="cart?service=removeItem&id=<%= id%>">
                                                <div class="how-itemcart1">
                                                    <img src="images/<%= p.getImage()%>" alt="IMG">
                                                </div>
                                            </a>
                                        </td>
                                        <td class="column-2"><%= p.getName()%></td>
                                        <td class="column-3">$<%= p.getPrice()%></td>
                                        <td class="column-4">
                                            <div class="wrap-num-product flex-w m-l-auto m-r-0">
                                                <button onclick="downNumber()">
                                                <div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m" >
                                                    <i class="fs-16 zmdi zmdi-minus" ></i>
                                                </div>
                                                </button>

                                                <input class="mtext-104 cl3 txt-center num-product" type="number"
                                                       name="p<%= id%>" id="number" form="add-cart" value="<%= quantity%>">

                                                <button onclick="upNumber()">
                                                <div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
                                                    <i class="fs-16 zmdi zmdi-plus"></i>
                                                </div>
                                                </button>
                                            </div>
                                        </td>
                                        <td class="column-5">$<%= Math.round((p.getPrice() * quantity) * 10) / 10.0 %></td>

                                    </tr>
                                    <%      total_raw += (p.getPrice() * quantity);
                                            }
                                        }
                                    DecimalFormat df = new DecimalFormat("#.0");
                                    String total = df.format(total_raw);
                                    %>

                                </table>
                            </div>

                            <div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">
                                <button onclick="document.getElementById('add-cart').submit();">
                                    <div class="flex-w flex-m m-r-20 m-tb-5">
                                        <div class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5">
                                            Update
                                        </div>
                                    </div>
                                </button>
                                <a href="cart?service=removeAll">
                                    <div
                                        class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
                                        Remove All
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
                        <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                            <h4 class="mtext-109 cl2 p-b-30">
                                Cart Totals
                            </h4>
                            <div class="flex-w flex-t bor12 p-t-15 p-b-30">
                                <div class="size-208 w-full-ssm">
                                    <span class="stext-110 cl2">
                                        Shipping:
                                    </span>
                                </div>

                                <div class="size-209 p-r-18 p-r-0-sm w-full-ssm">
                                    <p class="stext-111 cl6 p-t-2">
                                        There are no shipping methods available. Please double check your address, or
                                        contact us if you need any help.
                                    </p>
                                </div>
                            </div>

                            <div class="flex-w flex-t p-t-27 p-b-33">
                                <div class="size-208">
                                    <span class="mtext-101 cl2">
                                        Total:
                                    </span>
                                </div>

                                <div class="size-209 p-t-1">
                                    <span class="mtext-110 cl2">
                                        $<%= total%>
                                    </span>
                                </div>
                            </div>
                            <a href="cart?service=checkout">
                                <button class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
                                    Proceed to Checkout
                                </button>
                            </a>
                        </div>
                        <c:if test="${checkOutDone ne null}">
                            <div class="card-header bg-secondary border-0 text-center">
                                Checkout Done! See your <a href="cart?service=showBill&billId=${BillId}">Bill? (click here)</a>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </form>
    </c:if>

    <jsp:include page="footer.jsp"></jsp:include>

    <!--===============================================================================================-->
    <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/animsition/js/animsition.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/bootstrap/js/popper.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/select2/select2.min.js"></script>
    <script>
                                    $(".js-select2").each(function () {
                                        $(this).select2({
                                            minimumResultsForSearch: 20,
                                            dropdownParent: $(this).next('.dropDownSelect2')
                                        });
                                    })
    </script>
    <!--===============================================================================================-->
    <script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script>
                                    $('.js-pscroll').each(function () {
                                        $(this).css('position', 'relative');
                                        $(this).css('overflow', 'hidden');
                                        var ps = new PerfectScrollbar(this, {
                                            wheelSpeed: 1,
                                            scrollingThreshold: 1000,
                                            wheelPropagation: false,
                                        });

                                        $(window).on('resize', function () {
                                            ps.update();
                                        })
                                    });
    </script>
    <!--===============================================================================================-->
    <script>
        function upNumber() {
            var numberField = document.getElementById("number");
            numberField.value = parseInt(numberField.value) + 1;
        }
    </script>
    <script>
        function downNumber() {
            var numberField = document.getElementById("number");
            numberField.value = parseInt(numberField.value) - 1;
        }
    </script>
    <script src="js/main.js"></script>

</body>
</html>
