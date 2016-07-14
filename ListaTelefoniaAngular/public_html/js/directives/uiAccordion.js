app.directive("uiAccordions", function () {
    return{
        controller: function ($scope, $element, $attrs) {
            var accordions = [];
            this.registerAccordions = function (accordion) {
                accordions.push(accordion);
            };

            this.closeAll = function () {
                accordions.forEach(function (accordion) {
                    accordion.isOpened = false;
                });
            };
        }
    };
});

app.directive("uiAccordion", function () {
    return{
        templateUrl: "view/accordion.html",
        transclude: true,
        require: "^uiAccordions",
        scope: {
            title: "@"
        },
        link: function (scope, element, attrs, ctrl) {
            ctrl.registerAccordions(scope);
            scope.open = function () {
                ctrl.closeAll();
                scope.isOpened = true;
            };
        }
    };
});