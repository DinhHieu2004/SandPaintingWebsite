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
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/css/bootstrap.min.css" rel="stylesheet">

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

   .modal-body {
     padding: 2rem;
   }
  .form-group {
    margin-bottom: 1.5rem;
  }
  .form-label {
    font-weight: 500;
    margin-bottom: 0.5rem;
  }
  .form-control, .form-select {
    padding: 0.625rem;
    border-radius: 0.375rem;
  }
  #sizeQuantityContainer {
    background-color: #f8f9fa;
    padding: 1.5rem;
    border-radius: 0.5rem;
    margin-top: 1rem;
  }
  .size-quantity-pair {
    background-color: white;
    padding: 1rem;
    border-radius: 0.375rem;
    margin-bottom: 1rem;
    box-shadow: 0 1px 3px rgba(0,0,0,0.1);
  }
  .modal-footer {
    padding: 1rem 2rem;
    border-top: 1px solid #dee2e6;
  }
  .btn-primary {
    padding: 0.5rem 1.5rem;
  }
  #addSizeField {
    margin-top: 1rem;
    width: auto;
  }
  .modal-body {
    padding: 2rem;
  }
  .size-quantity-pair {
    background-color: #f8f9fa;
    padding: 0.75rem;
    border-radius: 0.375rem;
    margin-bottom: 0.5rem;
  }
  .form-label {
    font-weight: 500;
    margin-bottom: 0.25rem;
    font-size: 0.9rem;
  }
  .form-control-sm {
    height: 30px;
    padding: 0.25rem 0.5rem;
  }
  .d-flex {
    display: flex;
  }
  .w-50 {
    width: 50%;
  }


  </style>

</head>
<body>
<!-- Sidebar -->
<%@ include file="/admin/sidebar.jsp" %>
<div class="content">
  <div class="card mb-4">
    <div class="card-header bg-success text-white">
      <h4>Tranh</h4>

      <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addPaintingModal">
        Thêm họa sĩ
      </button>

    </div>
    <div class="card-body">
      <table id="products" class="table table-bordered display">
        <thead>
        <tr>
          <th>Mã sản phẩm</th>
          <th>Ảnh</th>
          <th>Tên </th>
          <th>Còn hàng</th>
          <th>giá</th>
          <th>tác giả</th>
          <th>Hành Động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${products}">
          <tr>
            <td>${p.id}</td>
            <td><img src="${pageContext.request.contextPath}/${p.imageUrl}" alt="${p.imageUrl}" width="60"></td>

            <td>${p.title}</td>
            <td>${p.available}</td>
            <td>${p.price}</td>
            <td>${p.artistName}</td>
            <td><button class="btn btn-info btn-sm" data-bs-toggle="modal"
                        data-bs-target="#orderDetailsModal" data-order-id="${p.id}">Xem Chi Tiết</button>

              <button class="btn btn-danger btn-sm" data-bs-toggle="modal"
                      data-bs-target="#orderDetailsModal" data-order-id="${p.id}">Xóa</button></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>


  <div class="row"> <!-- Thêm container row -->
    <div class="col-6"> <!-- Cột đầu tiên chiếm 6/12 -->
      <div class="card mb-4">
        <div class="card-header bg-secondary text-white">
          <h4>Danh sách Chủ Đề</h4>
        </div>
        <div class="card-body">
          <table id="themes" class="table table-bordered display">
            <thead>
            <tr>
              <th>ID</th>
              <th>Tên Chủ Đề</th>
              <th>Hành Động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="theme" items="${themes}">
              <tr>
                <td>${theme.id}</td>
                <td>${theme.themeName}</td>
                <td>
                  <button class="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target="#editThemeModal" data-theme-id="${theme.id}">Sửa</button>
                  <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteThemeModal" data-theme-id="${theme.id}">Xóa</button>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <div class="col-6"> <!-- Cột thứ hai chiếm 6/12 -->
      <div class="card mb-4">
        <div class="card-header bg-secondary text-white">
          <h4>Danh sách Kích Thước</h4> <!-- Sửa tên cho đúng -->
        </div>
        <div class="card-body">
          <table id="sizes" class="table table-bordered display">
            <thead>
            <tr>
              <th>ID</th>
              <th>Tên Kích thước</th>
              <th>Hành Động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="s" items="${sizes}">
              <tr>
                <td>${s.idSize}</td>
                <td>${s.sizeDescriptions}</td>
                <td>
                  <button class="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target="#editThemeModal" data-theme-id="${theme.id}">Sửa</button>
                  <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteThemeModal" data-theme-id="${theme.id}">Xóa</button>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
<!-- Modal thêm tranh -->
<div class="modal fade" id="addPaintingModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="AddPaintingServlet" method="post" enctype="multipart/form-data">
        <div class="modal-header">
          <h5 class="modal-title">Thêm Tranh</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>
        <div class="modal-body">
          <div class="row mb-3">
            <div class="col-md-6">
              <label class="form-label">Tiêu đề</label>
              <input type="text" class="form-control form-control-sm" name="title" required>
            </div>

            <div class="col-md-6">
              <label class="form-label">Chủ đề</label>
              <select class="form-select form-select-sm" name="themeId" required>
                <c:forEach var="t" items="${themes}">
                  <option value="${t.id}">${t.themeName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
            <div class="mb-3">
            <label class="form-label">Mô tả</label>
            <textarea class="form-control form-control-sm" name="description" rows="4"
                      placeholder="Nhập mô tả chi tiết về tranh..."></textarea>
            </div>

          <div class="row mb-3">
            <div class="col-md-6">
              <label class="form-label">Giá</label>
              <input type="number" step="0.01" class="form-control form-control-sm" name="price" required>
            </div>
            <div class="col-md-6">
              <label class="form-label">Họa sĩ</label>
              <select class="form-select form-select-sm" name="artistId" required>
                <c:forEach var="artist" items="${artists}">
                <option value="${artist.id}">${artist.name}</option>
                </c:forEach>

              </select>
            </div>
          </div>

          <div class="mb-3">
            <label class="form-label">Ảnh tranh</label>
            <input type="file" class="form-control form-control-sm" name="image" accept="image/*" required>
          </div>

          <div class="mb-3">
            <div class="form-check">
              <input type="checkbox" class="form-check-input" id="isFeatured" name="isFeatured">
              <label class="form-check-label" for="isFeatured">Tranh nổi bật</label>
            </div>
          </div>

          <div class="size-quantities">
            <label class="form-label">Kích thước và Số lượng</label>
              <c:forEach var="size" items="${sizes}">
                <div class="size-quantity-pair">
                  <div class="row g-2">
                    <div class="col-7">
                      <input type="hidden" name="sizeId" value="${size.idSize}">
                      <input type="text" class="form-control form-control-sm" name="size" value="${size.sizeDescriptions}" readonly>
                    </div>
                    <div class="col-5">
                      <input type="number" class="form-control form-control-sm" name="quantity" value="0" min="0">
                    </div>
                  </div>
                </div>
              </c:forEach>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary btn-sm" data-bs-dismiss="modal">Đóng</button>
          <button type="submit" class="btn btn-primary btn-sm">Thêm</button>
        </div>
      </form>
    </div>
  </div>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>

<script>
  $(document).ready(function() {
    $('#products').DataTable();
  });

</script>
</body>
</html>
