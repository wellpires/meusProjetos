var pg = require('pg');
module.exports = function () {
	return connectBD;
};
var connectBD = function () {
	var conString = 'postgres://postgres:123456@localhost/CASA_CODIGO';
	var client = new pg.Client(conString);
	client.connect();
	return client;
};
