document.querySelector("#submitPayment").addEventListener("click", function () {
    const paymentMethod = document.querySelector("#paymentMethod").value;

    if (!paymentMethod) {
        alert("Vui lòng chọn phương thức thanh toán!");
        return;
    }

    $.ajax({
        url: "checkout",
        type: "POST",
        data: { paymentMethod: paymentMethod },
        success: function (response) {
            alert(response);
            location.reload();
        },
        error: function (xhr) {
            if (xhr.status === 401) {
                alert("Bạn cần đăng nhập để thực hiện thanh toán!");
                $("#loginModal").modal("show");
            } else if (xhr.status === 400) {
                alert("Giỏ hàng của bạn đang trống!");
            } else {
                alert("Đã xảy ra lỗi trong quá trình thanh toán. Vui lòng thử lại!");
            }
        }
    });
});
