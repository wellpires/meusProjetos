app.controller("listaTelefonicaCtrl", function ($scope, contatosAPI, operadorasAPI) {
    $scope.app = "Lista Telefonica";
    $scope.contatos = [];
    $scope.operadoras = [];

    var carregarContatos = function () {
        contatosAPI.buscarContatos().success(function (data) {
            $scope.contatos = data;
        }).error(function (data) {
            $scope.error = "Não foi possível carregar os dados!";
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

        var contatosSelecionados = [];
        for (contato in contatos) {
            if (contatos[contato].selecionado) {
                contatosSelecionados.push(angular.copy(contatos[contato].contatos_id));
            }
        }
        contatosAPI.removerContatos(contatosSelecionados).success(function (data) {
            carregarContatos();
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