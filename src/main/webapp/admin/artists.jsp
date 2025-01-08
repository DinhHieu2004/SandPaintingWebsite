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
      <h4>Người dùng</h4>
    </div>
    <div class="card-body">
      <table id="artists" class="table table-bordered display">
        <thead>
        <tr>
          <th>Mã họa sĩ</th>
          <th>Ảnh</th>
          <th>Tên họa sĩ</th>
          <th>Ngày sinh</th>
          <th>Quốc tịch</th>
          <th>Hành Động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${artists}">
          <tr>
            <td>${u.id}</td>
            <td><img src="${u.photoUrl}" width="60"></td>
            <td>${u.name}</td>
            <td>${u.birthDate}</td>
            <td>${u.nationality}</td>
            <td><button class="btn btn-info btn-sm" data-bs-toggle="modal"
                        data-bs-target="#orderDetailsModal" data-order-id="${u.id}">Xem Chi Tiết</button></td>
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
    $('#artists').DataTable();
  });
</script>
</body>
</html>
