$(document).ready(function() {
    //Load dropdown for choosing which plant to remove with all current plants
    $.ajax({
        url: "/getPlants",
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
            for (var x = 0; x < res.length; x++) {
                $(".dropdown-content").append('<a th:href="@{home}" onclick="displayDetails(' + res[x].id + ')">' + res[x].name + '</a>');
            }
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
                    $(".plant-details").append('<p> Name: ' + res.name + '</p><br><p> Location: ' + res.location + '</p><br><p> Status: ' + res.status + '</p>');
            }
        });
        //ajax call to get readings
}


