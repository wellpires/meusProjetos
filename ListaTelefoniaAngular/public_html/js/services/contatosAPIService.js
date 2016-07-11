app.factory("contatosAPI", function($http, config){
   var _buscarContatos = function(){
       return $http.get(config.baseUrl + '/buscarContatos');
   };
   var _gravarContato = function(contato){
       var contatoSalvar = angular.copy(contato);
       contatoSalvar.telefone = contatoSalvar.telefone.replace('-', '');
       contatoSalvar.dataContatoJson = formatarData(contatoSalvar.dataContatoJson);
       contatoSalvar.cor = 'WHITE';
       return $http.post(config.baseUrl + '/cadastrarContatos', contatoSalvar);
   };
   var _removerContatos = function(contatos){
       return $http.post(config.baseUrl + '/removerContato', contatos);
   };
   return {
       buscarContatos: _buscarContatos,
       gravarContato: _gravarContato,
       removerContatos:_removerContatos
   };
});