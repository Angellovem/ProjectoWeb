document.querySelectorAll('.toggle-info').forEach(btn => {
    btn.addEventListener('click', function() {
        const info = this.nextElementSibling;
        const arrow = this.querySelector('.arrow');
        
        if (!info.classList.contains('show')) {
            info.classList.add('show');
            this.classList.add('active');
            arrow.innerHTML = "&#9654;"; // Mantener la misma flecha ya que CSS hace la rotación
        } else {
            info.classList.remove('show');
            this.classList.remove('active');
            arrow.innerHTML = "&#9654;";
        }
    });
});