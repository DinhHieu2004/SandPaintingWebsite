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
  <script src="https://cdnjs.cloudflare.com/ajax/libs/recharts/2.12.2/ReactUtils.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/lucide/0.263.1/lucide.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
</head>
<body>
<div class="container">
  <!-- Include sidebar -->
  <%@ include file="sidebar.jsp" %>

  <div class="main-content" id="mainContent">
    <!-- Content will be dynamically inserted here -->
  </div>

  <!-- Include all modals -->
  <%@ include file="discount-modal.jsp" %>
  <%@ include file="painting-modals.jsp" %>
  <%@ include file="order-modals.jsp" %>
  <%@ include file="user-modals.jsp" %>
</div>

<script src="${pageContext.request.contextPath}/assets/js/modal-admin.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/admin.js"></script>
</body>
</html>