<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Panel</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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

<!-- Main Content -->
<div class="content">
  admin
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
