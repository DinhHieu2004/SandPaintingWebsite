<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Cá Nhân</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/personal.css">

    
</head>

<body>
<%@ include file="/partials/header.jsp" %>

    <div id="content-wrapper">

    <div class="container mt-5">
        <div class="card mb-4">
            <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
                <h4>Thông Tin Cá Nhân</h4>

                <div class="button-group">
                    <button class="btn btn-light btn-sm" data-bs-toggle="modal"
                        data-bs-target="#changePassword">Đổi mật khẩu</button>
                    <button class="btn btn-light btn-sm" data-bs-toggle="modal"
                        data-bs-target="#editPersonalInfoModal">Chỉnh sửa</button>
                    <button class="btn btn-danger btn-sm">Đăng xuất</button>
                </div>
            </div>
            <div class="card-body">
                <p><strong>Họ và tên:</strong> Nguyễn Văn A</p>
                <p><strong>Số điện thoại:</strong> +84987654321</p>
                <p><strong>Email:</strong> nguyen.van.a@example.com</p>
            </div>
        </div>

        <div class="modal fade" id="editPersonalInfoModal" tabindex="-1" aria-labelledby="editPersonalInfoModalLabel"
            aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editPersonalInfoModalLabel">Chỉnh Sửa Thông Tin Cá Nhân</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="mb-3">
                                <label for="name" class="form-label">Họ và tên</label>
                                <input type="text" class="form-control" id="name" value="Nguyễn Văn A">
                            </div>
                            <div class="mb-3">
                                <label for="phone" class="form-label">Số điện thoại</label>
                                <input type="text" class="form-control" id="phone" value="+84987654321">
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" value="nguyen.van.a@example.com">
                            </div>
                            
                            <button type="button" class="btn change-btn">Lưu Thay Đổi</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="changePassword" tabindex="-1" aria-labelledby="changePassword" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="changePasswordContent">Đổi mật khẩu</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
                    </div>
                    <div class="modal-body">
                        <form action="change-password" method="post">
                            <div class="mb-3">
                                <label for="currentPassword" class="form-label">Mật khẩu hiện tại</label>
                                <input type="password" class="form-control" id="currentPassword" name="currentPassword" placeholder="Nhập mật khẩu hiện tại" required>
                            </div>
                            <div class="mb-3">
                                <label for="newPassword" class="form-label">Mật khẩu mới</label>
                                <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Nhập mật khẩu mới" required>
                            </div>
                            <div class="mb-3">
                                <label for="confirmPassword" class="form-label">Nhập lại mật khẩu mới</label>
                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Nhập lại mật khẩu mới" required>
                            </div>
                            <button type="submit" class="btn btn-primary" style="background-color: var(--primary-color)">Lưu Thay Đổi</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <div class="card mb-4">
            <div class="card-header bg-success text-white d-flex justify-content-between align-items-center">
                <h4>Đơn Hàng Hiện Tại</h4>
            </div>
            <div class="card-body">
                <table id="currentOrders" class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Mã Đơn Hàng</th>
                            <th>Sản Phẩm</th>
                            <th>Tổng Tiền</th>
                            <th>Ngày Dự Kiến Giao</th>
                            <th>Trạng Thái</th>
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>#5</td>
                            <td>
                                <ul>
                                    <li>Tranh Cảnh Biển</li>
                                    <li>Tranh Phong Cảnh</li>
                                </ul>
                            </td>
                            <td>7.000.000₫</td>
                            <td>Đang xử lý</td>
                            <td>Chưa giao</td>
                            <td><button class="btn btn-danger btn-sm">Hủy</button></td>
                        </tr>
                        <tr>
                            <td>#6</td>
                            <td>
                                <ul>
                                    <li>Tranh Đôi Uyên Ương</li>
                                </ul>
                            </td>
                            <td>15.000.000₫</td>
                            <td>2024-12-10</td>
                            <td>Đã giao</td>
                            <td>-</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        
        <div class="card">
            <div class="card-header bg-secondary text-white">
                <h4>Lịch Sử Đơn Hàng</h4>
            </div>
            <div class="card-body">
                <table id="orderHistory" class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Mã Đơn Hàng</th>
                            <th>Sản Phẩm</th>
                            <th>Tổng Tiền</th>
                            <th>Ngày Giao</th>
                            <th>Trạng Thái</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>#3</td>
                            <td>
                                <ul>
                                    <li>Tranh Thiên Nhiên</li>
                                </ul>
                            </td>
                            <td>5.000.000₫</td>
                            <td>2024-11-20</td>
                            <td>Đã giao</td>
                        </tr>
                        <tr>
                            <td>#1</td>
                            <td>
                                <ul>
                                    <li>Tranh Hoa Sen</li>
                                </ul>
                            </td>
                            <td>3.500.000₫</td>
                            <td>2024-11-18</td>
                            <td>Đã giao</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    
</div>
        <%@ include file="/partials/footer.jsp" %>

    <script src="/assets/js/header.js"></script>
    <script src="/assets/js/personal.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>