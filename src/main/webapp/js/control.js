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
			    	if (document.getElementById(section)!=null){
			    		document.getElementById(section).innerHTML = this.responseText;
			    	}else{
			    		setTimeout(loadDoc(type,section), 30);
			    	}
			    }
			  };
			  xhttp.open("GET", type + ".html", true);
			  xhttp.send();
			}
		
		function getStyle(element) {
		    if (typeof getComputedStyle !== "undefined") {
		        return getComputedStyle(element);
		    }
		    return element.currentStyle; // Old IE
		}
		
		function sleep(milliseconds) {
			  var start = new Date().getTime();
			  for (var i = 0; i < 1e7; i++) {
			    if ((new Date().getTime() - start) > milliseconds){
			      break;
			    }
			  }
			}
		function remove2(right,left){
			if (document.getElementById(right)!=null && document.getElementById(left)!=null){
			document.getElementById(right).outerHTML = "";
			document.getElementById(left).outerHTML = "";
			}else{
				console.log('nope');
			}
		}
		
		function remove(element){
			if (document.getElementById(element)!=null){
			document.getElementById(element).outerHTML = "";
			}else{
				console.log('nope');
			}
		}
		
		function equalize(){
			console.log('enter');
			if ($('#content').is(':visible')&& $('#side').is(':visible')&&$('#newscontent').is(':visible')){
				var right=getStyle(document.getElementById('newscontent')).height;
				var left=getStyle(document.getElementById('side')).height;
				console.log(right);
				console.log(left);
				if (right!=null && left!=null){
				if(left>right)
				{
				    document.getElementById('content').style.height=left;
				}
				if(right>left)
				{
				    document.getElementById('side').style.height=right;
				    document.getElementById('content').style.height=right;
				}else{
					
				}
				}else{
					setTimeout(equalize, 50);
					console.log('retry for null');
				}
			}else{
				setTimeout(equalize, 50);
				console.log('retry');
			}
		}
		
		