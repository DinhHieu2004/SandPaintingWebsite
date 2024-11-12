$(document).ready(function(){
    $("#footer-container").load("footer.html");


    console.log("jQuery đã tải"); // Kiểm tra xem jQuery có hoạt động không
    $("#header").load("header.html", function(response, status, xhr) {
        if (status == "error") {
            console.log("Lỗi khi load header:", xhr.status, xhr.statusText);
        } else {
            console.log("Header đã được tải thành công");
        }
    });
});

