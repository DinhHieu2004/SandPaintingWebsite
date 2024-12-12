const monthlyRevenue = [
    { month: 'Jan', revenue: 50000000 },
    { month: 'Feb', revenue: 65000000 },
    { month: 'Mar', revenue: 45000000 },
    { month: 'Apr', revenue: 70000000 },
];

const artistRevenue = [
    { name: 'Trần Minh Hoàng', value: 35 },
    { name: 'Nguyễn Thị Lan', value: 25 },
    { name: 'Lê Minh Tuấn', value: 40 },
];

const discounts = [
    { id: 1, name: 'Giảm giá mùa hè', percentage: 20, startDate: '2024-06-01', endDate: '2024-06-30' },
    { id: 2, name: 'Khuyến mãi đặc biệt', percentage: 15, startDate: '2024-07-01', endDate: '2024-07-15' },
];

const paintings = [
    { id: 1, title: 'Phong cảnh mùa thu', artist: 'Trần Minh Hoàng', price: 5000000, status: 'available' },
    { id: 2, title: 'Hoàng hôn', artist: 'Nguyễn Thị Lan', price: 7000000, status: 'sold' },
];

function renderDashboard() {
    return `
        <div class="stats-grid">
            <div class="card stat-card">
                <span class="stat-icon">$</span>
                <div>
                    <p>Tổng doanh thu</p>
                    <h3>230.000.000đ</h3>
                </div>
            </div>
            <div class="card stat-card">
                <span class="stat-icon">🛍️</span>
                <div>
                    <p>Đơn hàng</p>
                    <h3>120</h3>
                </div>
            </div>
            <div class="card stat-card">
                <span class="stat-icon">📦</span>
                <div>
                    <p>Sản phẩm</p>
                    <h3>80</h3>
                </div>
            </div>
            <div class="card stat-card">
                <span class="stat-icon">👥</span>
                <div>
                    <p>Người dùng</p>
                    <h3>450</h3>
                </div>
            </div>
        </div>

        <div class="charts-grid">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Doanh thu theo tháng</h3>
                </div>
                <div class="card-content">
                    <!-- Chart would go here -->
                    <p>Chart placeholder</p>
                </div>
            </div>

             <div class="charts-grid">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Sản phẩm bán ra trong 1 ngày</h3>
                </div>
                <div class="card-content">
                    <!-- Chart would go here -->
                    <p>Chart placeholder</p>
                </div>
            </div>
             <div class="charts-grid">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Tranh Tồn kho lâu nhất</h3>
                </div>
                <div class="card-content">
                    <!-- Chart would go here -->
                    <p>Chart placeholder</p>
                </div>
            </div>
            

            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Doanh thu theo nghệ sĩ</h3>
                </div>
                <div class="card-content">
                    <!-- Chart would go here -->
                    <p>Chart placeholder</p>
                </div>
            </div>
        </div>

        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Hoạt động gần đây</h3>
            </div>
            <div class="card-content">
                <div class="alert">Đơn hàng mới #123 từ Nguyễn Văn A</div>
                <div class="alert">Tranh "Hoàng hôn" đã được bán</div>
            </div>
        </div>
    `;
}

function renderDiscounts() {
    return `
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Quản lý chương trình giảm giá</h3>
                <button class="button button-primary" onclick="openAddDiscountModal()">
                    <span>+</span> Thêm chương trình
                </button>
            </div>
            <div class="card-content">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Tên chương trình</th>
                            <th>Phần trăm</th>
                            <th>Ngày bắt đầu</th>
                            <th>Ngày kết thúc</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${discounts.map(discount => `
                            <tr>
                                <td>${discount.name}</td>
                                <td>${discount.percentage}%</td>
                                <td>${discount.startDate}</td>
                                <td>${discount.endDate}</td>
                                <td>
                                    <button class="button">✏️</button>
                                    <button class="delete">🗑️</button>
                                </td>
                            </tr>
                        `).join('')}
                    </tbody>
                </table>
            </div>
        </div>
    `;
}

