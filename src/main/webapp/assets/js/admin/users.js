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