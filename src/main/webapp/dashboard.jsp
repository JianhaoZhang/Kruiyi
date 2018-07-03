<html>
  <head>
  <link rel="stylesheet" href="/css/w3.css"></link>
    <title>Create Account</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
		body {font-family: "Times New Roman", Georgia, Serif;
		}
		h1,h2,h3,h4,h5,h6 {
    	font-family: "Playfair Display";
    	letter-spacing: 5px;
		}
	</style>
	<script type='text/javascript'>
	
		function validate() {
			var value = document.getElementById("id").value;
			var title = document.getElementById("title").value;
			var date = document.getElementById("date").value;
			var body = document.getElementById("body").value;
			console.log(value.length);
			if (value.length < 1){
				alert("ID must be entered");
				return false;
			}else if (title.length <1){
				alert("Title must be entered");
				return false;
			}else if (date.length <1){
				alert("Date must be entered");
				return false;
			}else if (body.length <1){
				alert("Body must be entered");
				return false;
			}else{
				alert("Submitted");
				return true;
			}
			
		}
	</script>
  </head>
  <body>
  	<div class="w3-half">
  	<div class="w3-right">
  	<div class="w3-container w3-white w3-center w3-padding w3-card" style="letter-spacing:4px;">
    <form id="newsform" onsubmit="return validate();" name="newsform" action="update" method="post">
        <p><input class="w3-input w3-padding-16" type="text" required = "required" placeholder="ID" id="id" name="id"/></p>
        <p><input class="w3-input w3-padding-16" type="text" required = "required" placeholder="Title" id="title" name="title"/></p>
        <p><input class="w3-input w3-padding-16" type="text" required = "required" placeholder="Date" id="date" name="date"/></p>
        <p><textarea class="w3-input w3-padding-16"  required = "required" placeholder="Body" id="body" name="body"/></p>
    	<p><button class="w3-button w3-light-grey w3-padding-large" type="submit" id="submitbutton" name="submitbutton"  />Submit</p>
    </form>
    </div>
    </div>
    </div>
  </body>
</html>