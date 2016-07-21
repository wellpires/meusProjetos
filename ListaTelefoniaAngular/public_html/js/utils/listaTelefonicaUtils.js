function formatarData(data) {
    if (isUndefinedOrNull(data)) {
        return;
    }
    var dataSeparada = data.split('\/');
    return new Date(dataSeparada[2], dataSeparada[1] - 1, dataSeparada[0]);
}
;
function isUndefinedOrNull(valor) {
    return typeof (valor) === 'undefined' || valor === null;
}

