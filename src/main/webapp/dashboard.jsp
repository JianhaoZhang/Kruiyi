<html>
  <head>
  <link rel="stylesheet" href="/css/w3.css"></link>
    <title>Kruiyi Control Panel</title>
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
		
		function validate_product() {
			var value = document.getElementById("id").value;
			var name = document.getElementById("name").value;
			var brand = document.getElementById("brand").value;
			var type = document.getElementById("type").value;
			var description = document.getElementById("description").value;
			var path = document.getElementById("path").value;
			console.log(value.length);
			if (value.length < 1){
				alert("ID must be entered");
				return false;
			}else if (name.length <1){
				alert("name must be entered");
				return false;
			}else if (brand.length <1){
				alert("brand must be entered");
				return false;
			}else if (type.length <1){
				alert("type must be entered");
				return false;
			}else if (description.length <1){
				alert("description must be entered");
				return false;
			}else if (path.length <1){
				alert("path must be entered");
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
  	<div class="w3-half">
  	<div class="w3-container  w3-white w3-padding w3-card" style="letter-spacing:4px;">
    <form id="newsform" onsubmit="return validate();" name="newsform" action="news" method="post">
    	<p><h2>News</h2></p>
        <p><input class="w3-input w3-padding-16" type="text" required = "required" placeholder="PID" id="pid" name="pid"/></p>
        <p><input class="w3-input w3-padding-16" type="text" required = "required" placeholder="Title" id="title" name="title"/></p>
        <p><input class="w3-input w3-padding-16" type="text" required = "required" placeholder="Date" id="date" name="date"/></p>
        <p><textarea class="w3-input w3-padding-16"  required = "required" placeholder="Body" id="body" name="body"></textarea></p>
    	<p><button class="w3-button w3-light-grey w3-padding-large" type="submit" id="submitbutton" name="submitbutton"  />Submit</p>
    </form>
    </div>
    </div>
    <div class="w3-half">
  	<div class="w3-container  w3-white w3-padding w3-card" style="letter-spacing:4px;">
    <form id="productform" onsubmit="return validate_product();" name="productform" action="products" method="post">
    	<p><h2>Products</h2></p>
        <p><input class="w3-input w3-padding-16" type="text" required = "required" placeholder="PID" id="pid" name="pid"/></p>
        <p><input class="w3-input w3-padding-16" type="text" required = "required" placeholder="Name" id="name" name="name"/></p>
        <p><input class="w3-input w3-padding-16" type="text" required = "required" placeholder="Brand" id="brand" name="brand"/></p>
        <p><input class="w3-input w3-padding-16" type="text" required = "required" placeholder="Type" id="type" name="type"/></p>
        <p><textarea class="w3-input w3-padding-16"  required = "required" placeholder="Description" id="description" name="description"></textarea></p>
        <p><input class="w3-input w3-padding-16" type="text" required = "required" placeholder="PDF" id="path" name="path"/></p>
    	<p><button class="w3-button w3-light-grey w3-padding-large" type="submit" id="submitbutton" name="submitbutton"  />Submit</p>
    </form>
    </div>
    </div>
    </div>
    <div class="w3-half">
  	<div class="w3-half">
  	<div class="w3-container  w3-white w3-padding w3-card" style="letter-spacing:4px;">
    
    	<p><h2>Console</h2></p>
    	<p class="w3-row">
    	<a href="/logout" class="w3-button w3-blue w3-padding-large" >Log off</a>
    	<a href="/render" class="w3-button w3-indigo w3-padding-large">Re-render</a>
    	<a href="/clean" class="w3-button w3-red w3-padding-large">*Purge</a>
  		</p>
    </div>
    </div>
    <div class="w3-half">
  	<div class="w3-container  w3-white w3-padding w3-card" style="letter-spacing:4px;">
    
    	<p><h2>TBD</h2></p>

    </div>
    </div>
    </div>
  </body>
</html>