function renderPaintings() {
    return `
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Quản lý tranh</h3>
                <button class="button button-primary" onclick="openAddPaintingModal()">
                    <span>+</span> Thêm tranh mới
                </button>
            </div>
            <div class="card-content">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Tên tranh</th>
                            <th>Họa sĩ</th>
                            <th>Giá</th>
                            <th>Trạng thái</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${paintings.map(painting => `
                            <tr>
                                <td>${painting.title}</td>
                                <td>${painting.artist}</td>
                                <td>${painting.price.toLocaleString()}đ</td>
                                <td>
                                    <span class="status-badge ${painting.status === 'available' ? 'status-available' : 'status-sold'}">
                                        ${painting.status === 'available' ? 'Còn hàng' : 'Đã bán'}
                                    </span>
                                </td>
                                <td>
                                    <button class="button" onclick="openSeePaintingModal()">👁️</button>
                                    <button class="button" onclick="openEditPaintingModal()">✏️</button>
                                    <button class="button" onclick="openDeletePaintingModal()">🗑️</button>
                                </td>
                            </tr>
                        `).join('')}
                    </tbody>
                </table>
            </div>
        </div>
    `;
}



// Navigation handling
const mainContent = document.getElementById('mainContent');
const navButtons = document.querySelectorAll('.nav-button');

function setActiveSection(section) {
    navButtons.forEach(button => {
        button.classList.remove('active');
        if (button.dataset.section === section) {
            button.classList.add('active');
        }
    });

    switch(section) {
        case 'dashboard':
            mainContent.innerHTML = renderDashboard();
            break;
        case 'discounts':
            mainContent.innerHTML = renderDiscounts();
            break;
        case 'paintings':
            mainContent.innerHTML = renderPaintings();
            break;
    }
}

navButtons.forEach(button => {
    button.addEventListener('click', () => {
        setActiveSection(button.dataset.section);
    });
});

// Initialize dashboard view
setActiveSection('dashboard');




const users = [
    { id: 1, name: 'Nguyễn Văn A', email: 'nguyenvana@email.com', type: 'customer', status: 'active' },
];

const orders = [
    { 
        id: 1, 
        customer: 'Nguyễn Văn A', 
        items: ['Phong cảnh mùa thu'], 
        total: 5000000,
        status: 'completed',
        date: '2024-03-01'
    },
    { 
        id: 2, 
        customer: 'Trần Thị B', 
        items: ['Hoàng hôn'], 
        total: 7000000,
        status: 'pending',
        date: '2024-03-02'
    }
];

const reviews = [
    {
        id: 1,
        user: 'Nguyễn Văn A',
        rating: 5,
        comment: 'Tranh rất đẹp, dịch vụ tốt',
        date: '2024-03-01'
    },
    {
        id: 2,
        user: 'Trần Thị B',
        rating: 4,
        comment: 'Giao hàng hơi chậm nhưng tranh đẹp',
        date: '2024-03-02'
    }
];

// New render functions
function renderUsers() {
    return `
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Quản lý người dùng</h3>
                <button class="button button-primary">
                    <span>+</span> Thêm người dùng
                </button>
            </div>
            <div class="card-content">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Tên</th>
                            <th>Email</th>
                            <th>Loại tài khoản</th>
                            <th>Trạng thái</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${users.map(user => `
                            <tr>
                                <td>${user.name}</td>
                                <td>${user.email}</td>
                                <td>
                                    <span class="user-type-badge ${user.type === 'artist' ? 'user-artist' : 'user-customer'}">
                                        ${user.type === 'artist' ? 'Họa sĩ' : 'Khách hàng'}
                                    </span>
                                </td>
                                <td>${user.status}</td>
                                <td>
                                    <button class="button">✏️</button>
                                    <button class="button">🗑️</button>
                                </td>
                            </tr>
                        `).join('')}
                    </tbody>
                </table>
            </div>
        </div>
    `;
}

function renderOrders() {
    return `
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Quản lý đơn hàng</h3>
            </div>
            <div class="card-content">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Mã đơn</th>
                            <th>Khách hàng</th>
                            <th>Sản phẩm</th>
                            <th>Tổng tiền</th>
                            <th>Trạng thái</th>
                            <th>Ngày đặt</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${orders.map(order => `
                            <tr>
                                <td>#${order.id}</td>
                                <td>${order.customer}</td>
                                <td>${order.items.join(', ')}</td>
                                <td>${order.total.toLocaleString()}đ</td>
                                <td>
                                    <span class="order-status status-${order.status}">
                                        ${order.status === 'completed' ? 'Hoàn thành' : 
                                          order.status === 'pending' ? 'Đang xử lý' : 'Đã hủy'}
                                    </span>
                                </td>
                                <td>${order.date}</td>
                                <td>
                                    <button class="button" onclick="openSeeOrderModal()">👁️</button>
                                    <button class="button" onclick="openEditOrderModal()">✏️</button>
                                </td>
                            </tr>
                        `).join('')}
                    </tbody>
                </table>
            </div>
        </div>
    `;
}

