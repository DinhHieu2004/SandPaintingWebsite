<%--
  Created by IntelliJ IDEA.
  User: XPS
  Date: 1/4/2025
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Add User Modal -->
<div id="addUserModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h3>Thêm người dùng mới</h3>
            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addUserModal">Thêm người dùng</button>
        </div>
        <div class="modal-body">
            <form id="addUserForm">
                <div class="form-group">
                    <label for="username">Tên người dùng</label>
                    <input
                            type="text"
                            id="username"
                            name="username"
                            placeholder="Nhập tên người dùng"
                            required
                    >
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input
                            type="email"
                            id="email"
                            name="email"
                            placeholder="Nhập email"
                            required
                    >
                </div>
                <div class="form-group">
                    <label for="role">Vai trò</label>
                    <select id="role" name="role" required>
                        <option value="user">Người dùng</option>
                        <option value="admin">Quản trị viên</option>
                    </select>
                </div>
                <div class="form-actions">
                    <button type="button" onclick="submitAddUserForm()" class="btn btn-primary">Lưu</button>
                    <button type="button" onclick="closeAddUserModal()" class="btn btn-secondary">Hủy</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Edit User Modal -->
<div id="editUserModal" class="modal">
    <div class="modal-container">
        <div class="modal-header">
            <h2>Chỉnh sửa thông tin người dùng</h2>
            <button class="modal-close-btn" onclick="closeEditUserModal()">×</button>
        </div>

        <div class="modal-body">
            <form id="editUserForm" onsubmit="handleEditUser(event)">
                <input type="hidden" id="editUserId" name="userId">

                <div class="form-group">
                    <label for="editUsername">Tên người dùng</label>
                    <input
                            type="text"
                            id="editUsername"
                            name="username"
                            required
                            placeholder="Nhập tên người dùng"
                    >
                </div>

                <div class="form-group">
                    <label for="editEmail">Email</label>
                    <input
                            type="email"
                            id="editEmail"
                            name="email"
                            required
                            placeholder="Nhập địa chỉ email"
                    >
                </div>

                <div class="form-group">
                    <label for="editUserRole">Vai trò</label>
                    <select id="editUserRole" name="role" required>
                        <option value="user">Người dùng</option>
                        <option value="admin">Quản trị viên</option>
                    </select>
                </div>

                <div class="form-actions">
                    <button type="button" class="btn btn-secondary" onclick="closeEditUserModal()">Hủy</button>
                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Delete User Modal -->
<div id="deleteUserModal" class="modal">
    <div class="modal-container">
        <div class="modal-header">
            <h2>Xác nhận xóa người dùng</h2>
            <button class="modal-close-btn" onclick="closeDeleteUserModal()">×</button>
        </div>

        <div class="modal-body">
            <p>Bạn có chắc chắn muốn xóa người dùng <span id="deleteUserName"></span> không?</p>
            <input type="hidden" id="deleteUserId">

            <div class="form-actions">
                <button class="btn btn-secondary" onclick="closeDeleteUserModal()">Hủy</button>
                <button class="btn btn-danger" onclick="confirmDeleteUser()">Xóa</button>
            </div>
        </div>
    </div>
</div>