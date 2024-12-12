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

    <link rel="stylesheet" href="/assets/css/artWork.css">
    <link rel="stylesheet" href="/assets/css/footer.css">
    <link rel="stylesheet" href="/assets/css/header.css">


</head>

<body>
    <div id="header-container"></div>
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

                <!-- Lọc theo giá -->
                <div class="filter-group">
                    <h6 class="mb-3">Giá (VNĐ)</h6>
                    <div class="price-range d-flex align-items-center">
                        <div class="input-group price-input me-2">
                            <input type="number" class="form-control" id="minPrice" placeholder="Từ">
                            <span class="input-group-text">đ</span>
                        </div>
                        <span class="price-separator mx-2">-</span>
                        <div class="input-group price-input">
                            <input type="number" class="form-control" id="maxPrice" placeholder="Đến">
                            <span class="input-group-text">đ</span>
                        </div>
                    </div>
                </div>
                <!-- Lọc theo kích thước -->
                <div class="filter-group">
                    <h6 class="mb-3">Kích thước</h6>
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="checkbox" value="40x60" id="size1">
                        <label class="form-check-label" for="size1">40x60 cm</label>
                    </div>
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="checkbox" value="50x70" id="size2">
                        <label class="form-check-label" for="size2">50x70 cm</label>
                    </div>
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="checkbox" value="60x80" id="size3">
                        <label class="form-check-label" for="size3">60x80 cm</label>
                    </div>
                </div>
                <!-- Lọc theo chủ đề -->
                <div class="filter-group">
                    <h6 class="mb-3">Chủ đề</h6>
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="checkbox" value="scenery" id="scenery">
                        <label class="form-check-label" for="scenery">Phong cảnh</label>
                    </div>
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="checkbox" value="portrait" id="portrait">
                        <label class="form-check-label" for="portrait">Chân dung</label>
                    </div>
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="checkbox" value="still_life" id="still_life">
                        <label class="form-check-label" for="size3">Tĩnh vật</label>
                    </div>
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="checkbox" value="abstract" id="abstract">
                        <label class="form-check-label" for="">Trừu tượng</label>
                    </div>
                </div>
                <!-- Lọc theo họa sĩ -->
                <div class="filter-group">
                    <h6 class="mb-3">Họa sĩ</h6>
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="checkbox" value="NguyenVanA" id="artist1">
                        <label class="form-check-label" for="artist1">Nguyễn Tiến</label>
                    </div>
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="checkbox" value="TranVanB" id="artist2">
                        <label class="form-check-label" for="artist2">Quỳnh Hoa</label>
                    </div>
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="checkbox" value="LeVanC" id="artist3">
                        <label class="form-check-label" for="artist3">Nguyễn Nhật Anh</label>
                    </div>
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="checkbox" value="PhamVanD" id="artist4">
                        <label class="form-check-label" for="artist4">Nguyễn Thế Nhật Quang</label>
                    </div>
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="checkbox" value="PhamVanD" id="artist4">
                        <label class="form-check-label" for="artist4">Nguyễn Anh Vũ</label>
                    </div>
                </div>

                <button class="btn w-100" id="applyFilters">Áp dụng</button>
            </div>
            <div class="row g-4 g-2 col-10" id="artworkGallery">
                <div class="col-6 col-md-3">
                    <div class="card artwork-card">
                        <a href="painting-detail.jsp" class="card-link"></a>
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpp4FpVhylNpwTVddcn3b0SHdb5ej8M7uvfQ&s"
                            class="card-img-top artwork-image" alt="Phong Cảnh Biển">
                
                        <div class="card-body">
                            <h5 class="card-title">Phong Cảnh Biển</h5>
                            <p class="card-text">
                                <strong>Họa Sĩ:</strong> Nguyễn Văn A<br>
                                <strong>Kích Thước:</strong> 40x60 cm<br>
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

                <div class="col-6 col-md-3">
                    <div class="card artwork-card">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSthr50ZOqHVwMkrYSWSSjagIK9sAaHzr-kJg&s"
                            class="card-img-top artwork-image" alt="Phong Cảnh Biển">
                        <div class="card-body">
                            <h5 class="card-title">Phong Cảnh Biển</h5>
                            <p class="card-text">
                                <strong>Họa Sĩ:</strong> Nguyễn Văn A<br>
                                <strong>Kích Thước:</strong> 40x60 cm<br>
                                <strong>Giá:</strong> 500.000 VNĐ
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-6 col-md-3">
                    <div class="card artwork-card">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSthr50ZOqHVwMkrYSWSSjagIK9sAaHzr-kJg&s"
                            class="card-img-top artwork-image" alt="Phong Cảnh Biển">
                        <div class="card-body">
                            <h5 class="card-title">Phong Cảnh Biển</h5>
                            <p class="card-text">
                                <strong>Họa Sĩ:</strong> Nguyễn Văn A<br>
                                <strong>Kích Thước:</strong> 40x60 cm<br>
                                <strong>Giá:</strong> 500.000 VNĐ
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-6 col-md-3">
                    <div class="card artwork-card">
                        <a href="painting-detail.jsp" class="card-link"></a>
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpp4FpVhylNpwTVddcn3b0SHdb5ej8M7uvfQ&s"
                            class="card-img-top artwork-image" alt="Phong Cảnh Biển">
                
                        <div class="card-body">
                            <h5 class="card-title">Phong Cảnh Biển</h5>
                            <p class="card-text">
                                <strong>Họa Sĩ:</strong> Nguyễn Văn A<br>
                                <strong>Kích Thước:</strong> 40x60 cm<br>
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
                <div class="col-6 col-md-3">
                    <div class="card artwork-card">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSthr50ZOqHVwMkrYSWSSjagIK9sAaHzr-kJg&s"
                            class="card-img-top artwork-image" alt="Phong Cảnh Biển">
                        <div class="card-body">
                            <h5 class="card-title">Phong Cảnh Biển</h5>
                            <p class="card-text">
                                <strong>Họa Sĩ:</strong> Nguyễn Văn A<br>
                                <strong>Kích Thước:</strong> 40x60 cm<br>
                                <strong>Giá:</strong> 500.000 VNĐ
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-6 col-md-3">
                    <div class="card artwork-card">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSthr50ZOqHVwMkrYSWSSjagIK9sAaHzr-kJg&s"
                            class="card-img-top artwork-image" alt="Phong Cảnh Biển">
                        <div class="card-body">
                            <h5 class="card-title">Phong Cảnh Biển</h5>
                            <p class="card-text">
                                <strong>Họa Sĩ:</strong> Nguyễn Văn A<br>
                                <strong>Kích Thước:</strong> 40x60 cm<br>
                                <strong>Giá:</strong> 500.000 VNĐ
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-6 col-md-3">
                    <div class="card artwork-card">
                        <a href="painting-detail.jsp" class="card-link"></a>
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpp4FpVhylNpwTVddcn3b0SHdb5ej8M7uvfQ&s"
                            class="card-img-top artwork-image" alt="Phong Cảnh Biển">
                
                        <div class="card-body">
                            <h5 class="card-title">Phong Cảnh Biển</h5>
                            <p class="card-text">
                                <strong>Họa Sĩ:</strong> Nguyễn Văn A<br>
                                <strong>Kích Thước:</strong> 40x60 cm<br>
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
                <div class="col-6 col-md-3">
                    <div class="card artwork-card">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSthr50ZOqHVwMkrYSWSSjagIK9sAaHzr-kJg&s"
                            class="card-img-top artwork-image" alt="Phong Cảnh Biển">
                        <div class="card-body">
                            <h5 class="card-title">Phong Cảnh Biển</h5>
                            <p class="card-text">
                                <strong>Họa Sĩ:</strong> Nguyễn Văn A<br>
                                <strong>Kích Thước:</strong> 40x60 cm<br>
                                <strong>Giá:</strong> 500.000 VNĐ
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-6 col-md-3">
                    <div class="card artwork-card">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSthr50ZOqHVwMkrYSWSSjagIK9sAaHzr-kJg&s"
                            class="card-img-top artwork-image" alt="Phong Cảnh Biển">
                        <div class="card-body">
                            <h5 class="card-title">Phong Cảnh Biển</h5>
                            <p class="card-text">
                                <strong>Họa Sĩ:</strong> Nguyễn Văn A<br>
                                <strong>Kích Thước:</strong> 40x60 cm<br>
                                <strong>Giá:</strong> 500.000 VNĐ
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-6 col-md-3">
                    <div class="card artwork-card">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSthr50ZOqHVwMkrYSWSSjagIK9sAaHzr-kJg&s"
                            class="card-img-top artwork-image" alt="Phong Cảnh Biển">
                        <div class="card-body">
                            <h5 class="card-title">Phong Cảnh Biển</h5>
                            <p class="card-text">
                                <strong>Họa Sĩ:</strong> Nguyễn Văn A<br>
                                <strong>Kích Thước:</strong> 40x60 cm<br>
                                <strong>Giá:</strong> 500.000 VNĐ
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-6 col-md-3">
                    <div class="card artwork-card">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSthr50ZOqHVwMkrYSWSSjagIK9sAaHzr-kJg&s"
                            class="card-img-top artwork-image" alt="Phong Cảnh Biển">
                        <div class="card-body">
                            <h5 class="card-title">Phong Cảnh Biển</h5>
                            <p class="card-text">
                                <strong>Họa Sĩ:</strong> Nguyễn Văn A<br>
                                <strong>Kích Thước:</strong> 40x60 cm<br>
                                <strong>Giá:</strong> 500.000 VNĐ
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-6 col-md-3">
                    <div class="card artwork-card">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSthr50ZOqHVwMkrYSWSSjagIK9sAaHzr-kJg&s"
                            class="card-img-top artwork-image" alt="Phong Cảnh Biển">
                        <div class="card-body">
                            <h5 class="card-title">Phong Cảnh Biển</h5>
                            <p class="card-text">
                                <strong>Họa Sĩ:</strong> Nguyễn Văn A<br>
                                <strong>Kích Thước:</strong> 40x60 cm<br>
                                <strong>Giá:</strong> 500.000 VNĐ
                            </p>
                        </div>
                    </div>
                </div>

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
    <div id="footer-container"></div> 
    
    <div id="auth"></div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src="/assets/js/authModal.js"></script>
    <script src="/assets/js/header.js"></script>
    <script src="/assets/js/artWork.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</html>