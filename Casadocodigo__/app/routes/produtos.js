module.exports = function (app) {
	app.get('/produtos', function (request, response) {
		var connection = app.infra.connectionFactory();
		var produtosBanco = app.infra.produtosBanco;
		var results = produtosBanco.lista(connection, function (err, results) {
				response.render('produtos/lista', {
					lista : results.rows
				})
			});
		return results;
	});

	app.get('produtos/remove', function () {});

}
