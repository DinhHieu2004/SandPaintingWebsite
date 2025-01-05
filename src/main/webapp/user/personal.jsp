<%@ page import="com.example.web.dao.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Cá Nhân</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
</head>

<body>
<%@ include file="/partials/header.jsp" %>

<div class="container mt-5">
    <!-- Thông tin cá nhân -->
    <div class="card mb-4">
        <div class="card-header bg-primary text-white">
            <h4>Thông Tin Cá Nhân</h4>
        </div>
        <div class="card">
            <div class="card-body">
                <c:if test="${sessionScope.user != null}">
                    <p><strong>Họ và tên:</strong> ${sessionScope.user.fullName}</p>
                    <p><strong>Số điện thoại:</strong> ${sessionScope.user.phone}</p>
                    <p><strong>Email:</strong> ${sessionScope.user.email}</p>
                    <p><strong>Địa chỉ:</strong> ${sessionScope.user.address}</p>
                </c:if>
                <c:if test="${sessionScope.user == null}">
                    <p>Không tìm thấy thông tin người dùng.</p>
                </c:if>
            </div>
        </div>
    </div>

    <!-- Bảng Đơn Hàng Hiện Tại -->
    <div class="card mb-4">
        <div class="card-header bg-success text-white">
            <h4>Đơn Hàng Hiện Tại</h4>
        </div>
        <div class="card-body">
            <table id="currentOrders" class="table table-bordered display">
                <thead>
                <tr>
                    <th>Mã Đơn Hàng</th>
                    <th>Tổng Tiền</th>
                    <th>Ngày Đặt</th>
                    <th>Trạng Thái</th>
                    <th>Hành Động</th>
                </tr>
                </thead>
                <tbody>
                <%-- Current orders sẽ được xử lý bởi JavaScript --%>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Bảng Lịch Sử Đơn Hàng -->
    <div class="card mb-4">
        <div class="card-header bg-secondary text-white">
            <h4>Lịch Sử Đơn Hàng</h4>
        </div>
        <div class="card-body">
            <table id="orderHistory" class="table table-bordered display">
                <thead>
                <tr>
                    <th>Mã Đơn Hàng</th>
                    <th>Tổng Tiền</th>
                    <th>Ngày Đặt</th>
                    <th>Trạng Thái</th>
                    <th>Hành Động</th>
                </tr>
                </thead>
                <tbody>
                <%-- Previous orders sẽ được xử lý bởi JavaScript --%>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal Chi Tiết Đơn Hàng -->
    <div class="modal fade" id="orderDetailsModal" tabindex="-1" aria-labelledby="orderDetailsModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="orderDetailsModalLabel">Chi Tiết Đơn Hàng</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Tên Sản Phẩm</th>
                            <th>Kích Thước</th>
                            <th>Số Lượng</th>
                            <th>Giá</th>
                        </tr>
                        </thead>
                        <tbody id="orderDetailsBody"></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/partials/footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/personal.js"></script>
</body>

</html>
