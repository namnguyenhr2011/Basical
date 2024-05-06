<%-- 
    Document   : admin
    Created on : Mar 21, 2024, 1:52:22 PM
    Author     : WanaW
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"></jsp:include>
        <div class="bg0 m-t-23 p-b-140">
            <div class="container">
                <h1 style="text-align: center; color: blueviolet">Welcome to Admin Page</h1>
                <div class="flex-w flex-sb-m p-b-0">
                    <div class="flex-w flex-l-m filter-tope-group m-tb-10">
                        <a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" href="managecustomer">Manage Customer</a>
                        <a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" href="manageproduct">Manage Product</a>
                    </div>
                </div>
            <c:if test="${data ne null}" >
                <h1 style="text-align: center; color: blueviolet">Product Manage</h1>
                <br>
                <div class="m-l-25 m-r--38 m-lr-0-xl">
                    <div class="wrap-table-shopping-cart">
                        <a href="manageproduct?service=requestInsert">
                            <button type="button" class="btn btn-secondary">Insert Product</button>
                        </a>
                        <table class="table-shopping-cart">
                            <tr class="table_head">
                                <th class="column-3" style="text-align: center">ID</th>
                                <th class="column-2" style="text-align: center">Product Name</th>
                                <th class="column-3" style="text-align: center">Unit Price</th>
                                <th class="column-3" style="text-align: center">Quantity In Stock</th>
                                <th class="column-3" style="text-align: center">Release Date</th>
                                <th class="column-3" style="text-align: center">Update</th>
                                <th class="column-3" style="text-align: center">Delete</th>
                            </tr>
                            <c:forEach items="${requestScope.data}" var="product">
                                <tr class="table_row" style="height: 120px">
                                    <td class="column-3" style="text-align: center; padding-left: 0px; padding-bottom: 0px">${product.id}</td>

                                    <td class="column-2" style="padding-left: 0px; padding-bottom: 0px">
                                        <img src="images/${product.image}" style="width: 80px; margin-right: 10px" alt="IMG"/>${product.name}

                                    </td>
                                    <td class="column-3" style="text-align: center; padding-left: 0px; padding-bottom: 0px">$${product.price}</td>
                                    <td class="column-3" style="text-align: center; padding-left: 0px; padding-bottom: 0px">${product.quantity}</td>
                                    <td class="column-3" style="text-align: center; padding-left: 0px; padding-bottom: 0px">${product.release_date}</td>
                                    <td class="column-3" style="text-align: center; padding-left: 0px; padding-bottom: 0px">
                                        <a href="manageproduct?service=requestUpdate&productId=${product.id}"">
                                            <button type="button" class="btn btn-outline-dark">Update</button>
                                        </a>
                                    </td>
                                    <td class="column-3" style="text-align: center; padding-left: 0px; padding-bottom: 0px">
                                        <a href="manageproduct?service=requestDelete&productId=${product.id}" onclick="return confirmDelete(${product.id})">
                                            <button type="button" class="btn btn-outline-dark">Delete</button>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <script>
                                function confirmDelete(productId) {
                                    return confirm("Are you sure you want to delete this Product (ID = " + productId + ") ?");
                                }
                            </script>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${user ne null}" >
                <h1 style="text-align: center; color: blueviolet">Customer Manage</h1>
                <br>
                <div class="m-l-25 m-r--38 m-lr-0-xl">
                    <div class="wrap-table-shopping-cart">
                        <table class="table-shopping-cart">
                            <tr class="table_head">
                                <th class="column-3" style="text-align: center">ID</th>
                                <th class="column-3" style="text-align: center">Fullname</th>
                                <th class="column-3" style="text-align: center">Username</th>
                                <th class="column-3" style="text-align: center">Password</th>
                                <th class="column-3" style="text-align: center">Email</th>
                                <th class="column-3" style="text-align: center">Role</th>
                            </tr>
                            <c:forEach items="${requestScope.user}" var="u">
                                <tr class="table_row" style="height: 120px">
                                    <td class="column-3" style="text-align: center; padding-left: 0px; padding-bottom: 0px">${u.getId()}</td>
                                    <td class="column-3" style="text-align: center; padding-left: 0px; padding-bottom: 0px">${u.fullname}</td>
                                    <td class="column-3" style="text-align: center; padding-left: 0px; padding-bottom: 0px">${u.username}</td>
                                    <td class="column-3" style="text-align: center; padding-left: 0px; padding-bottom: 0px">${u.password}</td>
                                    <td class="column-3" style="text-align: center; padding-left: 0px; padding-bottom: 0px">${u.email}</td>
                                    <td class="column-3" style="text-align: center; padding-left: 0px; padding-bottom: 0px">${u.role_id}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${UpdateDone ne null}">
                <h3 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                    ${UpdateDone}
                </h3>
            </c:if>
            <c:if test="${productUpdate ne null}">
                <h1 style="text-align: center; color: blueviolet">Update Product</h1>
                <br>
                <form action="manageproduct" id="updateProduct">
                    <input type="hidden" name="service" value="sendUpdateDetail" />
                    <div class="col-lg-12 table-responsive mb-5">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Product Name</th>
                                    <th>Description</th>
                                    <th>Unit Price</th>
                                    <th>Quantity In Stock</th>
                                    <th>Release Date</th>
                                </tr>
                            </thead>                                       
                            <tbody class="align-middle">
                                <tr>
                                    <td class="align-middle">
                                        <input type="text" name="id" value="${productUpdate.id}" size="1" readonly />
                                    </td>
                                    <td class="align-middle">
                                        <input type="text" name="name" value="${productUpdate.name}" size="35"/>
                                    </td>
                                    <td class="align-middle">
                                        <input type="text" name="description" value="${productUpdate.description}" size="55"/>
                                    </td>
                                    <td class="align-middle">
                                        <input type="number" name="price" value="${productUpdate.price}" style="width: 80px"/>
                                    </td>
                                    <td class="align-middle">
                                        <input type="number" name="quantity" value="${productUpdate.quantity}" style="width: 80px"/>
                                    </td>
                                    <td class="align-middle">
                                        <input type="text" name="release_date" value="${productUpdate.release_date}" size="8"/>
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                    </div>
                    <button class="btn btn-block btn-primary my-3 py-3" 
                            style="transform: translateX(50vw); width: 15%;"
                            onclick="document.getElementById('updateProduct').submit();" >
                        Update
                    </button>
                </form>
            </c:if>
            <c:if test="${InsertDone ne null}">
                <h3 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                    ${InsertDone}
                </h3>
            </c:if>
            <c:if test="${insertProduct ne null}">
                <h1 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                    Insert a new Product 
                </h1>
                <form action="manageproduct" id="insertProduct">
                    <input type="hidden" name="service" value="sendInsertDetail" />
                    <div class="col-lg-12 table-responsive mb-5">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th>Product Name</th>
                                    <th>Unit Price</th>
                                    <th>Quantity In Stock</th>
                                    <th>Description</th>
                                    <th>Image</th>
                                    <th>Brand</th>
                                    <th>Release Date</th>
                                </tr>
                            </thead>                                       
                            <tbody class="align-middle">
                                <tr>
                                    <td class="align-middle">
                                        <input type="text" name="name" size="30"/>
                                    </td>
                                    <td class="align-middle">
                                        <input type="number" name="price" style="width: 80px"/>
                                    </td>
                                    <td class="align-middle">
                                        <input type="number" name="quantity" style="width: 150px"/>
                                    </td>
                                    <td class="align-middle">
                                        <input type="text" name="description" size="45"/>
                                    </td>
                                    <td class="align-middle">
                                        <input type="text" name="image" size="15"/>
                                    </td>
                                    <td>
                                        <select name="category_id">
                                            <c:forEach items="${allBrands}" var="brand">
                                                <option value="${brand.id}">${brand.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td class="align-middle">
                                        <input type="text" name="release_date" size="8"/>
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                    </div>
                    <button class="btn btn-block btn-primary my-3 py-3" 
                            style="transform: translateX(50vw); width: 15%;"
                            onclick="document.getElementById('insertProduct').submit();" >
                        Add
                    </button>
                </form>
            </c:if>
        </div>
    </div>
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
    <script src="js/main.js"></script>
</body>
</html>
