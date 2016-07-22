module.exports = function () {
	this.lista = function (connection, callback) {
		var results = [];
		var query = connection.query('SELECT * FROM tbl_livros', callback);
	}
	return this;
}
