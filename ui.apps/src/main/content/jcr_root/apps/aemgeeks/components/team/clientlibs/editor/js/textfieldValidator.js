// ui.apps/src/main/content/jcr_root/apps/aemgeeks/components/team/clientlibs/editor/js/multifieldDesignationValidator.js
(function($, Coral){
    "use strict";
    // Event delegation for dynamically rendered multifield items
    $(document).on("change", ".coral-Multifield input", function () {
        if ($(this).attr("name") && $(this).attr("name").includes("memberdesignation")) {
            var value = $(this).val();
            var regex = /^[A-Z]+$/;
            if (value && !regex.test(value)) {
                Coral.commons.ready(function() {
                    Coral.Dialog.alert({
                        header: "Invalid Designation",
                        content: "Only uppercase letters (A-Z) are allowed in the designation field."
                    });
                });
                $(this).val("");
            }
        }
    });
})(jQuery, Coral);