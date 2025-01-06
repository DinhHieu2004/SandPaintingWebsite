<%--
  Created by IntelliJ IDEA.
  User: XPS
  Date: 1/4/2025
  Time: 10:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Dashboard</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <link rel="stylesheet" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<div class="container">
  <!-- Sidebar -->
  <div class="sidebar">
    <h2>Admin Dashboard</h2>
    <nav>
      <button class="nav-button" data-page="dashboard">Dashboard</button>
      <button class="nav-button" data-page="orders">Quản lý đơn hàng</button>
      <button class="nav-button" data-page="discount">Quản lý giảm giá</button>
      <button class="nav-button" data-page="paintings">Quản lý tranh</button>
      <button class="nav-button" data-page="users">Quản lý người dùng</button>
    </nav>
  </div>

  <!-- Main content -->
  <div class="main-content" id="mainContent">
    <!-- Content will be dynamically inserted here -->
  </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/admin.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/admin/orderManager.js"></script>

</body>
</html>
