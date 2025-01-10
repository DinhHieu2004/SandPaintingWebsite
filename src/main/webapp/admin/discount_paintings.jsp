<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sản phẩm giảm giá</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
</head>
<body>
<div class="container mt-4">
  <!-- Hiển thị tên chương trình giảm giá -->
  <h2 class="mb-4">Sản phẩm trong chương trình giảm giá: <span>${discountName}</span></h2>

  <!-- Hiển thị danh sách sản phẩm dưới dạng DataTable -->
  <table id="paintingsTable" class="table table-bordered table-striped">
    <thead>
    <tr>
      <th>ID</th>
      <th>Tên</th>
      <th>Họa sĩ</th>
      <th>Giá</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="painting" items="${paintings}">
      <tr>
        <td>${painting.id}</td>
        <td>${painting.title}</td>
        <td>${painting.artistName}</td>
        <td>${painting.price}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
  $(document).ready(function() {
    $('#paintingsTable').DataTable({
    });
  });
</script>
</body>
</html>


