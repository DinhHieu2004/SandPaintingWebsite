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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
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
</body>
</html>
