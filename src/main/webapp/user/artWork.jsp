<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tranh Cát Nghệ Thuật</title>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <link rel="stylesheet" href="assets/css/artWork.css">
    <link rel="stylesheet" href="assets/css/footer.css">
    <link rel="stylesheet" href="assets/css/header.css">


</head>

<body>
<%@ include file="/partials/header.jsp" %>
    <div  class="page-title-inner">
        <h5 > Tác phẩm </h5>
    </div>
    <div id="content-wrapper">
        <div class="container_content">
            <div class="filter-section col-3">
               
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h5 class="filter-title m-0">Bộ lọc sản phẩm</h5>
                    <span class="filter-reset" id="resetFilters">Đặt lại</span>
                </div>
                <form id="filterForm" method="GET" action="artwork">

                <div class="filter-group">
                    <h6 class="mb-3">Giá (VNĐ)</h6>
                    <div class="price-range d-flex align-items-center">
                        <div class="input-group price-input me-2">
                            <input type="number" class="form-control" id="minPrice" name="minPrice" placeholder="Từ">
                            <span class="input-group-text">đ</span>
                        </div>
                        <span class="price-separator mx-2">-</span>
                        <div class="input-group price-input">
                            <input type="number" class="form-control" id="maxPrice" name="maxPrice" placeholder="Đến">
                            <span class="input-group-text">đ</span>
                        </div>
                    </div>
                </div>
                <!-- Lọc theo kích thước -->
                <div class="filter-group">
                    <div class="filter-group">
                        <h6 class="mb-3">Kích thước</h6>
                        <c:forEach var="size" items="${paintingSizes}">
                            <div class="form-check mb-2">
                                <input class="form-check-input" type="checkbox" value="${size.idSize}" id="${size.idSize}" name="size">
                                <label class="form-check-label" for="${size.sizeDescriptions}">${size.sizeDescriptions}</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!-- Lọc theo chủ đề -->
                <div class="filter-group">
                    <h6 class="mb-3">Chủ đề</h6>
                    <c:forEach var="theme" items="${themes}">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" value="${theme.id}" id="${theme.id}" name="theme">
                            <label class="form-check-label" for="${theme.themeName}">${theme.themeName}</label>
                        </div>
                    </c:forEach>
                </div>
                <!-- Lọc theo họa sĩ -->
                <div class="filter-group">
                    <h6 class="mb-3">Họa sĩ</h6>
                    <c:forEach var="artist" items="${artists}">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" value="${artist.id}" id="${artist.id}" name="artist">
                            <label class="form-check-label" for="${artist.name}">${artist.name}</label>
                        </div>
                    </c:forEach>
                </div>

                <button class="btn w-100" id="applyFilters">Áp dụng</button>
                </form>
            </div>
            <div class="row g-4 g-2 col-10" id="artworkGallery">
                <c:forEach var="p" items="${data}">
                    <div class="col-6 col-md-3">
                        <div class="card artwork-card">
                            <a href="painting-detail?pid=${p.id}" class="card-link"></a>
                            <img src="${p.imageUrl}" class="card-img-top artwork-image" alt="${p.title}">

                            <div class="card-body">
                                <h5 class="card-title">${p.title}</h5>
                                <p class="card-text">
                                    <strong>Họa Sĩ:</strong> ${p.artistName}<br>
                                    <strong>Chủ đề:</strong> ${p.themeName}<br>
                                </p>
                                <div class="price-discount">
                                    <c:choose>
                                        <c:when test="${p.discountPercentage > 0}">
                            <span class="text-muted original-price">
                                <del>${p.price} VNĐ</del>
                            </span>
                                            <span class="badge bg-success discount-badge">-${p.discountPercentage}%</span>
                                            <div>
                                <span class="text-danger sale-price">
                                    ${(p.price * (1 - p.discountPercentage / 100))} VNĐ
                                </span>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div>
                                                <span class="text-muted original-price">${p.price} VNĐ</span>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
             </c:forEach>
            </div>
          
        </div>
    </div>
  
    <nav aria-label="Page navigation" class="mt-4">
        <ul class="pagination justify-content-center">
            <li class="page-item disabled" id="prevPage">
                <a class="page-link" href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li class="page-item" id="nextPage">
                <a class="page-link" href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
<%@ include file="/partials/footer.jsp" %>

<%@ include file="/partials/authModal.jsp" %>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</html>