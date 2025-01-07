<%@ page import="com.example.web.dao.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    User currentUser = (session != null) ? (User) session.getAttribute("currentUser") : null;
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <link rel="stylesheet" href="../assets/css/discount.css">
    <link rel="stylesheet" href="../assets/css/footer.css">
    <link rel="stylesheet" href="../assets/css/header.css">
</head>

<body>
<c:choose>
    <c:when test="${not empty currentUser}">
        <%@ include file="/partials/subHeaderAuth.jsp" %>
    </c:when>
    <c:otherwise>
        <%@ include file="/partials/subHeader.jsp" %>
    </c:otherwise>
</c:choose>
    <h4 class="title">CHƯƠNG TRÌNH GIẢM GIÁ ĐẶC BIỆT</h4>
    <p class="sub_title"> Ưu đãi hấp dẫn cho các sản phẩm yêu thích của bạn</p>
    <div id="discount_list">
        <c:forEach var="discount" items="${list}">
        <a href="discount_content.jsp" class="discount_item">
            <img src="${discount.imageUrl}" alt="${discount.discountName}">
            <p class="content" style="color: #e7621b"><strong>${discount.discountName}</strong></p>
            <p class="content">(Áp dụng từ ngày ${discount.startDate} đến ${discount.endDate})</p>
        </a>
        </c:forEach>
    </div>

<%@ include file="/partials/footer.jsp" %>
<%@ include file="/partials/authModal.jsp" %>

</body>
<script src="/assets/js/discount.js"></script>

</html>