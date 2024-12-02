
    document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const email = document.getElementById('loginEmail').value.trim();
    const password = document.getElementById('loginPassword').value.trim();
    if (email === 'admin123@gmail.com' && password === '123456') {
    window.location.href = '/admin/dashboard.html';
} else {
    alert('Sai thông tin đăng nhập. Vui lòng thử lại.');
    window.location.href = 'index.html';
}
});


