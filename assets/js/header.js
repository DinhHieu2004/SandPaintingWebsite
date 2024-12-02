
    $(document).click(function(e) {
        if (!$(e.target).closest('#search-bar, #search-icon').length) {
            $('#search-bar').hide();
        }

        if (!$(e.target).closest('#user-icon').length) {
            $('#user-icon').hide();
        }
    });
