app.directive("uiTelefone", function () {
    return {
        require: "ngModel",
        link: function (scope, element, attrs, ctrl) {
            var _formatarTelefone = function (telefone) {
                if (telefone.length > 10) {
                    return telefone.substring(0, 10);
                }
                telefone = telefone.replace(/[^0-9]+/g, '');

                if (telefone.length > 4) {
                    telefone = telefone.substring(0, 4) + '-' + telefone.substring(4);
                }
                if (telefone.length > 9) {
                    telefone = telefone.replace('-', '');
                    telefone = telefone.substring(0, 5) + '-' + telefone.substring(5);
                }

                return telefone;
            };
            element.bind("keyup", function () {
                ctrl.$setViewValue(_formatarTelefone(ctrl.$viewValue));
                ctrl.$render();
            });
            ctrl.$parsers.push(function (value) {
                if (isUndefinedOrNull(value)) {
                    return value;
                }
                if (value.length === 9 || value.length === 10) {
                    return value.replace('-', '');
                }
            });
        }
    };
});