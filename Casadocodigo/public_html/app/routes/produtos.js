module.exports = function (app) {
    app.get('/produtos', function (request, response) {
        var connection = app.infra.connectionFactory();
        var produtosDAO = new app.infra.ProdutosBancoDAO(connection);
        var results = produtosDAO.lista(function (err, results) {
            response.render('produtos/lista', {
                lista: results.rows
            });
        });
        return results;
    });

    app.get('/produtos/form', function (request, response) {
        response.render('produtos/form');
    });

    app.post('/produtos/salvar', function (request, response, resul) {

        var connection = app.infra.connectionFactory();
        var produtosDAO = new app.infra.ProdutosBancoDAO(connection);
        var produto = request.body;
        produtosDAO.salva(produto, function(erros, resultados){
            console.log(erros);
            response.render('produtos/lista');
        });
    });
};
