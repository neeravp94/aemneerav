(function($, Coral){
    "use strict";
    console.log("Team clientlib loaded");

    var registry = $(window).adaptTo("foundation-registry");

    registry.register("foundation.validation.validator", {
        selector: "[data-validation=team-multifield]",
        validate: function(element){
            var e1 = $(element);
            let max = e1.data("max-items");
            let min = e1.data("min-items");
            var currentItems = e1.children("coral-multifield-item").length;
            console.log("Number of members are " + currentItems);
            if(max < currentItems)
                return "Maximum " + max + " members are allowed";
            if(min > currentItems)
                return "Please enter minimum " + min + " members";
        }
    });

    registry.register("foundation.validation.validator", {
            selector: "[data-validation=team-designation-validation]",
            validate: function(element) {
                let el = $(element);
                let pattern=/[0-9a-z]/;
                let value=el.val();
                if(pattern.test(value)){
                   return "Please add only Upper Case Letters in First name";
                }

            }
        });


})(jQuery, Coral);