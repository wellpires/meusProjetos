var app = require('./config/express')();

app.listen(3000, function () {
    var hora = new Date();
    var servidorHora = hora.getHours() + ':' + hora.getMinutes() + ':' + hora.getSeconds() + ',' + hora.getMilliseconds();
    console.log('SERVIDOR RODANDO');
    console.log('Hora: ' + servidorHora);
});

