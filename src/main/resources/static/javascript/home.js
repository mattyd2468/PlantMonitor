$(document).ready(function() {
    $('#readings').hide();
    //Load dropdown for choosing which plant to remove with all current plants
    $.ajax({
        url: "/getPlants",
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
            for (var x = 0; x < res.length; x++) {
                $(".dropdown-content").append('<a th:href="@{home}" onclick="displayDetails(' + res[x].id + ')">' + res[x].name + '</a>');
            }
        },
        error: function(e) {
            alert("Error fetching plants. Please try again later.")
        }

    });
});

function displayDetails(plantID) {
    $.ajax({
        url: "/getSinglePlant/ById/" + plantID,
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
            $(".plant-details").empty();
            $('.tableBody').empty();
            $(".plant-details").append('<p> Name: ' + res.name + '</p><br><p> Location: ' + res.location + '</p><br><p> Status: ' + res.status + '</p>');
            $.ajax({
                url: "/getReadings/ByPlantId/" + plantID,
                type: 'GET',
                dataType: 'json', // added data type
                success: function(res) {
                    for (var x = 0; x < res.length; x++) {
                        //console.log('<tr><td>' + res[x].sensorName + '</td><td>' + res[x].reading + res[x].unitOfMeasurement + '</td><td>' + res[x].timestamp + '</td></tr>');
                        $('#readings').show();
                        $('.tableBody').append('<tr><td>' + res[x].sensorName + '</td><td>' + res[x].reading + ' ' + res[x].unitOfMeasurement + '</td><td>' + res[x].timestamp + '</td></tr>');
                    }
                },
                error: function(e) {
                    alert("Error fetching readings. Please try again later.");
                }
            });
        },
        error: function(e) {
            alert("Error fetching plant. Please try again later.")
        }
    });
}