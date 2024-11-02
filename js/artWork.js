$(document).ready(function(){
    console.log("jQuery đã tải"); // Kiểm tra xem jQuery có hoạt động không
    $("#header").load("header.html", function(response, status, xhr) {
        if (status == "error") {
            console.log("Lỗi khi load header:", xhr.status, xhr.statusText);
        } else {
            console.log("Header đã được tải thành công");
        }
    });
});

function addProduct(title, artist, size, price, imageUrl) {
    let productCol = $(`
        <div class="col-6 col-md-3">
            <div class="card artwork-card">
                <img src="${imageUrl}" class="card-img-top artwork-image" alt="${title}">
                <div class="card-body">
                    <h5 class="card-title">${title}</h5>
                    <p class="card-text">
                        <strong>Họa Sĩ:</strong> ${artist}<br>
                        <strong>Kích Thước:</strong> ${size}<br>
                        <strong>Giá:</strong> ${price} VNĐ
                    </p>
                    <button class="btn btn-primary w-100 mt-2">Đặt Mua</button>
                </div>
            </div>
        </div>
    `);
    $('#artworkGallery').append(productCol);
}