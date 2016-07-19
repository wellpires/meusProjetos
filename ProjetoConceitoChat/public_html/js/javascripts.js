function sendMessage() {
    var inputMessage = document.getElementById('btn-input');
    var usuario = 'wellington.goncalves.pires@everis.com';

    $.get('view/newMessage.html', function (data, status, response) {
        data = data.replace(/#_MENSAGEM_USUARIO_#/, inputMessage.value);
        data = data.replace(/#_NOME_USUARIO_#/, usuario);
        $('#box_messages_sents').append(data);
    });
}
function ativarMinutos() {
    setInterval(function () {
        var minutos = document.getElementById('minutos');
        var minutosInteiro = minutos.innerHTML;
        var horas = '';
        var dia = '';
        var tempoFormatado = '';
        var regexp = /d|h/;
        if (regexp.test(minutosInteiro)) {
            var horaArray = '';
            if (/h/.test(minutosInteiro)) {
                horaArray = minutosInteiro.split('h');
            }
            horas = horaArray[0];
            minutosInteiro = parseInt(horaArray[1]);
        }
        minutosInteiro++;
        if (minutosInteiro > 59) {
            minutosInteiro = 0;
            horas++;
        }
        if (horas.toString().length > 0) {
            if (horas > 23) {
                dia++;
            }

            if (horas.toString().length < 2) {
                horas = '0' + horas.toString();
            }
            horas = horas.toString() + 'h';
        }
        if (minutosInteiro.toString().length < 2) {
            minutosInteiro = '0' + minutosInteiro;
        }


        tempoFormatado = horas.toString() + minutosInteiro.toString();

        minutos.innerHTML = tempoFormatado;

    }, 1000);
}