function renderReviews() {
    return `
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Đánh giá của khách hàng</h3>
            </div>
            <div class="card-content">
                ${reviews.map(review => `
                    <div class="review-card">
                        <div class="rating">
                            ${'⭐'.repeat(review.rating)}
                        </div>
                        <p><strong>${review.user}</strong> - ${review.date}</p>
                        <p>${review.comment}</p>
                        <div style="margin-top: 1rem">
                            <button class="button">✏️</button>
                            <button class="delete">🗑️</button>
                        </div>
                    </div>
                `).join('')}
            </div>
        </div>
    `;
}

// Update navigation handling
function setActiveSection(section) {
    navButtons.forEach(button => {
        button.classList.remove('active');
        if (button.dataset.section === section) {
            button.classList.add('active');
        }
    });

    switch(section) {
        case 'dashboard':
            mainContent.innerHTML = renderDashboard();
            break;
        case 'users':
            mainContent.innerHTML = renderUsers();
            break;
        case 'orders':
            mainContent.innerHTML = renderOrders();
            break;
        case 'reviews':
            mainContent.innerHTML = renderReviews();
            break;
        case 'discounts':
            mainContent.innerHTML = renderDiscounts();
            break;
        case 'paintings':
            mainContent.innerHTML = renderPaintings();
            break;
    }
}

// Modal handling
function openModal(modalId) {
    document.getElementById(modalId).style.display = 'flex';
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}

// Initialize dashboard view
setActiveSection('dashboard');
window.onclick = function(event) {
    const modal = document.getElementById('addDiscountModal');
    if (event.target === modal) {
        closeAddDiscountModal();
    }
}
function openEditOrderModal() {
    const modal = document.getElementById('editManageOrderModal');
    modal.style.display = 'flex';
}

function closeEditOrderModal() {
    const modal = document.getElementById('editManageOrderModal');
    modal.style.display = 'none';
}
function openSeeOrderModal() {
    const modal = document.getElementById('seeManageOrderModal');
    modal.style.display = 'flex';
}

function closeSeeOrderModal() {
    const modal = document.getElementById('seeManageOrderModal');
    modal.style.display = 'none';
}
function openSeePaintingModal() {
    const modal = document.getElementById('seePaintingModal');
    modal.style.display = 'flex';
}

function closeSeePaintingModal() {
    const modal = document.getElementById('seePaintingModal');
    modal.style.display = 'none';
}
function openDeletePaintingModal() {
    const modal = document.getElementById('deletePaintingModel');
    modal.style.display = 'flex';
}

function closeDeletePaintingModal() {
    const modal = document.getElementById('deletePaintingModel');
    modal.style.display = 'none';
}
function openEditPaintingModal() {
    const modal = document.getElementById('editPaintingModal');
    modal.style.display = 'flex';
}

function closeEditPaintingModal() {
    const modal = document.getElementById('editPaintingModal');
    modal.style.display = 'none';
}
document.getElementById('editPaintingForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const title = document.getElementById('paintingTitle').value;
    const artist = document.getElementById('artistName').value;
    const price = document.getElementById('paintingPrice').value;
    const status = document.getElementById('paintingStatus').value;

    // Thêm tranh mới vào danh sách tranh
    paintings.push({
        title,
        artist,
        price: parseFloat(price),
        status
    });

    closeEditPaintingModal();

    renderPaintings();
});
function openAddPaintingModal() {
    const modal = document.getElementById('addPaintingModal');
    modal.style.display = 'flex';
}

function closePaintingModal() {
    const modal = document.getElementById('addPaintingModal');
    modal.style.display = 'none';
}
document.getElementById('addPaintingForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const title = document.getElementById('paintingTitle').value;
    const artist = document.getElementById('artistName').value;
    const price = document.getElementById('paintingPrice').value;
    const status = document.getElementById('paintingStatus').value;

    // Thêm tranh mới vào danh sách tranh
    paintings.push({
        title,
        artist,
        price: parseFloat(price),
        status
    });

    closePaintingModal();

    renderPaintings();
});

// Sample paintings data
const availablePaintings = [
    { id: 1, title: 'Phong cảnh mùa thu', artist: 'Trần Minh Hoàng', price: 5000000 },
    { id: 2, title: 'Hoàng hôn', artist: 'Nguyễn Thị Lan', price: 7000000 },
    { id: 3, title: 'Bình minh', artist: 'Lê Văn Nam', price: 6000000 },
];

