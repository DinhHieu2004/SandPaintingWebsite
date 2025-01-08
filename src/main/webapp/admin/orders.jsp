<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Panel</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
  <style> .sidebar {
    height: 100vh;
    position: fixed;
    top: 0;
    left: 0;
    width: 250px;
    background-color: #343a40;
    color: white;
    padding: 20px 10px;
  }
  .sidebar a {
    color: white;
    text-decoration: none;
    display: block;
    padding: 10px;
    margin-bottom: 5px;
  }
  .sidebar a:hover {
    background-color: #495057;
    border-radius: 5px;
  }
  .content {
    margin-left: 260px; /* Sidebar width + margin */
    padding: 20px;
  }
  </style>

</head>
<body>
<!-- Sidebar -->
<%@ include file="/admin/sidebar.jsp" %>
<div class="content">
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
      <c:forEach var="order" items="${currentOrder}">
        <tr>
          <td>${order.id}</td>
          <td>${order.totalAmount}</td>
          <td>${order.orderDate}</td>
          <td>${order.status}</td>
          <td><!-- Các nút hành động hoặc liên kết xử lý hành động --></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

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
        <th>Ngày Giao</th>
        <th>Trạng Thái</th>
        <th>Hành Động</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="order" items="${historyOrder}">
        <tr>
          <td>${order.id}</td>
          <td>${order.totalAmount}</td>
          <td>${order.orderDate}</td>
          <td>${order.deliveryDate}</td>
          <td>${order.status}</td>
          <td></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
  $(document).ready(function() {
    $('#currentOrders').DataTable();
    $('#orderHistory').DataTable();
  });
</script>
</body>
</html>
