<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Page Title</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <!--  <link rel='stylesheet' type='text/css' media='screen' href='main.css'>-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <link rel="stylesheet" href="assets/css/footer.css">
    <link rel="stylesheet" href="assets/css/header.css">
    <link rel="stylesheet" href="assets/css/painter.css">

</head>

<body>
<%@ include file="/partials/header.jsp" %>
    <div  class="page-title-inner">
        <h5 >Thông tin họa sĩ </h5>
    </div>
    <div class="container my-5">

        <div class="row">
            <div class="col-md-4">
                <img src="${data.photoUrl}"
                    alt="Họa Sĩ" class="artist-photo">
            </div>

            <div class="col-md-8">
                <div class="artist-info">
                    <h2 class="artist-name">${data.name}</h2>
                    <div class="artist-details">
                        <p><strong>Ngày Sinh:</strong>${data.birthDate}</p>
                        <p><strong>Quốc Tịch:</strong>${data.nationality}</p>
                    </div>

                    <div class="artist-bio">
                        <h5>Tiểu sử</h5>
                        <p>${data.bio}</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Artist's Artworks -->
        <div class="container-product">
            <div class="row g-4 g-2 col-10" id="artworkGallery">
                <c:forEach var="p" items="${listP}">
                <div class="col-6 col-md-3">
                    <div class="card artwork-card">
                        <a href="painting-detail?pid=${p.id}" class="card-link"></a>
                        <img src="${p.imageUrl}"
                            class="card-img-top artwork-image" alt="${p.title}">

                        <div class="card-body">
                            <h5 class="card-title">${p.title}</h5>
                            <p class="card-text">
                                <strong>Chủ đề:</strong>${p.themeName}<br>
                            </p>
                            <div class="price-discount">
                                <span class="text-muted original-price">600.000 VNĐ</span>
                                <span class="badge bg-success discount-badge">-17%</span>
                            </div>
                            <div>
                                <span class="text-danger sale-price">500.000 VNĐ</span>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
        </div>
        <div class="text-center">
            <a href="painting-list-of-painter?id=${data.id}" class="btn ">Xem Thêm Tác Phẩm</a>
        </div>
    </div>
<%@ include file="/partials/footer.jsp" %>
<%@ include file="/partials/authModal.jsp" %>

    <script src="/assets/js/painter.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>