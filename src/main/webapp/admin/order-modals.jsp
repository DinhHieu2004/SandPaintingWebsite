<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Edit Order Modal -->
<div id="editManageOrderModal" class="modal">
  <div class="modal-content">
    <div class="modal-header">
      <h3>Chỉnh sửa thông tin đơn hàng</h3>
    </div>
    <div class="modal-body">
      <form id="editManageOderForm">
        <div class="form-group">
          <label for="paintingTitle">ID</label>
          <input type="text" id="manageOrderIDEdit" name="title" required placeholder="#1">
        </div>
        <div class="form-group">
          <label for="artistName">Ngày đặt hàng</label>
          <input type="text" id="manageOrderDateEdit" name="artist" required placeholder="12/12/2024">
        </div>
        <div class="form-group">
          <label for="artistName">Ngày giao hàng</label>
          <input type="text" id="manageDeliveryDateEdit" name="artist" required placeholder="20/12/2024">
        </div>
        <div class="form-group">
          <label for="paintingPrice">Tổng giá tiền</label>
          <input type="number" id="manageOrderAmountEdit" name="price" required placeholder="5.000.000">
        </div>
        <div class="form-group">
          <label for="paintingPrice">Sản phẩm:</label>
          <label for="paintingPrice"> 01 | Phong cảnh mùa Thu | Trần Minh Hoàng | 5.000.000đ</label>
        </div>
        <div class="form-group">
          <label for="paintingStatus">Trạng thái</label>
          <select id="orderEditStatus" name="status" required>
            <option value="complete">Hoàn thành</option>
            <option value="processing">Đang xử lý</option>
            <option value="fail">Thất bại</option>
          </select>
        </div>
        <div class="form-actions">
          <button type="submit" class="button button-primary">Lưu</button>
          <button type="button" class="button" onclick="closeEditOrderModal()">Hủy</button>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- View Order Modal -->
<div id="seeManageOrderModal" class="modal">
  <div class="modal-content">
    <div class="modal-header">
      <h3>Xem thông tin đơn hàng</h3>
    </div>
    <div class="modal-body">
      <form id="seeManageOderForm">
        <div class="form-group">
          <label for="paintingTitle">ID: #1</label>
        </div>
        <div class="form-group">
          <label for="artistName">Ngày đặt hàng: 12/12/2024</label>
        </div>
        <div class="form-group">
          <label for="artistName">Ngày giao hàng: 20/12/2024</label>
        </div>
        <div class="form-group">
          <label for="paintingPrice">Tổng giá tiền: 5.000.000đ</label>
        </div>
        <div class="form-group">
          <label for="paintingPrice">Sản phẩm: 01 | Phong cảnh mùa Thu | Trần Minh Hoàng | 5.000.000đ</label>
        </div>
        <div class="form-group">
          <label for="paintingStatus">Trạng thái: Hoàn thành</label>
        </div>
        <div class="form-actions">
          <button type="button" class="button" onclick="closeSeeOrderModal()">Hủy</button>
        </div>
      </form>
    </div>
  </div>
</div>