// Populate paintings select
function populatePaintingsSelect() {
    const select = document.querySelector('select[name="paintings"]');
    availablePaintings.forEach(painting => {
        const option = document.createElement('option');
        option.value = painting.id;
        option.textContent = `${painting.title} - ${painting.artist} - ${painting.price.toLocaleString()}đ`;
        select.appendChild(option);
    });
}

// Modal handling
function openAddDiscountModal() {
    const modal = document.getElementById('addDiscountModal');
    modal.style.display = 'flex';
    populatePaintingsSelect();
}

function closeAddDiscountModal() {
    const modal = document.getElementById('addDiscountModal');
    modal.style.display = 'none';
    document.getElementById('addDiscountForm').reset();
    hideAlerts();
}

// Alert handling
function showAlert(type) {
    hideAlerts();
    document.getElementById(`${type}Alert`).style.display = 'block';
    setTimeout(() => {
        document.getElementById(`${type}Alert`).style.display = 'none';
    }, 3000);
}

function hideAlerts() {
    document.getElementById('successAlert').style.display = 'none';
    document.getElementById('errorAlert').style.display = 'none';
}

// Form submission
function handleSubmitDiscount(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    
    // Validate dates
    const startDate = new Date(formData.get('startDate'));
    const endDate = new Date(formData.get('endDate'));
    
    if (endDate < startDate) {
        showAlert('error');
        return;
    }

    // Create discount object
    const discount = {
        name: formData.get('name'),
        percentage: formData.get('percentage'),
        paintings: Array.from(formData.getAll('paintings')),
        startDate: formData.get('startDate'),
        endDate: formData.get('endDate')
    };

    // Here you would typically send this to your backend
    console.log('New discount:', discount);
    
    showAlert('success');
    setTimeout(() => {
        closeAddDiscountModal();
    }, 1500);
}

// Close modal when clicking outside
window.onclick = function(event) {
    const modal = document.getElementById('addDiscountModal');
    if (event.target === modal) {
        closeAddDiscountModal();
    }
}
// Xử lý sự kiện khi nhấn nút "Xóa"
document.addEventListener("click", function (event) {
  if (event.target.classList.contains("delete")) {
    const isConfirmed = confirm("Bạn có chắc chắn muốn xóa mục này không?");
    if (isConfirmed) {
      // Xóa dòng chứa nút "Xóa"
      const row = event.target.closest("tr");
      row.remove();
    }
  }
});
// Modal management functions
function openManageUsersModal() {
    document.getElementById('manageUsersModal').style.display = 'block';
}

function closeManageUsersModal() {
    document.getElementById('manageUsersModal').style.display = 'none';
}

function openAddUserModal() {
    document.getElementById('addUserModal').style.display = 'block';
}

function closeAddUserModal() {
    document.getElementById('addUserModal').style.display = 'none';
    document.getElementById('addUserForm').reset();
}

function openEditUserModal(userId) {
    const editModal = document.getElementById('editUserModal');
    editModal.style.display = 'block';
    
    // Giả sử bạn có một hàm để lấy thông tin user từ backend
    // Đây là ví dụ mock data
    const userData = {
        username: "User " + userId,
        email: "user" + userId + "@example.com",
        role: "user"
    };
    
    document.getElementById('editUsername').value = userData.username;
    document.getElementById('editEmail').value = userData.email;
    document.getElementById('editRole').value = userData.role;
    editModal.dataset.userId = userId;
}

function closeEditUserModal() {
    document.getElementById('editUserModal').style.display = 'none';
    document.getElementById('editUserForm').reset();
}

function openDeleteUserModal(userId) {
    const deleteModal = document.getElementById('deleteUserModal');
    deleteModal.style.display = 'block';
    deleteModal.dataset.userId = userId;
}

function closeDeleteUserModal() {
    document.getElementById('deleteUserModal').style.display = 'none';
}

// Form submission handlers
function handleAddUser(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const userData = {
        username: formData.get('username'),
        email: formData.get('email'),
        role: formData.get('role')
    };
    
    // Giả sử bạn có API endpoint để thêm user
    console.log('Adding user:', userData);
    
    // Sau khi thêm thành công
    closeAddUserModal();
    loadUsers(); // Reload user list
}

