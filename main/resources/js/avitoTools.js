window.atSetHeader = function (header, value) {
    try {
        atSetInputOrArea(header, value);
        console.debug("input success");
        return;
    } catch (e) {
    }
    try {
        atSetSelect(header, value);
        console.debug("select success");
        return;
    } catch (e) {
    }
};

window.atSetInputOrArea = function (header, value) {
    var label = atFindLabel(header);
    console.debug("found label");
    console.debug(label);
    var res = $(label).parent().find("input");
    if (res.size() == 0) {
        res = $(label).parent().find("textarea");
        if (res.size() == 0) {
            throw "Input and text area was not found";
        }
    }
    res.val(value);
};

window.atSetSelect = function (header, value) {
    var select = atFindSelect(header);
    console.debug("found select");
    console.debug(select);
    var elem = levElement($(select).get(0), ".//*", value);
    console.debug("using");
    console.debug(elem);
    if (elem == null) {
        throw "elem with value was not found";
    }
    console.debug("new value is " + $(elem).val());
    // $(elem).parent().val($(elem).val());
    $(elem).attr("selected", "selected");
    $(select).change();
};

window.atFindSelect = function (header) {
    res = $("form#f_item select option:contains(" + header + ")").parent();
    console.debug("found select");
    console.debug(res);
    if (res.size() == 0) {
        throw "Error select not found";
    }
    return res.first();
};

window.atFindLabel = function (header) {
    var res = $("label:contains(" + header + ")");
    if (res.size() == 0) {
        res = $("label b:contains(" + header + ")");
    }
    if (res.size() == 0) {
        throw "Error label not found";
    }
    return res.first();
};