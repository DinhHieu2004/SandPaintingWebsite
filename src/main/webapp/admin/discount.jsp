<%--
  Created by IntelliJ IDEA.
  User: XPS
  Date: 1/4/2025
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="addDiscountModal" class="modal">
  <div class="modal-content">
    <div class="modal-header">
      <h3 class="modal-title">Thêm chương trình giảm giá</h3>
      <button class="close-button" onclick="closeAddDiscountModal()">&times;</button>
    </div>

    <div id="successAlert" class="alert alert-success">
      Thêm chương trình giảm giá thành công!
    </div>

    <div id="errorAlert" class="alert alert-error">
      Vui lòng điền đầy đủ thông tin!
    </div>

    <form id="addDiscountForm" onsubmit="handleSubmitDiscount(event)">
      <div class="form-group">
        <label class="form-label">Tên chương trình</label>
        <input
                type="text"
                class="form-control"
                name="name"
                placeholder="Nhập tên chương trình giảm giá"
                required
        >
      </div>

      <div class="form-group">
        <label class="form-label">Phần trăm giảm giá (%)</label>
        <input
                type="number"
                class="form-control"
                name="percentage"
                min="0"
                max="100"
                placeholder="Nhập phần trăm giảm giá"
                required
        >
      </div>

      <div class="form-group">
        <label class="form-label">Tranh áp dụng</label>
        <select
                class="form-control paintings-select"
                name="paintings"
                multiple
                required
        >
          <!-- Will be populated by JavaScript -->
        </select>
      </div>

      <div class="form-group">
        <label class="form-label">Ngày bắt đầu</label>
        <input
                type="date"
                class="form-control"
                name="startDate"
                required
        >
      </div>

      <div class="form-group">
        <label class="form-label">Ngày kết thúc</label>
        <input
                type="date"
                class="form-control"
                name="endDate"
                required
        >
      </div>

      <div class="button-group">
        <button type="button" class="btn btn-secondary" onclick="closeAddDiscountModal()">
          Hủy
        </button>
        <button type="submit" class="btn btn-primary">
          Lưu
        </button>
      </div>
    </form>
  </div>
</div>