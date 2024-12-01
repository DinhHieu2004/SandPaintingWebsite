
    $(document).click(function(e) {
        if (!$(e.target).closest('#search-bar, #search-icon').length) {
            $('#search-bar').hide();
        }
    });
