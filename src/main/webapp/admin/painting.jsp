<%--
  Created by IntelliJ IDEA.
  User: XPS
  Date: 1/4/2025
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- View Painting Modal -->
<div id="seePaintingModal" class="modal">
  <div class="modal-content">
    <div class="modal-header">
      <h3>Chi tiết tranh</h3>
    </div>
    <div class="modal-body">
      <form id="seePaintingForm">
        <div class="form-group">
          <label for="paintingTitle">Tên tranh: Phong cảnh mùa Thu</label>
        </div>
        <div class="form-group">
          <label for="artistName">Id: 01</label>
        </div>
        <div class="form-group">
          <label for="artistName">Họa sĩ: Trần Minh Hoàng</label>
        </div>
        <div class="form-group">
          <label for="paintingPrice">Chủ đề: Phong cảnh</label>
        </div>
        <div class="form-group">
          <label for="paintingPrice">Kích thước: 40x40cm</label>
        </div>
        <div class="form-group" style="text-align: center">
          <img src="/api/placeholder/400/320" alt="placeholder">
        </div>
        <div class="form-group">
          <label for="paintingPrice">Mô tả tranh: Phong cảnh mùa Thu đầy tính nghệ thuật của họa sĩ Trần Minh Hoàng</label>
        </div>
        <div class="form-group">
          <label for="paintingPrice">Giá: 5.000.000đ</label>
        </div>
        <div class="form-group">
          <label for="paintingStatus">Trạng thái: Còn hàng</label>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Add Painting Modal -->
<div id="addPaintingModal" class="modal">
  <div class="modal-content">
    <div class="modal-header">
      <h3>Thêm tranh mới</h3>
    </div>
    <div class="modal-body">
      <form id="addPaintingForm">
        <div class="form-group">
          <label for="paintingTitle">Tên tranh</label>
          <input type="text" id="paintingTitle" name="title" required placeholder="Nhập tên tranh">
        </div>
        <div class="form-group">
          <label for="artistName">Họa sĩ</label>
          <input type="text" id="artistName" name="artist" required placeholder="Nhập tên họa sĩ">
        </div>
        <div class="form-group">
          <label for="paintingPrice">Giá</label>
          <input type="number" id="paintingPrice" name="price" required placeholder="Nhập giá tranh">
        </div>
        <div class="form-group">
          <label for="paintingPrice">ảnh</label>
          <input type="text" id="img" name="img" required placeholder="Nhập url ảnh">
        </div>
        <div class="form-group">
          <label for="paintingStatus">Trạng thái</label>
          <select id="paintingStatus" name="status" required>
            <option value="available">Còn hàng</option>
            <option value="sold">Đã bán</option>
          </select>
        </div>
        <div class="form-actions">
          <button type="submit" class="button button-primary">Lưu</button>
          <button type="button" class="button" onclick="closePaintingModal()">Hủy</button>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Edit Painting Modal -->
<div id="editPaintingModal" class="modal">
  <div class="modal-content">
    <div class="modal-header">
      <h3>Chỉnh sửa tranh</h3>
    </div>
    <div class="modal-body">
      <form id="editPaintingForm">
        <div class="form-group">
          <label for="paintingTitle">Tên tranh</label>
          <input type="text" id="paintingTitleEdit" name="title" required placeholder="Phong cảnh mùa thu">
        </div>
        <div class="form-group">
          <label for="artistName">Họa sĩ</label>
          <input type="text" id="artistNameEdit" name="artist" required placeholder="Trần Minh Hoàng">
        </div>
        <div class="form-group">
          <label for="paintingPrice">Giá</label>
          <input type="number" id="paintingPriceEdit" name="price" required placeholder="5.000.000">
        </div>
        <div class="form-group">
          <label for="paintingStatus">Trạng thái</label>
          <select id="paintingStatusEdit" name="status" required>
            <option value="available">Còn hàng</option>
            <option value="sold">Đã bán</option>
          </select>
        </div>
        <div class="form-actions">
          <button type="submit" class="button button-primary">Lưu</button>
          <button type="button" class="button" onclick="closeEditPaintingModal()">Hủy</button>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Delete Painting Modal -->
<div id="deletePaintingModel" class="modal">
  <div class="model-content" style="background-color: white; padding: 2rem; border-radius: 0.5rem; width: 90%; max-width: 600px;">
    <div class="modal-header">
      <h3>Bạn muốn xóa tranh?</h3>
    </div>
    <div class="modal-body">
      <form>
        <div class="form-actions">
          <button type="submit" class="button button-primary">Xóa</button>
          <button type="button" class="button" onclick="closeDeletePaintingModal()">Hủy</button>
        </div>
      </form>
    </div>
  </div>
</div>