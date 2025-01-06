$(document).ready(function () {
    // Khi nhấn vào các button trong sidebar
    $('.nav-button').click(function () {
        var page = $(this).data('page');  // Lấy dữ liệu trang tương ứng từ data-page

        // Gọi hàm để tải nội dung trang tương ứng
        loadPageContent(page);
    });

    // Hàm tải nội dung trang tương ứng
    function loadPageContent(page) {
        // Dùng AJAX để lấy nội dung của trang từ server
        $.ajax({
            url: `${page}.jsp`,  // Gửi yêu cầu tới trang tương ứng (ví dụ: dashboard.jsp, orders.jsp)
            method: 'GET',
            success: function (data) {
                // Thay thế nội dung của phần "mainContent" bằng nội dung trả về từ server
                $('#mainContent').html(data);
            },
            error: function () {
                alert('Có lỗi xảy ra khi tải nội dung!');
            }
        });
    }
});
