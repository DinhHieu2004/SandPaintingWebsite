<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<div class="orders-content">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="orderDetailsModalLabel">Chi Tiết Đơn Hàng</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div id="orderRecipientInfo">
          </div>
          <table class="table table-striped">
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
          <div id="totalPrice">
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/datatables.net-dt/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/admin/orderManager.js"></script>
</div>