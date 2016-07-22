function ProdutosBancoDAO(connection) {
    this._connection = connection;
}
ProdutosBancoDAO.prototype.lista = function (callback) {
    this._connection.query('SELECT * FROM tbl_livros', callback);
};

ProdutosBancoDAO.prototype.salva = function (produto, callback) {
    this._connection.query('INSERT INTO tbl_livros VALUES ($1)',[produto],callback);
};

module.exports = function () {
    return ProdutosBancoDAO;
};
