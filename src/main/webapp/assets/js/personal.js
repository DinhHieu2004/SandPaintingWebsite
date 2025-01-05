$(document).ready(function () {
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

    $('#orderDetailsModal').on('show.bs.modal', function (event) {
        const button = $(event.relatedTarget);
        const orderId = button.data('order-id');
        const modalBody = $('#orderDetailsBody');
        modalBody.empty();

        $.ajax({
            url: `orders/${orderId}`,
            method: 'GET',
            dataType: 'json',
            success: function (details) {
                details.products.forEach(product => {
                    const row = `
                        <tr>
                            <td>${product.name}</td>
                            <td>${product.size}</td>
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
    });
});
