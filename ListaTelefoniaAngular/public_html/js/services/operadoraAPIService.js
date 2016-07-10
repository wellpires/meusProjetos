app.service("operadorasAPI", function($http, config){
    this.buscarOperadoras = function(){
        return $http.get(config.baseUrl + '/buscarOperadoras');
    };
});