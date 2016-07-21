app.filter("telefoneFilter", function () {
    return function (input) {
        if (isUndefinedOrNull(input)) {
            return input;
        }
        var primeiraParte = '';
        var segundaParte = '';
        input = input.toString().trim();
        if (input.length === 9) {
            primeiraParte = input.substring(0, 5);
            segundaParte = input.substring(5);
        }
        if (input.length === 8) {
            primeiraParte = input.substring(0, 4);
            segundaParte = input.substring(4);
        }

        return primeiraParte + '-' + segundaParte;
    };
});