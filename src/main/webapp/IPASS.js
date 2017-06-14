laadLeden();
laadWedstrijden();
navBar();
function laadLeden(){
	$("#LedenLijst").html("");
	$.getJSON("http://localhost:4711/IPASS/restservices/Lid", function(data){
		$.each(data, function(key, value){
			$("#LedenLijst").append("<a href='#' class='list-group-item'>"+ value["Voornaam"] + " "+ value["Achternaam"] +"<input onclick='deleteLid("+ value["Bondsnummer"] +")' id='btn' type='button' value='verwijder' class='pull-right btn'></a>");
		});
	});
	$("#LedenLijst").append("</div>");
}

function laadWedstrijden(){
	$("#WedstrijdLijst").html("");
	$.getJSON("http://localhost:4711/IPASS/restservices/Wedstrijd", function(data){
		$.each(data, function(key, value){
			$("#WedstrijdLijst").append("<a href='#' id='list-item' class='list-group-item'>"+ value["Naam"] + " "+ value["Datum"] +"<input onclick='deleteWedstrijd("+ value["WedstrijdId"] +")' type='button' id='btn' value='verwijder' class='pull-right btn'></a>");
		});
	});
	$("#WedstrijdLijst").append("</div>");
}

function navBar(){
	$("#custom-bootstrap-menu").append("<div class='container-fluid'>"+
	        "<div class='navbar-header'><a class='navbar-brand' href='http://localhost:4711/IPASS'>ABC-Taxus</a>"+
	            "<button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-menubuilder'><span class='sr-only'>Toggle navigation</span><span class='icon-bar'></span><span class='icon-bar'></span><span class='icon-bar'></span>"+
	            "</button>"+
	        "</div>"+
	        "<div class='collapse navbar-collapse navbar-menubuilder'>"+
	            "<ul class='nav navbar-nav navbar-left'>"+
	                "<li><a href='http://localhost:4711/IPASS/LedenLijst.html'>Leden lijst</a>"+
	                "</li>"+
	                "<li><a href='http://localhost:4711/IPASS/LidAanmaken.html'>Lid toevoegen</a>"+
	                "</li>"+
	                "<li><a href='http://localhost:4711/IPASS/WedstrijdAanmaken.html'>Wedstrijd aanmaken</a>"+
	                "</li>"+
	                "<li><a href='http://localhost:4711/IPASS/WedstrijdLijst.html' onclick='destroySession()'>Wedstrijd lijst</a>"+
	                "</li>"+
	            "</ul>"+
	            "<ul class='nav navbar-nav navbar-right'>"+
                	"<li><a href='http://localhost:4711/IPASS/Login.html' onclick=''>Uitloggen</a>"+
                	"</li>"+
                "</ul>"+
	        "</div>"+
	    "</div>");
}

function deleteWedstrijd(WedstrijdId){
	console.log("delete wedstrijd");
	var uri = "http://localhost:4711/IPASS/restservices/Wedstrijd/Delete/"+WedstrijdId;
    $.ajax(uri, { 
        type: "DELETE", 
//        beforeSend: function(xhr){
//        	var token = window.sessionStorage.getItem("sessionToken");
//        	xhr.setRequestHeader('Authorization', 'Bearer' + token);
//        },
//        success: function(response) {
//            $("#response").text("Something Inserted!");
//        },
//        error: function(response) {
//            $("#response").text("RIP!");
//        }
    }); 
    laadWedstrijden();
}

function deleteLid(Bondsnummer){
	var uri = "http://localhost:4711/IPASS/restservices/Lid/Delete/"+Bondsnummer;
    $.ajax(uri, { 
        type: "DELETE", 
        beforeSend: function(xhr){
        	var token = window.sessionStorage.getItem("sessionToken");
        	xhr.setRequestHeader('Authorization', 'Bearer ' + token);
        },
        success: function(response) {
            $("#response").text("Something Inserted!");
        },
        error: function(response) {
            $("#response").text("RIP!");
        }
    });
    laadLeden();
}

$('#login').click(function(event){
	var data = $('#loginForm').serialize();
	
	$.post("http://localhost:4711/IPASS/restservices/authentication", data, function(response){
		window.sessionStorage.setItem("sessionToken", response);
	}).fail(function(jqXHR, textStatus, errorThrown){
		console.log(textStatus);
		console.log(errorThrown);
	});
});

function destroySession(){
	sessionStorage.clear();
}
