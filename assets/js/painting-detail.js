$(document).ready(function(){
    $("#footer-container").load("/partials/footer.html");
    $("#header-container").load("/partials/header.html");
});
const heartIcon = document.getElementById('heartIcon');
const likeCount = document.getElementById('likeCount');

heartIcon.addEventListener('click', () => {
    if (heartIcon.classList.contains('liked')) {
        // Unlike functionality
        heartIcon.classList.remove('liked');
        likeCount.textContent = parseInt(likeCount.textContent) - 1;
    } else {
        // Like functionality
        heartIcon.classList.add('liked');
        likeCount.textContent = parseInt(likeCount.textContent) + 1;
    }
});

