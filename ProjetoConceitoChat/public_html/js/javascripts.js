function sendMessage() {
    var inputMessage = $('#btn-input');
    var usuario = 'usuario963';
    var novoID = '';
    var novoIDIntervalo = '';

    $.get('view/newMessage.html', function (data, status, response) {
        var lastID = findLastID();
        var lastInterval = findLastIDIntervalo();
        novoID = 'ID_MINUTO_' + (++lastID);
        novoIDIntervalo = 'INTERVALO_' + (++lastInterval);
        data = data.replace(/#_MENSAGEM_USUARIO_#/, inputMessage.val());
        data = data.replace(/#_NOME_USUARIO_#/, usuario);
        data = data.replace(/#_ID_MINUTO_#/g, novoID);
        data = data.replace(/#_INTERVALO_#/g, novoIDIntervalo);

        var _html = $(data);
        _html.children().children().children()[1].id = novoID;
        $('#box_messages_sents').append(_html);
        inputMessage.val('');
    });

}

function findLastID() {

    var data = $(document).contents()[1].innerHTML;
    var result = '';

    var regexp = /ID_MINUTO_\d{1,}/gi;
    var ids = data.match(regexp);
    if (!ids)
        return -1;
    ids.sort(function (s1, s2) {
        var s1lower = s1.toLowerCase();
        var s2lower = s2.toLowerCase();
        return s1lower > s2lower ? 1 : (s1lower < s2lower ? -1 : 0);
    });
    regexp = /\d{1,}/;
    result = parseInt(ids[ids.length - 1].match(regexp));

    return result;
}
function findLastIDIntervalo() {

    var data = $(document).contents()[1].innerHTML;
    var result = '';

    var regexp = /INTERVALO_\d{1,}/gi;
    var ids = data.match(regexp);
    if (!ids)
        return -1;
    ids.sort(function (s1, s2) {
        var s1lower = s1.toLowerCase();
        var s2lower = s2.toLowerCase();
        return s1lower > s2lower ? 1 : (s1lower < s2lower ? -1 : 0);
    });
    regexp = /\d{1,}/;
    result = parseInt(ids[ids.length - 1].match(regexp));

    return result;
}