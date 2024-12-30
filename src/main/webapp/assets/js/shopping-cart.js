$(document).ready(function () {
    // Bắt sự kiện khi nhấn nút "Xóa"
    $(".remove-item").click(function (e) {
        e.preventDefault(); // Ngăn chặn việc gửi form mặc định

        const button = $(this);
        const productId = button.data("product-id");
        const sizeId = button.data("size-id");

        // Gửi yêu cầu AJAX đến Servlet
        $.ajax({
            url: "remove-from-cart", // URL servlet của bạn
            type: "POST",
            data: {
                productId: productId,
                sizeId: sizeId,
            },
            success: function (response) {
                if (response.status === "success") {
                    // Xóa dòng sản phẩm khỏi bảng
                    $(`#cart-item-${productId}-${sizeId}`).remove();

                    // Cập nhật tổng tiền
                    if (response.cart.totalPrice) {
                        $("#total-price").text(response.cart.totalPrice.toLocaleString() + " VND");
                    }

                    // Nếu giỏ hàng rỗng, hiển thị thông báo
                    if (!response.cart.items || response.cart.items.length === 0) {
                        $(".card-body").html(`
                            <div class="alert alert-info text-center" role="alert">
                                Giỏ hàng của bạn đang trống.
                            </div>
                        `);
                    }
                    updateMiniCart(response.cart);
                } else {
                    alert("Đã xảy ra lỗi khi xóa sản phẩm khỏi giỏ hàng.");
                }
            },
            error: function () {
                alert("Lỗi kết nối đến máy chủ.");
            },
        });
    });

    function updateMiniCart(cart) {
        const miniCart = $("#mini-cart"); // Selector của giỏ hàng mini
        miniCart.empty(); // Xóa nội dung cũ

        if (cart.items && cart.items.length > 0) {
            // Thêm sản phẩm vào giỏ hàng mini
            cart.items.forEach(item => {
                miniCart.append(`
                    <li>
                        <img src="${item.imageUrl}" alt="${item.productName}" width="30">
                        ${item.productName} - ${item.quantity} x ${item.price.toLocaleString()} VND
                    </li>
                `);
            });
            miniCart.append(`
                <li class="text-end fw-bold">Tổng: ${cart.totalPrice.toLocaleString()} VND</li>
            `);
        } else {
            miniCart.html(`
                <li>Giỏ hàng trống.</li>
            `);
        }
    }

});
