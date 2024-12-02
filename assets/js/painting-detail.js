$(document).ready(function(){
    // Tải header và footer
    $("#footer-container").load("/partials/footer.html");
    $("#header-container").load("/partials/header.html");
});

document.addEventListener("DOMContentLoaded", function () {
    // Nút "Thêm vào giỏ hàng"
    const addToCartButton = document.getElementById("add-to-cart-btn");

    // Lấy thông tin sản phẩm
    const paintingId = document.getElementById("paintingId").innerText;
    const paintingTitle = document.getElementById("paintingTitle").innerText.split(": ")[1];
    const originalPrice = document.getElementById("originalPrice").innerText.replace(/[^0-9]/g, ""); // Lấy giá gốc
    const discountedPrice = document.getElementById("discountedPrice").innerText.replace(/[^0-9]/g, ""); // Lấy giá giảm
    const paintingImage = document.getElementById("paintingImage").src;

    // Lưu giỏ hàng trong localStorage
    let cart = JSON.parse(localStorage.getItem("cart")) || [];

    // Hàm thêm sản phẩm vào giỏ hàng
    addToCartButton.addEventListener("click", function () {
        const product = {
            id: paintingId,
            title: paintingTitle,
            originalPrice: parseInt(originalPrice),
            discountedPrice: parseInt(discountedPrice),
            image: paintingImage,
        };

        // Thêm sản phẩm vào giỏ
        cart.push(product);
        localStorage.setItem("cart", JSON.stringify(cart));

        alert("Đã thêm vào giỏ hàng!");
    });
});