$(document).ready(function () {
    $('#addToCartForm').on('submit', function (event) {
        event.preventDefault();

        const formData = $(this).serialize();

        $.ajax({
            url: 'add-to-cart',
            type: 'POST',
            data: formData,
            success: function (response) {
                $('#cartMessage').html(`
                    <div id="alertMessage" class="alert alert-success alert-dismissible fade show" role="alert">
                        <strong>Đã thêm vào giỏ hàng thành công!</strong>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                `);

                setTimeout(() => {
                    $('#alertMessage').fadeOut(500, function () {
                        $(this).remove();
                    });
                }, 1000);
            },
            error: function () {
                $('#cartMessage').html(`
                    <div id="alertMessage" class="alert alert-danger alert-dismissible fade show" role="alert">
                        <strong>Đã xảy ra lỗi, vui lòng thử lại!</strong>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                `);

                setTimeout(() => {
                    $('#alertMessage').fadeOut(500, function () {
                        $(this).remove();
                    });
                }, 1000);
            }
        });
    });

    window.incrementQuantity = function () {
        const input = $('#quantity');
        const max = parseInt(input.attr('max')) || Infinity;
        const currentValue = parseInt(input.val());
        if (currentValue < max) {
            input.val(currentValue + 1);
        }
    };

    window.decrementQuantity = function () {
        const input = $('#quantity');
        const currentValue = parseInt(input.val());
        if (currentValue > 1) {
            input.val(currentValue - 1);
        }
    };
});
