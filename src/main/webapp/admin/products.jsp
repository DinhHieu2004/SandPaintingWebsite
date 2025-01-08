<table class="table table-bordered table-striped">
  <thead>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="product" items="${products}">
    <tr>
      <td>${product.id}</td>
      <td>${product.name}</td>
      <td>${product.price}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
