<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Chi tiết giảm giá</title>

  <!-- External Libraries -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom Styles -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/discount_content.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/artWork.css">

</head>
<body>
<%@ include file="/partials/header.jsp" %>

<!-- Title Section -->
<div id="title">
  <div class="content text-center">
    <img src="${discount.imageUrl}" alt="${discount.discountName}" class="img-fluid">
    <h4 style="color: #e7621b">${discount.discountName}</h4>
  </div>
</div>

<!-- Discount Content Section -->
<div id="discount_content" class="container mt-4">
  <h5 class="mb-4">Ưu đãi lên đến ${discount.discountPercentage}% bao gồm các sản phẩm:</h5>
  <div class="row g-4 g-2 col-10" id="artworkGallery">
    <c:forEach var="p" items="${paintings}">
      <div class="col-6 col-md-3">
        <div class="card artwork-card">
          <a href="<c:url value='/painting-detail?pid=${p.id}'/>" class="card-link"></a>
          <img src="${pageContext.request.contextPath}/${p.imageUrl}" class="card-img-top artwork-image" alt="${p.title}" style="width: 100%; height:180px !important;">
          <div class="card-body">
            <h5 class="card-title">${p.title}</h5>
            <p class="card-text">
              <strong>Họa Sĩ:</strong> ${p.artistName}<br>
              <strong>Chủ đề:</strong> ${p.themeName}<br>
              <span class="rating-stars">
                            <c:forEach begin="1" end="5" var="i">
                              <i class="fas fa-star ${i <= p.averageRating ? 'text-warning' : 'text-gray-200'}" style="${i > p.averageRating ? 'color: #e9ecef !important;' : ''}; font-size: 0.875rem;"></i>
                            </c:forEach>
                        </span>
              <span class="ms-1">${p.averageRating}</span>
            </p>
            <div class="price-section">
              <c:choose>
                <c:when test="${p.discountPercentage > 0}">
                  <div class="price-container">
                    <div class="original-price-wrapper">
                                    <span class="text-muted original-price">
                                        <del><f:formatNumber value="${p.price}" type="currency" currencySymbol="VNĐ"/></del>
                                    </span>
                      <span class="badge bg-success discount-badge">-${p.discountPercentage}%</span>
                    </div>
                    <div class="sale-price-wrapper">
                                    <span class="text-danger fw-bold sale-price">
                                        <f:formatNumber value="${p.price * (1 - p.discountPercentage / 100)}" type="currency" currencySymbol="VNĐ"/>
                                    </span>
                    </div>
                  </div>
                </c:when>
                <c:otherwise>
                  <div class="regular-price">
                    <span class="fw-bold"><f:formatNumber value="${p.price}" type="currency" currencySymbol="VNĐ"/></span>
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
<!-- Phân trang -->
<div class="pagination-container text-center mt-4">
  <ul class="pagination justify-content-center">

    <c:if test="${currentPage > 1}">
      <li class="page-item">
        <a class="page-link" href="${pageContext.request.contextPath}/discount_content?id=${discount.id}&page=${currentPage - 1}">&laquo;</a>
      </li>
    </c:if>


    <c:forEach var="i" begin="1" end="${totalPages}">
      <li class="page-item ${i == currentPage ? 'active' : ''}">
        <a class="page-link" href="${pageContext.request.contextPath}/discount_content?id=${discount.id}&page=${i}">${i}</a>
      </li>
    </c:forEach>


    <c:if test="${currentPage < totalPages}">
      <li class="page-item">
        <a class="page-link" href="${pageContext.request.contextPath}/discount_content?id=${discount.id}&page=${currentPage + 1}">&raquo;</a>
      </li>
    </c:if>
  </ul>
</div>
<!-- Discount Period -->
<p class="text-center text-muted">(Ưu đãi áp dụng từ ${discount.startDate} đến hết ngày ${discount.endDate})</p>

<%@ include file="/partials/footer.jsp" %>
<%@ include file="/partials/authModal.jsp" %>

<!-- Custom Scripts -->
<script src="${pageContext.request.contextPath}/assets/js/discount.js"></script>
</body>
</html>