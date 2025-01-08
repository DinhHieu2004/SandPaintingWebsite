function renderOrders() {
    return `

<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#orderDetailsModal">
  Open Modal
</button>


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
</div>

    `;
}
