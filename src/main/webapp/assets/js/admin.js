$(document).ready(function () {
    $('.nav-button').click(function () {
        var page = $(this).data('page');

        loadPageContent(page);
    });

    function loadPageContent(page) {
        $.ajax({
            url: `${page}.jsp`,  // Gửi yêu cầu tới trang tương ứng (ví dụ: dashboard.jsp, orders.jsp)
            method: 'GET',
            success: function (data) {
                $('#mainContent').html(data);
            },
            error: function () {
                alert('Có lỗi xảy ra khi tải nội dung!');
            }
        });
    }
});
