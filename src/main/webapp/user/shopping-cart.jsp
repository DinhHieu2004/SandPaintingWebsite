<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/shopping-cart.css">
</head>
<body>
<%@ include file="/partials/header.jsp" %>
<div class="page-title-inner text-center my-4">
    <h5>Giỏ hàng</h5>
</div>
<div class="container my-5">
    <div class="card mb-4">
        <div class="card-header">
            <h5>Giỏ hàng của bạn</h5>
        </div>
        <div class="card-body">
            <c:choose>
                <c:when test="${not empty sessionScope.cart.items}">
                    <table class="table table-bordered">
                        <thead class="table-light">
                        <tr>
                            <th>#</th>
                            <th>Ảnh</th>
                            <th>Tên Tranh</th>
                            <th>Kích thước</th>
                            <th>Số Lượng</th>
                            <th>Giá</th>
                            <th>Hành Động</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${sessionScope.cart.items}" var="cp" varStatus="status">
                            <tr id="cart-item-${cp.productId}-${cp.sizeId}">
                                <td>${status.index + 1}</td>
                                <td><img src="${cp.imageUrl}" alt="${cp.productName}" width="50"></td>
                                <td>${cp.productName}</td>
                                <td>${cp.sizeDescriptions}</td>
                                <td>
                                    <div class="d-flex align-items-center">
                                        <button class="btn btn-sm btn-secondary decrease-quantity"
                                                data-product-id="${cp.productId}"
                                                data-size-id="${cp.sizeId}">
                                            -
                                        </button>
                                        <span class="mx-2 quantity">${cp.quantity}</span>
                                        <button class="btn btn-sm btn-secondary increase-quantity"
                                                data-product-id="${cp.productId}"
                                                data-size-id="${cp.sizeId}"
                                                data-max-quantity="${cp.quanlytiOfSize}">
                                            +
                                        </button>
                                    </div>
                                </td>
                                <td class="item-total-price">${cp.totalPrice} VND</td>
                                <td>
                                    <button class="btn btn-danger btn-sm remove-item"
                                            data-product-id="${cp.productId}"
                                            data-size-id="${cp.sizeId}">
                                        Xóa
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                            <th colspan="4" class="text-end">Tổng tiền</th>
                            <th id="total-price" colspan="2">${sessionScope.cart.totalPrice} VND</th>
                        </tr>
                        </tfoot>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info text-center" role="alert">
                        Giỏ hàng của bạn đang trống.
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="d-flex justify-content-between">
        <a href="artWork.jsp" class="btn btn-secondary">Tiếp tục mua hàng</a>
        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#paymentModal">Mua hàng</button>
    </div>
    <div class="modal fade" id="paymentModal" tabindex="-1" aria-labelledby="paymentModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="paymentModalLabel">Thanh toán</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Địa chỉ nhận hàng -->
                    <div class="mb-3">
                        <label for="recipientName" class="form-label">Tên người nhận hàng:</label>
                        <input type="text" class="form-control" id="recipientName" name="recipientName"
                               value="${sessionScope.user.fullName}" placeholder="Nhập Tên người nhận hàng" required>
                    </div>
                    <div class="mb-3">
                        <label for="deliveryAddress" class="form-label">Địa chỉ nhận hàng:</label>
                        <input type="text" class="form-control" id="deliveryAddress" name="deliveryAddress"
                               value="${sessionScope.user.address}" placeholder="Nhập địa chỉ nhận hàng" required>
                    </div>

                    <div class="mb-3">
                        <label for="recipientPhone" class="form-label">Số điện thoại người nhận:</label>
                        <input type="text" class="form-control" id="recipientPhone" name="recipientPhone"
                               value="${sessionScope.user.phone}" placeholder="Nhập số điện thoại" required>
                    </div>

                    <div class="mb-3">
                        <h6 class="fw-bold">Danh sách sản phẩm:</h6>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Ảnh</th>
                                <th>Tên Tranh</th>
                                <th>Số Lượng</th>
                                <th>Giá</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${sessionScope.cart.items}" var="cp" varStatus="status">
                                <tr id="cart-item-${cp.productId}-${cp.sizeId}">
                                    <td>${status.index + 1}</td>
                                    <td><img src="${cp.imageUrl}" alt="${cp.productName}" width="50"></td>
                                    <td>${cp.productName}</td>
                                    <td>${cp.quantity}</td>
                                    <td>${cp.totalPrice} VND</td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="mb-3">
                        <label for="totalAmount" class="form-label fw-bold">Tổng tiền thanh toán:</label>
                        <div id="totalAmount">${sessionScope.cart.totalPrice} VND</div>
                    </div>

                    <div class="mb-3">
                        <label for="paymentMethod" class="form-label">Chọn phương thức thanh toán:</label>
                        <select class="form-select" id="paymentMethod" name="paymentMethod" required>
                            <option value="" disabled selected>Chọn...</option>
                            <option value="1">Thanh toán khi nhận hàng (COD)</option>
                            <option value="2">Thẻ tín dụng</option>
                        </select>
                    </div>

                    <div id="creditCardInfo" class="d-none">
                        <div class="mb-3">
                            <label for="cardHolderName" class="form-label">Tên chủ thẻ:</label>
                            <input type="text" class="form-control" id="cardHolderName" name="cardHolderName" placeholder="Nhập tên trên thẻ">
                        </div>
                        <div class="mb-3">
                            <label for="cardNumber" class="form-label">Số thẻ:</label>
                            <input type="text" class="form-control" id="cardNumber" name="cardNumber" placeholder="Nhập số thẻ tín dụng">
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="cardExpiry" class="form-label">Ngày hết hạn:</label>
                                <input type="text" class="form-control" id="cardExpiry" name="cardExpiry" placeholder="MM/YY">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="cardCVV" class="form-label">Mã CVV:</label>
                                <input type="text" class="form-control" id="cardCVV" name="cardCVV" placeholder="CVV">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="button" id="submitPayment" class="btn btn-primary">Xác nhận</button>
                </div>
            </div>
        </div>
    </div>

<%@ include file="/partials/authModal.jsp" %>

<%@ include file="/partials/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
 <script src="${pageContext.request.contextPath}/assets/js/RemoveCartItem.js"></script>
 <script src="${pageContext.request.contextPath}/assets/js/UpdateQuantity.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/checkout.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
</body>
</html>
