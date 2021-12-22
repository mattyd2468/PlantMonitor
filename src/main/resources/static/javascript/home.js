$(document).ready(function() {
     $.ajax({
            url: "/updateStatus",
            type: 'PUT',
            dataType: 'json',
            data: null,
            contentType: "application/json; charset=utf-8",
            success: function(res) {
            }
        });


    $('#readings').hide();
    //Load dropdown for choosing which plant to display the details of
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

//function to get details of a single plant. including its latest readings
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
                        $('#readings').show();
                        $('.tableBody').append('<tr><td>' + res[x].sensorName + '</td><td>' + res[x].reading + ' ' + res[x].unitOfMeasurement + '</td><td>' + res[x].timestamp + '</td><td id = "bounds' + res[x].sensorLookupId + '"></td></tr>');
                        console.log('<tr><td>' + res[x].sensorName + '</td><td>' + res[x].reading + ' ' + res[x].unitOfMeasurement + '</td><td>' + res[x].timestamp + '</td><td id = "bounds' + res[x].sensorLookupId + '"></td></tr>');
                        isWithinBounds(res[x].sensorLookupId, res[x].reading);
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

//function to check if a reading is within acceptable bounds and to display the result
function isWithinBounds(sensorId, sensorReading) {
    $.ajax({
        url: "/getAcceptableBounds/singleSensor/" + sensorId,
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
        //TODO - Put this logic in Java instead
            var id = '#bounds' + sensorId;
            if (sensorReading == null) {
                $(id).append("Acceptable range is not available");
            } else if (sensorReading > res.upperBoundry) {
                $(id).append("Reading is above the upper boundary");
            } else if (sensorReading < res.lowerBoundry) {
                $(id).append("Reading is below the lower boundary");
            } else {
                $(id).append("Reading is within acceptable range");
            }
        }
    });
}