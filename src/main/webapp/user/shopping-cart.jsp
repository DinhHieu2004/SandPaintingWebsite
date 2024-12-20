<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %><!DOCTYPE html>y
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <link rel="stylesheet" href="assets/css/footer.css">
    <link rel="stylesheet" href="assets/css/header.css">
    <link rel="stylesheet" href="assets/css/shopping-cart.css">

</head>
<body>
<%@ include file="/partials/header.jsp" %>
    <div  class="page-title-inner">
        <h5 > Giỏ hàng</h5>
    </div>
    <div class="container my-5">
        <div class="card mb-4">
            <div class="card-header">
                <h5>Giỏ hàng của bạn</h5>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead class="table-light">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Ảnh</th>
                            <th scope="col">Tên Tranh</th>
                            <th scope="col">Số Lượng</th>
                            <th scope="col">Giá</th>
                            <th scope="col">Hành Động</th>
                        </tr>
                    </thead>
                    <tbody id="cart-items">
                    <c:forEach  items="${sessionScope.cart.items}" var="cp">
                        <tr>
                            <th scope="row"></th>
                            <td><img src="${cp.imageUrl}" alt="${cp.productName}" width="50"></td>
                            <td>${cp.productName}</td>
                            <td>${cp.quantity}</td>
                            <td>${cp.totalPrice} VND</td>
                            <td>
                                <form method="post" action="remove-from-cart">
                                    <input type="hidden" name="productId" value="${cp.productId}">
                                    <input type="hidden" name="sizeId" value="${cp.sizeId}">
                                    <button type="submit" class="btn btn-danger btn-sm">Xóa</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="4" class="text-end">Tổng tiền</th>
                            <th id="total-price">${sessionScope.cart.totalPrice} VND</th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        

        <div class="d-flex justify-content-between">
            <a href="artWork.jsp" class="btn btn-secondary">Tiếp tục mua hàng</a>
            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#paymentModal">Mua hàng</button>
        </div>
    </div>


    <div class="modal fade" id="paymentModal" tabindex="-1" aria-labelledby="paymentModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h5 class="modal-title" id="paymentModalLabel">Thanh toán</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <!-- Modal Body -->
                <div class="modal-body">
                    <form>
                        <!-- Tổng tiền thanh toán -->
                        <div class="mb-3">
                            <label for="totalAmount" class="form-label fw-bold">Tổng tiền thanh toán:</label>
                            <div id="totalAmount">${sessionScope.cart.totalPrice}</div>
                        </div>
    
                        <div class="mb-3">
                            <label for="paymentMethod" class="form-label">Chọn phương thức thanh toán:</label>
                            <select class="form-select" id="paymentMethod" required>
                                <option value="" disabled selected>Chọn...</option>
                                <c:forEach var="method" items="${paymentMethods}">
                                    <option value="${method.id}">${method.methodName}</option>
                                </c:forEach>
                            </select>
                        </div>
    
                     >
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="button" class="btn btn-primary">Xác nhận</button>
                </div>
            </div>
        </div>
    </div>
<%@ include file="/partials/authModal.jsp" %>

<%@ include file="/partials/footer.jsp" %>
    <script src="/assets/js/shopping-cart.js"></script>
    <script src="/assets/js/header.js"></script>
    <script src="/assets/js/painting-detail.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
