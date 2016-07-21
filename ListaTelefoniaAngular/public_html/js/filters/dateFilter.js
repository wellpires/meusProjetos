app.filter("dateFilter", function ($filter) {
    return function (input) {
        if (isUndefinedOrNull(input)) {
            return input;
        }
        return $filter("date")(new Date(input), "dd/MM/yyyy");
    };
});