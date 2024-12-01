$(document).ready(function(){
    $("#footer-container").load("/partials/footer.html");
    $("#header-container").load("/partials/header.html");
    setupPagination();


});


function setupPagination() {
    const itemsPerPage = 15; 
    const items = $('#artworkGallery .artwork-card');
    const totalItems = items.length;
    const totalPages = Math.ceil(totalItems / itemsPerPage);
    items.hide();

    items.slice(0, itemsPerPage).show();
    const paginationContainer = $('.pagination');
    paginationContainer.empty();

    paginationContainer.append(
        `<li class="page-item ${currentPage === 1 ? 'disabled' : ''}">
            <a class="page-link" href="#" data-page="${currentPage - 1}">Trước</a>
        </li>`
    );

    for (let i = 1; i <= totalPages; i++) {
        paginationContainer.append(
            `<li class="page-item ${currentPage === i ? 'active' : ''}">
                <a class="page-link" href="#" data-page="${i}">${i}</a>
            </li>`
        );
    }

    paginationContainer.append(
        `<li class="page-item ${currentPage === totalPages ? 'disabled' : ''}">
            <a class="page-link" href="#" data-page="${currentPage + 1}">Tiếp</a>
        </li>`
    );

    paginationContainer.find('.page-link').on('click', function(e) {
        e.preventDefault();
        const page = $(this).data('page');
        
        if (page < 1 || page > totalPages) return;
        items.hide();

        const startIndex = (page - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;
        items.slice(startIndex, endIndex).show();

        currentPage = page;
        setupPagination();
    });
}
let currentPage = 1;
