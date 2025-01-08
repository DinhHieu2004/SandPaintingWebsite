<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa; /* Nền sáng */
        }

        .sidebar {
            height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
            width: 250px;
            background-color: #343a40; /* Sidebar màu xám đậm */
            color: white;
            padding: 20px 10px;
        }

        .sidebar a {
            color: white;
            text-decoration: none;
            display: block;
            padding: 10px;
            margin-bottom: 5px;
            border-radius: 5px;
        }

        .sidebar a:hover {
            background-color: #495057;
        }

        .content {
            margin-left: 260px;
            padding: 20px;
        }
        .container{
            margin: 0;
            padding: 0;
        }
        .card {
            border: none;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .gap-4 {
            gap: 1.5rem;
        }
        .chart-container {
            height: 300px;
        }
    </style>
</head>
<body>
<!-- Sidebar -->
<%@ include file="/admin/sidebar.jsp" %>

<!-- Main Content -->
<div class="content">
    <div class="container">
        <h2 class="mb-4">Tổng quan</h2>

        <!-- Cards for statistics -->
        <div class="row mb-4">
            <!-- Tổng doanh thu -->
            <div class="col-md-3">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Tổng Doanh Thu</h5>
                        <p class="card-text fs-4 text-success">${totalRevenue} VNĐ</p>
                    </div>
                </div>
            </div>
            <!-- Tổng đơn hàng -->
            <div class="col-md-3">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Tổng Đơn Hàng</h5>
                        <p class="card-text fs-4">${totalOrders}</p>
                    </div>
                </div>
            </div>
            <!-- Tổng người dùng -->
            <div class="col-md-3">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Tổng Người Dùng</h5>
                        <p class="card-text fs-4">${totalUsers}</p>
                    </div>
                </div>
            </div>
            <!-- Tổng sản phẩm -->
            <div class="col-md-3">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Tổng Sản Phẩm</h5>
                        <p class="card-text fs-4">${totalProducts}</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Biểu đồ Doanh Thu Theo Nghệ Sĩ -->
        <div class="row mb-4">
            <div class="col-md-6">
                <h4 class="mb-3">Doanh Thu Theo Nghệ Sĩ</h4>
                <div class="chart-container">
                    <canvas id="revenueByArtistChart"></canvas>
                </div>
            </div>

            <!-- Biểu đồ Trạng Thái Đơn Hàng -->
            <div class="col-md-6">
                <h4 class="mb-3">Trạng Thái Đơn Hàng</h4>
                <div class="chart-container">
                    <canvas id="orderStatusChart"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Chart.js scripts -->
<script>
    // Dữ liệu cho biểu đồ Doanh Thu Theo Nghệ Sĩ
    const revenueByArtistData = {
        labels: [
            <c:forEach var="entry" items="${revenueByArtist}">
            "${entry.key}",
            </c:forEach>
        ],
        datasets: [{
            label: 'Doanh Thu (VNĐ)',
            data: [
                <c:forEach var="entry" items="${revenueByArtist}">
                ${entry.value},
                </c:forEach>
            ],
            backgroundColor: '#007bff',
            borderColor: '#0056b3',
            borderWidth: 1
        }]
    };

    const revenueByArtistConfig = {
        type: 'bar',
        data: revenueByArtistData,
        options: {
            responsive: true,
            plugins: {
                legend: {
                    display: false
                }
            },
            scales: {
                x: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Nghệ Sĩ'
                    }
                },
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Doanh Thu (VNĐ)'
                    }
                }
            }
        }
    };

    // Biểu đồ trạng thái đơn hàng
    const orderStatusData = {
        labels: [
            <c:forEach var="status" items="${orderStatusCount}">
            "${status.key}",
            </c:forEach>
        ],
        datasets: [{
            data: [
                <c:forEach var="status" items="${orderStatusCount}">
                ${status.value},
                </c:forEach>
            ],
            backgroundColor: ['#007bff', '#28a745', '#ffc107', '#dc3545']
        }]
    };

    const orderStatusConfig = {
        type: 'doughnut',
        data: orderStatusData,
        options: {
            plugins: {
                legend: {
                    position: 'bottom'
                }
            }
        }
    };

    // Render biểu đồ
    new Chart(document.getElementById('revenueByArtistChart'), revenueByArtistConfig);
    new Chart(document.getElementById('orderStatusChart'), orderStatusConfig);
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
