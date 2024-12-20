
<div class="modal fade" id="authModal" tabindex="-1" data-bs-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="authModalLabel">Đăng Nhập / Đăng Ký</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Tabs for Login and Register -->
                <ul class="nav nav-tabs" id="authTabs" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button class="nav-link active " id="login-tab" data-bs-toggle="tab" data-bs-target="#login"
                            type="button" role="tab" aria-controls="login" aria-selected="true">Đăng Nhập</button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="register-tab" data-bs-toggle="tab" data-bs-target="#register"
                            type="button" role="tab" aria-controls="register" aria-selected="false">Đăng Ký</button>
                    </li>
                </ul>

                <div class="tab-content mt-3" id="authTabsContent">
                    <!-- Login Form -->
                    <div class="tab-pane fade show active" id="login" role="tabpanel" aria-labelledby="login-tab">
                        <form id="loginForm" method="post" action="login">
                            <div class="mb-3">
                                <label for="username" class="form-label">Tên đăng nhập</label>
                                <input type="text" class="form-control" id="username"
                                    placeholder="Nhập tên đăng nhập của bạn" required>
                            </div>
                            <div class="mb-3">
                                <label for="loginPassword" class="form-label">Mật khẩu</label>
                                <input type="password" class="form-control" id="loginPassword"
                                    placeholder="Nhập mật khẩu" required>
                            </div>
                            <button type="submit" class="btn btn-primary w-100 login-btn">Đăng Nhập</button>
                        </form>
                    </div>

                    <!-- Register Form -->
                    <div class="tab-pane fade" id="register" role="tabpanel" aria-labelledby="register-tab">
                        <form id="registerForm">
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="registerName" class="form-label">Họ và Tên</label>
                                    <input type="text" class="form-control" id="registerName" placeholder="Nhập họ và tên của bạn" required>
                                </div>
                                <div class="col-md-6">
                                    <label for="registerUsername" class="form-label">Tên đăng nhập</label>
                                    <input type="text" class="form-control" id="registerUsername" placeholder="Nhập tên đăng nhập" required>
                                </div>
                            </div>
                    
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="registerPhone" class="form-label">Số điện thoại</label>
                                    <input type="text" class="form-control" id="registerPhone" placeholder="Nhập số điện thoại" required>
                                </div>
                                <div class="col-md-6">
                                    <label for="registerEmail" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="registerEmail" placeholder="Nhập email của bạn" required>
                                </div>
                            </div>
                    
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="registerAddress" class="form-label">Địa chỉ</label>
                                    <input type="text" class="form-control" id="registerAddress" placeholder="Nhập địa chỉ của bạn" required>
                                </div>
                                <div class="col-md-6">
                                    <label for="registerPassword" class="form-label">Mật khẩu</label>
                                    <input type="password" class="form-control" id="registerPassword" placeholder="Tạo mật khẩu" required>
                                </div>
                            </div>
                    
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="ConfirmRegisterPassword" class="form-label">Nhập lại mật khẩu</label>
                                    <input type="password" class="form-control" id="ConfirmRegisterPassword" placeholder="Nhập lại mật khẩu" required>
                                </div>
                            </div>
                    
                            <button type="submit" class="btn btn-success w-100 login-btn">Đăng Ký</button>
                        </form>
                    </div>
                    <div class="tab-pane fade" id="register" role="tabpanel" aria-labelledby="register-tab">
                        <form id="registerForm">
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="registerName" class="form-label">Họ và Tên</label>
                                    <input type="text" class="form-control" id="registerName" placeholder="Nhập họ và tên của bạn" required>
                                </div>
                                <div class="col-md-6">
                                    <label for="registerUsername" class="form-label">Tên đăng nhập</label>
                                    <input type="text" class="form-control" id="registerUsername" placeholder="Nhập tên đăng nhập" required>
                                </div>
                            </div>
                    
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="registerPhone" class="form-label">Số điện thoại</label>
                                    <input type="text" class="form-control" id="registerPhone" placeholder="Nhập số điện thoại" required>
                                </div>
                                <div class="col-md-6">
                                    <label for="registerEmail" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="registerEmail" placeholder="Nhập email của bạn" required>
                                </div>
                            </div>
                    
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="registerPassword" class="form-label">Mật khẩu</label>
                                    <input type="password" class="form-control" id="registerPassword" placeholder="Tạo mật khẩu" required>
                                </div>
                                <div class="col-md-6">
                                    <label for="ConfirmRegisterPassword" class="form-label">Nhập lại mật khẩu</label>
                                    <input type="password" class="form-control" id="ConfirmRegisterPassword" placeholder="Nhập lại mật khẩu" required>
                                </div>
                               
                            </div>
                    
                            <div class="row mb-3">
                                 <div class="col-md-6">
                                    <label for="registerAddress" class="form-label">Địa chỉ</label>
                                    <input type="text" class="form-control" id="registerAddress" placeholder="Nhập địa chỉ của bạn" required>
                                </div>
                            </div>
                    
                            <button type="submit" class="btn btn-success w-100 login-btn">Đăng Ký</button>
                        </form>
                    </div>
                                        
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../assets/js/authModal.js"></script>
