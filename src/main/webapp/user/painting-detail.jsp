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

<div class="container py-4">
    <div class="row">
        <!-- Phần hình ảnh -->
        <div class="col-md-6">
            <div class="card">
                <img src="${p.imageUrl}" alt="${p.title}" class="card-img-top img-fluid">
            </div>
        </div>

        <!-- Phần thông tin -->
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h1 class="card-title h3">${p.title}</h1>
                    <p class="text-muted">Mã sản phẩm: #${p.id}</p>

                    <!-- Thông tin cơ bản -->
                    <div class="mb-3">
                        <p><strong>Họa sĩ:</strong> ${p.artistName}</p>
                        <p><strong>Chủ đề:</strong> ${p.themeName}</p>
                        <p><strong>Mô tả:</strong> ${p.description}</p>
                    </div>

                    <!-- Giá và giảm giá -->
                    <div class="mb-4">
                        <c:choose>
                            <c:when test="${p.discountPercentage > 0}">
                                <div class="d-flex align-items-center gap-2">
                                    <span class="text-muted text-decoration-line-through">${p.price} VNĐ</span>
                                    <span class="h4 text-danger mb-0">${p.price * (1 - p.discountPercentage / 100)} VNĐ</span>
                                    <span class="badge bg-danger">-${p.discountPercentage}%</span>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <span class="h4">${p.price} VNĐ</span>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <!-- Form thêm vào giỏ hàng -->
                    <form action="add-to-cart" method="POST" id="addToCartForm" class="needs-validation" novalidate>


                        <!-- Chọn kích thước -->
                        <div class="mb-3">
                            <label class="form-label"><strong>Kích thước:</strong></label>
                            <div class="row g-2">
                                <c:forEach var="size" items="${p.sizes}">
                                    <div class="col-auto">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="size"
                                                   id="size_${size.sizeDescriptions}"
                                                   value="${size.idSize}"
                                                   data-quantity="${size.quantity}"
                                                ${size.quantity <= 0 ? 'disabled' : ''}>
                                            <label class="form-check-label" for="size_${size.sizeDescriptions}">
                                                    ${size.sizeDescriptions}
                                                <small class="text-muted">(Còn ${size.quantity})</small>
                                            </label>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="invalid-feedback">Vui lòng chọn kích thước</div>
                        </div>

                        <input type="hidden" name="pid" value="${p.id}">
                        <div class="mb-3">
                            <label for="quantity" class="form-label"><strong>Số lượng:</strong></label>
                            <div class="input-group">
                                <button type="button" class="btn btn-outline-secondary" onclick="decrementQuantity()">-</button>
                                <input type="number" class="form-control text-center" id="quantity" name="quantity"
                                       value="1" min="1" required>
                                <button type="button" class="btn btn-outline-secondary" onclick="incrementQuantity()">+</button>
                            </div>
                            <div class="invalid-feedback">Số lượng không hợp lệ</div>
                        </div>

                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-cart-plus me-2"></i>Thêm vào giỏ hàng
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/partials/footer.jsp" %>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const form = document.getElementById('addToCartForm');
        const quantityInput = document.getElementById('quantity');
        const sizeInputs = document.querySelectorAll('input[name="size"]');

        sizeInputs.forEach(input => {
            input.addEventListener('change', function() {
                const maxQuantity = parseInt(this.dataset.quantity);
                quantityInput.max = maxQuantity;
                quantityInput.value = Math.min(quantityInput.value, maxQuantity);
            });
        });


        form.addEventListener('submit', function(event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }

            const selectedSize = document.querySelector('input[name="size"]:checked');
            if (!selectedSize) {
                event.preventDefault();
                alert('Vui lòng chọn kích thước');
                return;
            }

            form.classList.add('was-validated');
        });
    });

    function incrementQuantity() {
        const input = document.getElementById('quantity');
        const max = input.max ? parseInt(input.max) : Infinity;
        const currentValue = parseInt(input.value);
        if (currentValue < max) {
            input.value = currentValue + 1;
        }
    }

    function decrementQuantity() {
        const input = document.getElementById('quantity');
        const currentValue = parseInt(input.value);
        if (currentValue > 1) {
            input.value = currentValue - 1;
        }
    }
</script>
</body>
</html>