		function checkContainers (){
			if($('#map1').is(':visible') && $('#map2').is(':visible') && $('#map3').is(':visible') && 
					$('#map4').is(':visible') && $('#map5').is(':visible')){ //if the container is visible on the page
			   	initMap();  //Adds a grid to the html
			  } else {
			    setTimeout(checkContainers, 50); //wait 50 ms, then try again
			  }
		}
		
		function loadDoc(type,section) {
			  var xhttp = new XMLHttpRequest();
			  xhttp.onreadystatechange = function() {
			    if (this.readyState == 4 && this.status == 200) {
			      document.getElementById(section).innerHTML =
			      this.responseText;
			    }
			  };
			  xhttp.open("GET", type + ".html", true);
			  xhttp.send();
			}