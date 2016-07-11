app.filter("dateFilter", function(){
    return function (input){
        if(isUndefinedOrNull(input)){
            return input;
        }
        
        var data = new Date(input);
        var dia = data.getDate();
        var mes = (data.getMonth()+1);
        var ano = data.getFullYear();
        
        if(dia.toString().trim().length === 1){
            dia = '0' + dia;
        }
        if(mes.toString().trim().length === 1){
            mes = '0' + mes;
        }
        return dia + '/' + mes + '/' + ano;
    };
});