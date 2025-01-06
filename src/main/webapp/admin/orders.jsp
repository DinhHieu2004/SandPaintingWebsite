<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">

<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/personal.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/datatables.net-dt/css/jquery.dataTables.min.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/datatables.net/js/jquery.dataTables.min.js"></script>
</head>
<style> .modal-dialog {
  margin: 0 auto !important;
  max-width: 500px;
}</style>
<body>
<div class="orders-content">


  <div class="card mb-4">

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
        </tbody>
      </table>
    </div>
  </div>

    <div class="modal fade" id="orderDetailsModal" tabindex="-1" aria-labelledby="orderDetailsModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="orderDetailsModalLabel">Chi Tiết Đơn Hàng</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <div id="orderRecipientInfo">
            </div>

            <div class="mt-3">
              <label for="orderStatus" class="form-label"><strong>Thay Đổi Trạng Thái Đơn Hàng:</strong></label>
              <select id="orderStatus" class="form-select">
                <option value="Chờ">chờ xử lý</option>
                <option value="đang giao">Đang giao</option>
                <option value="hoàn thành">hoàn thành</option>
                <option value="đã hủy">Đã hủy</option>
              </select>
            </div>

            <div id="info">
            </div>

            <table class="table table-striped mt-3">
              <thead>
              <tr>
                <th>Tên Sản Phẩm</th>
                <th>Kích Thước</th>
                <th>Số Lượng</th>
                <th>Giá</th>
              </tr>
              </thead>
              <tbody id="orderDetailsBody"></tbody>
            </table>

            <div id="totalPrice" class="mt-3">
              <!-- Tổng giá -->
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" id="saveStatusBtn" class="btn btn-primary">Lưu Trạng Thái</button>
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
          </div>
        </div>
      </div>
    </div>

  </div>

<script src="${pageContext.request.contextPath}/assets/js/admin/orderManager.js"></script>
</div>

</body>
</html>