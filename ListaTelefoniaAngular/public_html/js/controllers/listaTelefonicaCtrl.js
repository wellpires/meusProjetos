app.controller("listaTelefonicaCtrl", function ($scope, contatosAPI, operadorasAPI) {
    $scope.app = "Lista Telefonica";
    var carregarContatos = function () {
        contatosAPI.buscarContatos().success(function (data) {
            $scope.contatos = data;
        });
    };

    var carregarOperadoras = function () {
        operadorasAPI.buscarOperadoras().success(function (data) {
            $scope.operadoras = data;
        });
    };

    $scope.adicionarContato = function (contato) {
        contatosAPI.gravarContato(contato).success(function (data) {
            carregarContatos();
            delete $scope.contato;
            $scope.contatoForm.$setPristine();
        });
    };

    $scope.apagarContatos = function (contatos) {
        $scope.contatos = contatos.filter(function (contato) {
            if (!contato.selecionado)
                return contato;
        });
    };
    $scope.isContatoSelecionado = function (contatos) {
        if (!isUndefinedOrNull(contatos)) {
            return contatos.some(function (contato) {
                return contato.selecionado;
            });
        }
    };
    $scope.ordenarPor = function (campo) {
        $scope.campoOrdenacao = campo;
        $scope.direcaoOrdenacao = !$scope.direcaoOrdenacao;
    };

    carregarContatos();
    carregarOperadoras();
});