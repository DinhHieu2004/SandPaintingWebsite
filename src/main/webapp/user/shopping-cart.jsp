<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <link rel="stylesheet" href="/assets/css/footer.css">
    <link rel="stylesheet" href="/assets/css/header.css">
    <link rel="stylesheet" href="/assets/css/shopping-cart.css">

</head>
<body>
    <div id="header-container"></div>
    <div  class="page-title-inner">
        <h5 > Giỏ hàng</h5>
    </div>
    <div class="container my-5">
        

        <!-- Cart Items -->
        <div class="card mb-4">
            <div class="card-header">
                <h5>Giỏ hàng của bạn</h5>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead class="table-light">
                        <tr>
                            <th>#</th>
                            <th>Ảnh</th>
                            <th>Tên Tranh</th>
                            <th>Giá</th>
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody id="cart-items">
                        <!-- Các sản phẩm sẽ được thêm vào đây từ JavaScript -->
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="4" class="text-end">Tổng tiền</th>
                            <th id="total-price"></th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        

        <div class="d-flex justify-content-between">
            <a href="artWork.jsp" class="btn btn-secondary">Tiếp tục mua hàng</a>
            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#paymentModal">Mua hàng</button>
        </div>
    </div>


    <div class="modal fade" id="paymentModal" tabindex="-1" aria-labelledby="paymentModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h5 class="modal-title" id="paymentModalLabel">Thanh toán</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <!-- Modal Body -->
                <div class="modal-body">
                    <form>
                        <!-- Tổng tiền thanh toán -->
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
    <div id="auth"></div>

    <div id="footer-container"></div>
    <script src="/assets/js/shopping-cart.js"></script>
    <script src="/assets/js/header.js"></script>
    <script src="/assets/js/painting-detail.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