function handleEditUser(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const userId = document.getElementById('editUserModal').dataset.userId;
    const userData = {
        username: formData.get('username'),
        email: formData.get('email'),
        role: formData.get('role')
    };
    
    // Giả sử bạn có API endpoint để cập nhật user
    console.log('Updating user:', userId, userData);
    
    // Sau khi cập nhật thành công
    closeEditUserModal();
    loadUsers(); // Reload user list
}

function confirmDeleteUser() {
    const userId = document.getElementById('deleteUserModal').dataset.userId;
    
    // Giả sử bạn có API endpoint để xóa user
    console.log('Deleting user:', userId);
    
    // Sau khi xóa thành công
    closeDeleteUserModal();
    loadUsers(); // Reload user list
}

// Load users function
function loadUsers() {
    // Mock data - thay thế bằng call API thực tế
    const users = [
        { id: 1, username: 'User 1', email: 'user1@example.com', role: 'user' },
        { id: 2, username: 'Admin 1', email: 'admin1@example.com', role: 'admin' }
    ];
    
    const tbody = document.getElementById('userTableBody');
    tbody.innerHTML = '';
    
    users.forEach(user => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td>
                <button onclick="openEditUserModal(${user.id})">Sửa</button>
                <button onclick="openDeleteUserModal(${user.id})">Xóa</button>
            </td>
        `;
        tbody.appendChild(row);
    });
}

// Initialize when page loads
document.addEventListener('DOMContentLoaded', () => {
    loadUsers();
});

// Close modals when clicking outside
window.addEventListener('click', (event) => {
    const modals = [
        'manageUsersModal',
        'addUserModal',
        'editUserModal',
        'deleteUserModal'
    ];
    
    modals.forEach(modalId => {
        const modal = document.getElementById(modalId);
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });
});

// User Management Functions
function openManageUsersModal() {
    document.getElementById('manageUsersModal').style.display = 'block';
}

function closeManageUsersModal() {
    document.getElementById('manageUsersModal').style.display = 'none';
}

function openAddUserModal() {
    document.getElementById('addUserModal').style.display = 'block';
}

function closeAddUserModal() {
    document.getElementById('addUserModal').style.display = 'none';
}

function openEditUserModal(userId) {
    const editModal = document.getElementById('editUserModal');
    const editForm = document.getElementById('editUserForm');
    
    // Populate form with user data (you'd typically fetch this from your backend)
    const usernameInput = document.getElementById('editUsername');
    const emailInput = document.getElementById('editEmail');
    const roleInput = document.getElementById('editRole');
    
    // Example of populating with mock data - replace with actual data retrieval
    usernameInput.value = 'Example User';
    emailInput.value = 'user@example.com';
    roleInput.value = 'user';
    
    editModal.style.display = 'block';
}

function closeEditUserModal() {
    document.getElementById('editUserModal').style.display = 'none';
}

function openDeleteUserModal(userId) {
    const deleteModal = document.getElementById('deleteUserModal');
    deleteModal.style.display = 'block';
}

function closeDeleteUserModal() {
    document.getElementById('deleteUserModal').style.display = 'none';
}

function handleAddUser(event) {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    
    const userData = {
        username: formData.get('username'),
        email: formData.get('email'),
        role: formData.get('role')
    };
    
    // TODO: Implement actual user addition logic
    console.log('Adding user:', userData);
    
    // Close modal and reset form
    closeAddUserModal();
    form.reset();
}

function handleEditUser(event) {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    
    const userData = {
        username: formData.get('username'),
        email: formData.get('email'),
        role: formData.get('role')
    };
    
    // TODO: Implement actual user edit logic
    console.log('Editing user:', userData);
    
    // Close modal and reset form
    closeEditUserModal();
}

function confirmDeleteUser() {
    // TODO: Implement actual user deletion logic
    console.log('Deleting user');
    
    // Close modal
    closeDeleteUserModal();
}

// Optional: Populate user table when page loads
document.addEventListener('DOMContentLoaded', function() {
    const userTableBody = document.getElementById('userTableBody');
    
    // Mock user data - replace with actual data fetching
    const users = [
        { id: 1, username: 'admin', email: 'admin@example.com', role: 'admin' },
        { id: 2, username: 'user1', email: 'user1@example.com', role: 'user' }
    ];
    
    userTableBody.innerHTML = users.map(user => `
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td>
                <button onclick="openEditUserModal(${user.id})">Sửa</button>
                <button onclick="openDeleteUserModal(${user.id})">Xóa</button>
            </td>
        </tr>
    `).join('');
});