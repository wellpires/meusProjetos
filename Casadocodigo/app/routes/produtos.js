module.exports = function (app) {
	app.get('/produtos', function (request, response) {
		var results = [];
		var pg = require('pg');
		var conString = 'postgres://postgres:123456@localhost/CASA_CODIGO';
		var client = new pg.Client(conString);
		client.connect();

		var query = client.query('SELECT * FROM tbl_livros');
		query.on('row', function (row) {
			results.push(row);
		});
		query.on('end', function () {
			response.render('produtos/lista',{lista: results})
			client.end();
		});

	});
}
