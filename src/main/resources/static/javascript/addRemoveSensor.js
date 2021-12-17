$(document).ready(function() {

 //Load dropdown for choosing which plant to remove with all current plants
    $.ajax({
        url: "/getPlants",
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
            for (var x = 0; x < res.length; x++) {
               $("#plant").append("<option value=\"" + res[x].name + "\">" + res[x].name + "</option>");
            }
        }
    });
    //Load dropdown for choosing which sensor to remove with all current plants
    $.ajax({
        url: "/getSensors",
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
            for (var x = 0; x < res.length; x++) {
                $(".dropdown-content-remove").append("<a th:href=\"@{addRemoveSensor}\" onclick=\"deleteSensorWithId(" + res[x].id + ")\">" + res[x].name + "</a>");
            }
        }
    });
});

function deleteSensorWithId(id) {
    if (confirm("Are you sure you want to delete this sensor?")) {
        $.ajax({
            url: "/deleteSensor/" + id,
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

function addSensor() {
    $.ajax({
        url: "/getLookupId/Plants/" + $('#plant').val(),
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
            var name = $('#name').val();
            var uom = $('#uom').val();
            var lookupId = res;
            var requestBody = '{"name": "' + name + '", "unitOfMeasurement": "' + uom + '", "lookupId": "' + lookupId + '"}';
            console.log(requestBody);
            requestBody = JSON.parse(requestBody);
            //AJAX post request
            $.ajax({
                type: "POST",
                url: "/addSensor",
                // The key needs to match your method's input parameter (case-sensitive).
                data: JSON.stringify(requestBody),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            });
        }
    });
    setTimeout(function() {
                location.reload(true);
            }, 2000);
}