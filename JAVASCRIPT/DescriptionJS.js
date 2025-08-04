document.querySelectorAll('.toggle-info').forEach(btn => {
    btn.addEventListener('click', function() {
        const info = this.nextElementSibling;
        const arrow = this.querySelector('.arrow');
        if (!info.classList.contains('show')) {
            info.classList.add('show');
            arrow.innerHTML = "&#9660;";
        } else {
            info.classList.remove('show');
            arrow.innerHTML = "&#9654;";
        }
    });
});