<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Tranh</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
    <link rel="stylesheet" href="/assets/css/footer.css">
    <link rel="stylesheet" href="/assets/css/header.css">
    <link rel="stylesheet" href="assets/css/painting-detail.css">
   
</head>

<body>
<%@ include file="/partials/header.jsp" %>

    <div id="content-wrapper">
        <div class="container_content">

            <div class="row">
                <div class="col-md-6">
                    <img id="paintingImage"
                        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpp4FpVhylNpwTVddcn3b0SHdb5ej8M7uvfQ&s"
                        alt="Painting Image" class="img-fluid">
                </div>

                <div class="col-md-6">
                    <h2 id="paintingId">#: ${p.id}</h2>
                    <h2 id="paintingTitle">Tên: ${p.title}</h2>
                    <p id="paintingTheme">Chủ đề: ${p.themeName}</p>
                    <p id="artist">Họa sĩ: ${p.artistName}</p>
                    <p id="paintingDescription">Mô tả: ${p.description}</p>

                    <div>
                        <strong>Kích thước:</strong>
                        <ul>
                            <c:forEach var="size" items="${p.sizes}">
                                <li>${size.sizeDescriptions} - Số lượng: ${size.quantity}</li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div>
                        <c:choose>
                            <c:when test="${p.discountPercentage > 0}">
                                <p><strong>Giá gốc:</strong> <span id="originalPrice" class="text-muted" style="text-decoration: line-through;">${p.price} VND</span></p>
                                <p><strong>Giá giảm:</strong> <span id="discountedPrice" class="text-danger">${p.price * (1 - p.discountPercentage / 100)} VND</span></p>
                                <p><span id="discountPercentage" class="badge bg-success">Giảm ${p.discountPercentage}%</span></p>
                            </c:when>
                            <c:otherwise>
                                <p><strong>Giá:</strong> <span id="originalPrice">${p.price} VND</span></p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                   
                    <button class="btn btn-primary" id="add-to-cart-btn"> Thêm vào giỏ hàng </button>                     
                       <button id="buyNow" class="btn btn-primary" style="border:1px solid black;" data-bs-toggle="modal" data-bs-target="#paymentModal" >Mua ngay</button>
                </div>
            </div>         
        </div>
        </div>
    </div>


<%@ include file="/partials/footer.jsp" %>

    <div class="modal fade" id="paymentModal" tabindex="-1" aria-labelledby="paymentModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="paymentModalLabel">Thanh toán</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="mb-3">
                            <label for="totalAmount" class="form-label fw-bold">Tổng tiền thanh toán:</label>
                            <div id="totalAmount">$400.000-vnd</div>
                        </div>
    
                        <div class="mb-3">
                            <label for="paymentMethod" class="form-label">Chọn phương thức thanh toán:</label>
                            <select class="form-select" id="paymentMethod" required>
                                <option value="" disabled selected>Chọn...</option>
                                <option value="credit_card">online</option>
                                <option value="cod">Thanh toán khi nhận hàng (COD)</option>
                            </select>
                        </div>
    
                        <div class="mb-3">
                            <label for="shippingAddress" class="form-label">Địa chỉ nhận hàng:</label>
                            <textarea class="form-control" id="shippingAddress" rows="3" placeholder="Nhập địa chỉ nhận hàng..." required></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="button" class="btn btn-primary">Xác nhận</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="paymentModal" tabindex="-1" aria-labelledby="paymentModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="paymentModalLabel">Thanh toán</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="mb-3">
                            <label for="totalAmount" class="form-label fw-bold">Tổng tiền thanh toán:</label>
                            <div id="totalAmount">$400.000-vnd</div>
                        </div>
    
                        <div class="mb-3">
                            <label for="paymentMethod" class="form-label">Chọn phương thức thanh toán:</label>
                            <select class="form-select" id="paymentMethod" required>
                                <option value="" disabled selected>Chọn...</option>
                                <option value="credit_card">Thẻ tín dụng/Thẻ ghi nợ</option>
                                <option value="cod">Thanh toán khi nhận hàng (COD)</option>
                                <option value="bank_transfer">Chuyển khoản ngân hàng</option>
                            </select>
                        </div>
    
                        <div class="mb-3">
                            <label for="shippingAddress" class="form-label">Địa chỉ nhận hàng:</label>
                            <textarea class="form-control" id="shippingAddress" rows="3" placeholder="Nhập địa chỉ nhận hàng..." required></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="button" class="btn btn-primary">Xác nhận</button>
                </div>
            </div>
        </div>
    </div>
<%@ include file="/partials/authModal.jsp" %>
    
</body>
<script src="/assets/js/painting-detail.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src =""> </script>
</html>
