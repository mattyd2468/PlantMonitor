$( document ).ready(function() {
    //Load dropdown with all plants from lookup table
    $.ajax({
             url: "/getPlantLookup",
             type: 'GET',
             dataType: 'json', // added data type
             success: function(res) {
               for(var x = 0; x<res.length; x++){
                   $( ".dropdown-content-add" ).append( "<p>" + res[x].name + "</p>" );
               }
             }
         });

    //Load dropdown for choosing which plant to remove with all current plants
     $.ajax({
                  url: "/getPlants",
                  type: 'GET',
                  dataType: 'json', // added data type
                  success: function(res) {
                    for(var x = 0; x<res.length; x++){
                        $( ".dropdown-content-remove" ).append("<a th:href=\"@{addRemovePlant}\" onclick=\"deletePlantWithId(" + res[x].id + ")\">" + res[x].name + "</a>");
                    }                  }
              });
});

function deletePlantWithId(id) {
    $.ajax({
                     url: "/deletePlant/"+id,
                     type: 'DELETE',
                       error: function(e) {
                       alert(e);
                       }
                 });
 }