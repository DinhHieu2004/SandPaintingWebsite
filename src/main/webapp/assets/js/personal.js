$(document).ready(function () {
    let orderStatus = null;
    $.ajax({
        url: 'user/orders',
        method: 'GET',
        dataType: 'json',
        success: function (response) {
            const currentOrders = response.currentOrders;
            const currentTableBody = $('#currentOrders tbody');
            currentOrders.forEach(order => {
                const row = `
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.totalAmount}₫</td>
                        <td>${order.orderDate}</td>
                        <td>${order.status}</td>
                        <td><button class="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target="#orderDetailsModal" data-order-id="${order.id}">Xem Chi Tiết</button></td>
                    </tr>`;
                currentTableBody.append(row);
            });
            $('#currentOrders').DataTable();

            const previousOrders = response.previousOrders;
            const previousTableBody = $('#orderHistory tbody');
            previousOrders.forEach(order => {
                const row = `
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.totalAmount}₫</td>
                        <td>${order.orderDate}</td>
                        <td>${order.deliveryDate}</td>
                        <td>${order.status}</td>
                        <td><button class="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target="#orderDetailsModal" data-order-id="${order.id}">Xem Chi Tiết</button></td>
                    </tr>`;
                previousTableBody.append(row);
            });
            $('#orderHistory').DataTable();
        },
        error: function () {
            alert('Lỗi khi tải dữ liệu đơn hàng.');
        }
    });

    let currentOrderId = null;

    $('#orderDetailsModal').on('show.bs.modal', function (event) {
        const button = $(event.relatedTarget);
        const orderId = button.data('order-id');
        const isFromPreviousOrders = button.closest('table').attr('id') === 'orderHistory';
        if (currentOrderId !== orderId) {
            currentOrderId = orderId;
            const modalBody = $('#orderDetailsBody');
            const modalInfo = $('#orderRecipientInfo');
            const modelPrice = $(`#totalPrice`)
            modalInfo.empty();
            modalBody.empty();

            $.ajax({
                url: `order-detail?orderId=${orderId}`,
                method: 'GET',
                dataType: 'json',
                success: function (response) {


                    console.log('Response from order-detail:', response); // Thêm log để debug

                    if (response && response) {
                        const order = response;
                        orderStatus = order.status;
                        modalInfo.html(`
                <p><strong>Tên người nhận:</strong> ${order.recipientName}</p>
                <p><strong>Số điện thoại:</strong> ${order.recipientPhone}</p>
                <p><strong>Địa chỉ nhận hàng:</strong> ${order.deliveryAddress}</p>
            `);
                        modelPrice.html(`<p><strong>Tổng trả:</strong> ${order.totalAmount}</p>`)
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
                url: `order/order-items?orderId=${orderId}`,
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
                                <td>${product.name}</td>
                                <td>${product.sizeDescription}</td>
                                <td>${product.quantity}</td>
                                <td>${product.price}₫</td>
                                <td>${isFromPreviousOrders ?
                            `<button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#reviewModal" data-product-id="${product.id}">Đánh Giá</button>`
                            : ''}</td>
                                 </tr>`;
                        modalBody.append(row);
                    });
                },
                error: function () {
                    alert('Lỗi khi tải chi tiết đơn hàng.');
                }
            });
        }
    });

    // Xử lý khi modal đóng để reset orderId
    $('#orderDetailsModal').on('hidden.bs.modal', function () {
        currentOrderId = null;
    });
});
