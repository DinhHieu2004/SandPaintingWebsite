<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sản phẩm giảm giá</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <h2>Sản phẩm trong chương trình giảm giá</h2>
  <table class="table table-bordered">
    <thead>
    <tr>
      <th>ID</th>
      <th>Tên</th>
      <th>Họa sĩ</th>
      <th>Giá</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="painting" items="${paintings}">
      <tr>
        <td>${painting.id}</td>
        <td>${painting.name}</td>
        <td>${painting.artist}</td>
        <td>${painting.price}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>

