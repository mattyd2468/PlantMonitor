$(document).ready(function() {
    //Load dropdown with all plants from lookup table
    $.ajax({
        url: "/getPlantLookup",
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
            for (var x = 0; x < res.length; x++) {
                //$(".dropdown-content-add").append("<p>" + res[x].name + "</p>");
                $("#type").append("<option value=\"" + res[x].name + "\">" + res[x].name + "</option>");
            }
        }
    });

    //Load dropdown for choosing which plant to remove with all current plants
    $.ajax({
        url: "/getPlants",
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
            for (var x = 0; x < res.length; x++) {
                $(".dropdown-content-remove").append("<a th:href=\"@{addRemovePlant}\" onclick=\"deletePlantWithId(" + res[x].id + ")\">" + res[x].name + "</a>");
            }
        }
    });
});

function deletePlantWithId(id) {
    if (confirm("Are you sure you want to delete this plant?")) {
        $.ajax({
            url: "/deletePlant/" + id,
            type: 'DELETE',
            error: function(e) {
                alert(e);
            }
        });
        setTimeout(function() {
            location.reload(true);
        }, 2000);

    }
    return false;
}

function addPlant() {
    $.ajax({
        url: "/getLookupId/" + $('#type').val(),
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
            var name = $('#name').val();
            var location = $('#location').val();
            var lookupId = res;
            var requestBody = '{"name": "' + name + '", "location": "' + location + '", "lookupId": "' + lookupId + '"}';
            console.log(requestBody);
            requestBody = JSON.parse(requestBody);
            //AJAX post request
            $.ajax({
                type: "POST",
                url: "/addPlant",
                // The key needs to match your method's input parameter (case-sensitive).
                data: JSON.stringify(requestBody),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            });
        }
    });
}