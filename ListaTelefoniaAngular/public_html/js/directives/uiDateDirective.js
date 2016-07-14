app.directive("uiDate", function ($filter) {
    return{
        require: "ngModel",
        link: function (scope, element, attrs, ctrl) {
            var _formatDate = function (date) {
                if (date.length > 10) {
                    return date.substring(0, 10);
                }
                if (date.length < 8) {
                    date = date.replace(/[^0-9]+/g, '');
                    if (date.length > 2) {
                        date = date.substring(0, 2) + '/' + date.substring(2);
                    }
                    if (date.length > 5) {
                        date = date.substring(0, 5) + '/' + date.substring(5);
                    }
                }
                return date;
            };

            element.bind("keyup", function () {
                ctrl.$setViewValue(_formatDate(ctrl.$viewValue));
                ctrl.$render();
            });

            ctrl.$parsers.push(function (value) {
                if (isUndefinedOrNull(value)) {
                    return value;
                }
                if (value.length === 10) {
                    return formatarData(value);
                }
            });
        }
    };
});