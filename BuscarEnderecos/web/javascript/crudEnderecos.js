
function apenasNumeros(evento) {
    var regexp = /^\d{1,8}$/;
    if (evento.key.match(regexp) === null && evento.keyCode !== 8 && evento.keyCode !== 9) {
        evento.preventDefault();
    }
}