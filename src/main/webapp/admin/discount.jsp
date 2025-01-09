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
    <div class="card-header bg-success text-white">
      <h4>Danh sách giảm giá</h4>
    </div>
    <div class="card-body">
      <table id="discounts" class="table table-bordered display">
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addDiscountModal">
          Thêm chương trình giảm giá
        </button>
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
            <td>${discount.startDate}</td>
            <td>${discount.endDate}</td>
            <td>${discount.createdAt}</td>
            <td>
              <a href="${pageContext.request.contextPath}/admin/discountPainting?discountId=${discount.id}"
                 class="btn btn-info btn-sm">
                Xem sản phẩm giảm giá
              </a>

              <button class="btn btn-info btn-sm" data-bs-toggle="modal"
                      data-bs-target="#viewEditDiscountModal" data-discount-id="${discount.id}">
                Chỉnh sửa
              </button>
              <button class="btn btn-danger btn-sm" data-bs-toggle="modal"
                      data-bs-target="#deleteDiscountModal" data-discount-id="${discount.id}">
                Xóa
              </button>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

<!-- Modal: Chỉnh sửa giảm giá -->
<div class="modal fade" id="viewEditDiscountModal" tabindex="-1" aria-labelledby="viewEditDiscountModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="viewEditDiscountModalLabel">Chỉnh sửa giảm giá</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form id="editDiscountForm">
        <div class="modal-body">
          <div class="mb-3">
            <label for="editDiscountName" class="form-label">Tên giảm giá</label>
            <input type="text" class="form-control" id="editDiscountName" name="discountName" required>
          </div>
          <div class="mb-3">
            <label for="editDiscountPercentage" class="form-label">Phần trăm giảm giá</label>
            <input type="number" class="form-control" id="editDiscountPercentage" name="discountPercentage" required>
          </div>
          <div class="mb-3">
            <label for="editStartDate" class="form-label">Ngày bắt đầu</label>
            <input type="date" class="form-control" id="editStartDate" name="startDate" required>
          </div>
          <div class="mb-3">
            <label for="editEndDate" class="form-label">Ngày kết thúc</label>
            <input type="date" class="form-control" id="editEndDate" name="endDate" required>
          </div>
          <div class="mb-3">
            <label for="editImageUrl" class="form-label">URL ảnh</label>
            <input type="url" class="form-control" id="editImageUrl" name="imageUrl">
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
          <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Modal: Thêm giảm giá -->
<div class="modal fade" id="addDiscountModal" tabindex="-1" aria-labelledby="addDiscountModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="addDiscountModalLabel">Thêm chương trình giảm giá</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="${pageContext.request.contextPath}/admin/discounts/add" method="POST" id="addDiscountForm">
        <div class="modal-body">
          <div class="mb-3">
            <label for="addDiscountName" class="form-label">Tên giảm giá</label>
            <input type="text" class="form-control" id="addDiscountName" name="discountName" required>
          </div>
          <div class="mb-3">
            <label for="addDiscountPercentage" class="form-label">Phần trăm giảm giá</label>
            <input type="number" class="form-control" id="addDiscountPercentage" name="discountPercentage" required>
          </div>
          <div class="mb-3">
            <label for="addStartDate" class="form-label">Ngày bắt đầu</label>
            <input type="date" class="form-control" id="addStartDate" name="startDate" required>
          </div>
          <div class="mb-3">
            <label for="addEndDate" class="form-label">Ngày kết thúc</label>
            <input type="date" class="form-control" id="addEndDate" name="endDate" required>
          </div>
          <div class="mb-3">
            <label for="addImageUrl" class="form-label">URL ảnh</label>
            <input type="url" class="form-control" id="addImageUrl" name="imageUrl">
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
          <button type="submit" class="btn btn-primary">Lưu</button>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Modal: Xóa giảm giá -->
<div class="modal fade" id="deleteDiscountModal" tabindex="-1" aria-labelledby="deleteDiscountModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="deleteDiscountModalLabel">Xóa giảm giá</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Bạn có chắc chắn muốn xóa chương trình giảm giá này không?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
        <button type="button" class="btn btn-danger" id="confirmDeleteButton">Xóa</button>
      </div>
    </div>
  </div>
</div>

<script>
  $(document).ready(function () {
    $('#discounts').DataTable();
  });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
