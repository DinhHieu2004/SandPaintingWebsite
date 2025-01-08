$(document).ready(function () {
    let orderStatus = null;

    $('#orderDetailsModal').on('show.bs.modal', function (event) {
        const button = $(event.relatedTarget);
        const orderId = button.data('order-id');
        const modalBody = $('#orderDetailsBody');
        const modalInfo = $('#orderRecipientInfo');
        const modelPrice = $(`#totalPrice`)
        const modalStatus = $('#orderStatus');
        const statusSelect = $('#statusSelect');
        const updateStatusBtn = $('#updateStatusBtn');
        modalInfo.empty();
        modalBody.empty();
        modalStatus.empty();

        $.ajax({
            url: `../order-detail?orderId=${orderId}`,
            method: 'GET',
            dataType: 'json',
            success: function (response) {


                console.log('Response from order-detail:', response);

                if (response && response) {
                    const order = response;
                    orderStatus = order.status;
                    modalInfo.html(`
                <p><strong>Tên người nhận:</strong> ${order.recipientName}</p>
                <p><strong>Số điện thoại:</strong> ${order.recipientPhone}</p>
                <p><strong>Địa chỉ nhận hàng:</strong> ${order.deliveryAddress}</p>
            `);
                    modelPrice.html(`<p><strong>Tổng trả:</strong> ${order.totalAmount}</p>`)
                    modalStatus.text(orderStatus);
                    statusSelect.val(orderStatus);
                } else {
                    modalInfo.html('<p>Không tìm thấy thông tin đơn hàng</p>');
                }

            },
            error: function (xhr, status, error) {
                console.error('Error:', error);
                console.error('Status:', status);
                console.error('Response:', xhr.responseText);
                modalInfo.html('<p>Có lỗi khi tải thông tin đơn hàng</p>');
            }
        });

        $.ajax({
            url: `../order/order-items?orderId=${orderId}`,
            method: 'GET',
            dataType: 'json',
            success: function (details) {
                if (details.length === 0) {
                    modalBody.append('<tr><td colspan="4">Không có chi tiết đơn hàng.</td></tr>');
                    return;
                }
                console.log(orderStatus)
                details.forEach(product => {
                    const row = `
                            <tr>
                                <td>${product.paintingId}</td>
                                <td>${product.name}</td>
                                <td><img src="${product.img}" alt="${product.name}" width="50"></td>
                                <td>${product.sizeDescription}</td>
                                <td>${product.quantity}</td>
                                <td>${product.price}₫</td>
                             
                                 </tr>`;
                    modalBody.append(row);
                });
            },
            error: function () {
                alert('Lỗi khi tải chi tiết đơn hàng.');
            }

        });
        updateStatusBtn.on('click', function () {
            const newStatus = statusSelect.val();

            $.ajax({
                url: `../update-order-status`,
                method: 'POST',
                data: {
                    orderId: orderId,
                    status: newStatus
                },
                success: function (response) {
                    alert('Cập nhật trạng thái thành công');
                    modalStatus.text(newStatus);
                },
                error: function () {
                    alert('Lỗi khi cập nhật trạng thái đơn hàng');
                }
            });
        });
    });
});
