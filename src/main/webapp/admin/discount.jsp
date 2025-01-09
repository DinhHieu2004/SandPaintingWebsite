<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Quản lý giảm giá</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
  <style>
    .sidebar {
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
      margin-left: 260px;
      padding: 20px;
    }
  </style>
</head>
<body>
<%@ include file="/admin/sidebar.jsp" %>
<div class="content">
  <div class="card mb-4">
    <div class="card-header bg-primary text-white">
      <h4>Danh sách giảm giá</h4>
    </div>
    <div class="card-body">
      <table id="discounts" class="table table-bordered display">
        <thead>
        <tr>
          <th>Mã giảm giá</th>
          <th>Ảnh</th>
          <th>Tên giảm giá</th>
          <th>% Giảm</th>
          <th>Ngày bắt đầu</th>
          <th>Ngày kết thúc</th>
          <th>Ngày tạo</th>
          <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
          <c:forEach var="discount" items="${list}">
            <tr>
              <td>${discount.id}</td>
              <td>
                <img src="${discount.imageUrl != null ? discount.imageUrl : 'default-image.png'}"
                     alt="${discount.discountName}" width="50">
              </td>
              <td>${discount.discountName != null ? discount.discountName : 'Không xác định'}</td>
              <td>
                <f:formatNumber value="${discount.discountPercentage}" type="number"
                                minFractionDigits="0" maxFractionDigits="2"/>%
              </td>
              <td>
                ${discount.startDate}
              </td>
              <td>
                  ${discount.endDate}
              </td>
              <td>
                ${discount.createdAt}
              </td>
              <td>
                <button class="btn btn-info btn-sm" data-bs-toggle="modal"
                        data-bs-target="#viewDetailsModal" data-discount-id="${discount.id}">
                  Chỉnh sửa
                </button>
                <button class="btn btn-danger btn-sm" data-bs-toggle="modal"
                        data-bs-target="#deleteDiscountModal" data-discount-id="${discount.id}">
                  Xóa
                </button>
              </td>
            </tr>
          </c:forEach>
        <c:if test="${empty dc}">
        </c:if>
        </tbody>
      </table>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
  $(document).ready(function () {
    $('#discounts').DataTable();
  });
</script>
</body>
</html>
