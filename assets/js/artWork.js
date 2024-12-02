$(document).ready(function(){
    $("#footer-container").load("/partials/footer.html");
    $("#header-container").load("/partials/header.html");
    $("#auth").load("/partials/authModal.html");

    setupPagination();


});


let currentPage = 1; 

function setupPagination() {
    const itemsPerPage = 15; 
    const items = $('#artworkGallery .artwork-card'); 
    const totalItems = items.length;
    const totalPages = Math.ceil(totalItems / itemsPerPage); 
    const paginationContainer = $('.pagination');

    items.hide();

    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    items.slice(startIndex, endIndex).show();

    // Xóa phân trang cũ
    paginationContainer.empty();

    // Nút "Trước"
    paginationContainer.append(
        `<li class="page-item ${currentPage === 1 ? 'disabled' : ''}">
            <a class="page-link" href="#" data-page="${currentPage - 1}">Trước</a>
        </li>`
    );

    // Các số trang
    for (let i = 1; i <= totalPages; i++) {
        paginationContainer.append(
            `<li class="page-item ${currentPage === i ? 'active' : ''}">
                <a class="page-link" href="#" data-page="${i}">${i}</a>
            </li>`
        );
    }

    // Nút "Tiếp"
    paginationContainer.append(
        `<li class="page-item ${currentPage === totalPages ? 'disabled' : ''}">
            <a class="page-link" href="#" data-page="${currentPage + 1}">Tiếp</a>
        </li>`
    );

    // Sự kiện click cho các liên kết phân trang
    paginationContainer.find('.page-link').on('click', function(e) {
        e.preventDefault();
        const page = parseInt($(this).data('page'), 10);

        if (page >= 1 && page <= totalPages) {
            currentPage = page;
            setupPagination(); // Cập nhật lại giao diện phân trang
        }
    });
}

$(document).ready(function () {
    setupPagination();
});
