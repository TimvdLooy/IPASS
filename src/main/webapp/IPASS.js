laadLeden();
laadWedstrijden();
navBar();
function laadLeden(){
	$("#LedenLijst").html("");
	$.getJSON("restservices/Lid", function(data){
		$.each(data, function(key, value){
			$("#LedenLijst").append("<a href='#' class='list-group-item'>"+ value["Voornaam"] + " "+ value["Achternaam"] +"<input onclick='deleteLid("+ value["Bondsnummer"] +")' id='btn' type='button' value='verwijder' class='pull-right btn'></a>");
		});
	});
	$("#LedenLijst").append("</div>");
}

function laadWedstrijden(){
	var href7 = window.location.href.split("/");
	if(href7[href7.length-1] ==  "WedstrijdLijst.html"){
	$("#WedstrijdLijst").html("");
	var uri = "restservices/Wedstrijd";
	$.ajax(uri, { 
        type: "GET", 
        beforeSend: function(xhr){
        	var token = window.sessionStorage.getItem("sessionToken");
        	xhr.setRequestHeader('Authorization', 'Bearer ' + token);
       },
        success: function(response) {
		$.each(response, function(key, value){
			$("#WedstrijdLijst").append("<a href='#' id='list-item' class='list-group-item'><div class='row'><div class='col-sm-12'><p>"+ value["Naam"] + " "+ value["Datum"] +
					"</p></div></div><div class='row'><div class='col-sm-12'><div class='btn-group'><input onclick='deleteWedstrijd("+ value["WedstrijdId"] +")' type='button' id='btn' value='verwijder' class='btn-danger'>"+
					"<input onclick='Redirect("+ value["WedstrijdId"] +")' type='button' id='btn' value='Score invoeren' class='btn-info'></div></div></div></a>");
		});
        },
        error: function(response) {
            $("#response").text("RIP!");
       }
	});
	$("#WedstrijdLijst").append("</div>");
	}
	
	var href6 = window.location.href.split("/");
	if(href6[href6.length-1] == "WedstrijdLijst_lid.html"){
	$("#WedstrijdLijst_lid").html("");
	var uri = "restservices/Wedstrijd/Leeftijd/"+window.sessionStorage.getItem("Leeftijd");
	$.ajax(uri, { 
        type: "GET", 
        beforeSend: function(xhr){
        	var token = window.sessionStorage.getItem("sessionToken");
        	xhr.setRequestHeader('Authorization', 'Bearer ' + token);
       },
        success: function(response) {
		$.each(response, function(key, value){
			$("#WedstrijdLijst_lid").append("<a href='#' id='list-item' class='list-group-item'>"+ value["Naam"] + " "+ value["Datum"] +"<input onclick='redirectWedstrijd("+ value["WedstrijdId"] +")' type='button' id='btn' value='Informatie' class='pull-right btn'></a>");
		});
        },
        error: function(response) {
            $("#response").text("RIP!");
       }
	});
	$("#WedstrijdLijst_lid").append("</div>");
	}
}

function navBar(){
	$("#custom-bootstrap-menu").append("<div class='container-fluid'>"+
	        "<div class='navbar-header'><a class='navbar-brand' href='HomePaginaAdmin.html'>ABC-Taxus</a>"+
	            "<button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-menubuilder'><span class='sr-only'>Toggle navigation</span><span class='icon-bar'></span><span class='icon-bar'></span><span class='icon-bar'></span>"+
	            "</button>"+
	        "</div>"+
	        "<div class='collapse navbar-collapse navbar-menubuilder'>"+
	            "<ul class='nav navbar-nav navbar-left'>"+
	                "<li><a href='LedenLijst.html'>Leden lijst</a>"+
	                "</li>"+
	                "<li><a href='LidAanmaken.html'>Lid toevoegen</a>"+
	                "</li>"+
	                "<li><a href='WedstrijdAanmaken.html'>Wedstrijd aanmaken</a>"+
	                "</li>"+
	                "<li><a href='WedstrijdLijst.html'>Wedstrijd lijst</a>"+
	                "</li>"+
	            "</ul>"+
	            "<ul class='nav navbar-nav navbar-right'>"+
                	"<li><a href='index.html' onclick=''>Uitloggen</a>"+
                	"</li>"+
                "</ul>"+
	        "</div>"+
	    "</div>");
	
	$("#custom-bootstrap-menu-user").append("<div class='container-fluid'>"+
	        "<div class='navbar-header'><a class='navbar-brand' href='HomePagina.html'>ABC-Taxus</a>"+
	            "<button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-menubuilder'><span class='sr-only'>Toggle navigation</span><span class='icon-bar'></span><span class='icon-bar'></span><span class='icon-bar'></span>"+
	            "</button>"+
	        "</div>"+
	        "<div class='collapse navbar-collapse navbar-menubuilder'>"+
	            "<ul class='nav navbar-nav navbar-left'>"+
	                "<li><a href='Statistieken.html'>Statistieken</a>"+
	                "</li>"+
	                "<li><a href='WedstrijdLijst_lid.html'>Wedstrijden</a>"+
	                "</li>"+
	            "</ul>"+
	            "<ul class='nav navbar-nav navbar-right'>"+
                	"<li><a href='index.html' onclick=''>Uitloggen</a>"+
                	"</li>"+
                "</ul>"+
	        "</div>"+
	    "</div>");
}

function deleteWedstrijd(WedstrijdId){
	console.log("delete wedstrijd");
	var uri = "restservices/Wedstrijd/Delete/"+WedstrijdId;
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
    setTimeout(function() {
    	console.log("reloading");
    	location.reload();
    },1000)
}

function deleteLid(Bondsnummer){
	var uri = "restservices/Lid/Delete/"+Bondsnummer;
    $.ajax(uri, { 
        type: "DELETE", 
        beforeSend: function(xhr){
        	var token = window.sessionStorage.getItem("sessionToken");
        	xhr.setRequestHeader('Authorization', 'Bearer ' + token);
        },
        success: function(response) {
            $("#response").text("Something DELETED!");
        },
        error: function(response) {
            $("#response").text("RIP!");
        }
    });
    laadLeden();
}

$("#RegistreerLid").click(function(){
	var uri = "restservices/Lid/Update";
$.ajax(uri, { 
    type: "PUT", 
    data: $("#LidForum2").serialize(),
    beforeSend: function(xhr){
    	var token = window.sessionStorage.getItem("sessionToken");
    	xhr.setRequestHeader('Authorization', 'Bearer ' + token);
    },
    success: function(response) {
        $("#response").text("Something Inserted!");
        window.location.replace("index.html");
    },
    error: function(response) {
        $("#response").text("RIP!");
    }
}); 
});

$('#login').click(function(event){
	var data = $('#loginForm').serialize();
	
	$.post("restservices/authentication", data, function(response){
		window.sessionStorage.setItem("sessionToken", response);
	}).fail(function(jqXHR, textStatus, errorThrown){
		console.log(textStatus);
		console.log(errorThrown);
		$("#foutwachtwoord").html("Fout bondnsummer, wachtwoord combinatie");
	}).done(function(){
		var uri = "restservices/Lid/vindLid";
		 $.ajax(uri, { 
		        type: "POST", 
		        data: $('#loginForm').serialize(),
		        beforeSend: function(xhr){
		        	var token = window.sessionStorage.getItem("sessionToken");
		        	xhr.setRequestHeader('Authorization', 'Bearer ' + token);
		        },
		        success: function(response) {
		            $.each(response, function(key, value){
		            	window.sessionStorage.setItem("ROL", value["role"]);
		            	window.sessionStorage.setItem("Bondsnummer", value["Bondsnummer"]);
		            	window.sessionStorage.setItem("Leeftijd", value["Leeftijd"]);
		            	if(value["role"] == "Admin"){
		            		window.location.replace("HomePaginaAdmin.html");
		            	}
		            	else if(value["role"] == "User"){
		            		window.location.replace("HomePagina.html");
		            	}
		            });
		        },
		        error: function(response) {
		            $("#response").text("RIP!");
		        }
		    });
	});
	
	
});

function destroySession(){
	sessionStorage.clear();
}

function redirectWedstrijd(WedstrijdId){
	window.sessionStorage.setItem("WedstrijdId", WedstrijdId);
	window.location.replace("WedstrijdInfo.html");
}

var href3 = window.location.href.split("/")
if(href3[href3.length-1] == "WedstrijdInfo.html"){
	info(window.sessionStorage.getItem("WedstrijdId"));
}

function info(WedstrijdId){
	var uri = "restservices/Wedstrijd/find/"+WedstrijdId;
	 $.ajax(uri, { 
	        type: "GET", 
	        beforeSend: function(xhr){
	        	var token = window.sessionStorage.getItem("sessionToken");
	        	xhr.setRequestHeader('Authorization', 'Bearer ' + token);
	        },
	        success: function(response) {
	            $.each(response, function(key, value){
	            	$("#NaamWedstrijd").html("<p>Naam wedstrijd: "+ value["Naam"] +"</p>");
	            	$("#Dag").html("<p>Dag: "+ value["Datum"] +"</p>");
	            	$("#Tijd").html("<p>Tijd: "+ value["Begintijd"] +" tot "+ value["Eindtijd"] +"</p>");
	            	$("#TypeBoog").html("<p>Type boog: "+ value["Typeboog"] +"</p>");
	            	$("#Minimumleeftijd").html("<p>Minimumleeftijd: "+ value["Minimumleeftijd"] +"</p>");
	            	$("#Beschrijving").html("<p>Beschrijving: "+ value["Wedstrijdbeschrijving"] +"</p>");
	            	window.sessionStorage.setItem("WedstrijdId", value["WedstrijdId"]);
	            });
	        },
	        error: function(response) {
	            $("#response").text("RIP!");
	        }
	    });
}

var href = window.location.href.split("/");
if(href[href.length-1] == "Statistieken.html"){
	GewonnenVerloren(window.sessionStorage.getItem("Bondsnummer"))
}


var href2 = window.location.href.split("/")
if(href2[href.length-1] == "WedstrijdInfo.html"){
	checkValue();
}

	function GewonnenVerloren(Bondsnummer){
	var uri = "restservices/Inschrijvingen/GewonnenVerloren/"+Bondsnummer;
	$.ajax(uri, { 
	       type: "POST", 
	       beforeSend: function(xhr){
	       	var token = window.sessionStorage.getItem("sessionToken");
	       	xhr.setRequestHeader('Authorization', 'Bearer ' + token);
	       },
	       success: function(response) {
	           $.each(response, function(key, value){
	        	   var ctx = document.getElementById('myChart2').getContext('2d');
	
	        	   var myPieChart = new Chart(ctx,{
	        		   responsive: true,
	        	       type: 'pie',
	        	       maintainAspectRatio: true,
	        	       data: {
	        	           labels: ["Gewonnen", "Verloren", "Nog te spelen"],
	        	           datasets: [{
	        	               backgroundColor: [
	        	                   'rgb(20, 255, 20)',
	        	                   'rgb(255, 20, 20)',
	        	                   'rgb(255, 206, 86)'],
	        	               borderColor: 'rgb(255, 99, 132)',
	        	               data: [value["Gewonnen"],value["Verloren"],value["NogTeSpelen"]],
	        	           }]
	        	       },
	        	       options: {}
	        	   });
	           });
	       },
	       error: function(response) {
	           $("#response").text("RIP!");
	       }
	   });
	}
	
	
	var href1 = window.location.href.split("/");
	if(href1[href1.length-1] == "Statistieken.html"){
		GemiddeldePerMaand(window.sessionStorage.getItem("Bondsnummer"), 2017);
	}
	
	function GemiddeldePerMaand(Bondsnummer, Jaar){
		var uri = "restservices/Inschrijvingen/averageScore/"+Bondsnummer+"/"+Jaar;
		 $.ajax(uri, { 
		        type: "POST", 
		        beforeSend: function(xhr){
		        	var token = window.sessionStorage.getItem("sessionToken");
		        	xhr.setRequestHeader('Authorization', 'Bearer ' + token);
		        },
		        success: function(response) {
		            	var ctx = document.getElementById('myChart').getContext('2d');
		            	var myChart = new Chart(ctx, {
		            	  type: 'line',
		            	  data: {
		            	    labels: ['Januari', 'februari', 'maart','April', 'mei', 'juni', 'juli', 'augustus', 'september', 'oktober', 'november', 'december'],
		            	    datasets: [{
		            	      label: 'Scoren',
		            	      data: [response[0]["Score"],response[1]["Score"],response[2]["Score"],response[3]["Score"],response[4]["Score"],response[5]["Score"],response[6]["Score"],response[7]["Score"],response[8]["Score"],response[9]["Score"],response[10]["Score"],response[11]["Score"]],
		            	      backgroundColor: "rgba(153,255,51,0.4)"
		            	    }]
		            	  }
		            	});
		        },
		        error: function(response) {
		            $("#response").text("RIP!");
		        }
		    });
	}

	$("#Inschrijven").click(function(){
		var uri = "restservices/Inschrijvingen/Insert/"+window.sessionStorage.getItem("Bondsnummer")+"/"+window.sessionStorage.getItem("WedstrijdId");
	$.ajax(uri, { 
	    type: "POST", 
	    beforeSend: function(xhr){
	    	var token = window.sessionStorage.getItem("sessionToken");
	    	xhr.setRequestHeader('Authorization', 'Bearer ' + token);
	    },
	    success: function(response) {
	        $("#response").text("Something Inserted!");
	        window.location.replace("WedstrijdLijst_lid.html");
	    },
	    error: function(response) {
	        $("#response").text("RIP!");
	    }
	}); 
	});
	
function checkValue(){
	var uri = "restservices/Inschrijvingen/checkInschrijving/"+window.sessionStorage.getItem("Bondsnummer")+"/"+window.sessionStorage.getItem("WedstrijdId");
	$.ajax(uri, { 
	    type: "POST", 
	    beforeSend: function(xhr){
	    	var token = window.sessionStorage.getItem("sessionToken");
	    	xhr.setRequestHeader('Authorization', 'Bearer ' + token);
	    },
	    success: function(response) {
	    	 $.each(response, function(key, value){
	    		 if(value["Ingeschreven"] == true){
	    			 $("#inschrijfButton").html("<input type='button' class='btn-lg btn-primary btn-block' id='Uitschrijven' value='Uitschrijven'>");
	    		 }
	    	 });  
	    },
	    error: function(response) {
	        $("#response").text("RIP!");
	    }
	}); 
}

$(document).ready ( function () {
	$(document).on('click', "#Uitschrijven", function(){
		var uri = "restservices/Inschrijvingen/Delete/"+window.sessionStorage.getItem("Bondsnummer")+"/"+window.sessionStorage.getItem("WedstrijdId");
	    $.ajax(uri, { 
	        type: "DELETE", 
	        beforeSend: function(xhr){
	        	var token = window.sessionStorage.getItem("sessionToken");
	        	xhr.setRequestHeader('Authorization', 'Bearer ' + token);
	        },
	        success: function(response) {
	            $("#response").text("Something Deleted!");
	            window.location.replace("WedstrijdLijst_lid.html");
	        },
	        error: function(response) {
	            $("#response").text("RIP!");
	        }
	    }); 
	});
});

function setScores(WedstrijdId){
	var uri = "restservices/Inschrijvingen/findInschrijvingen/"+WedstrijdId;
	 $.ajax(uri, { 
	        type: "POST", 
	        beforeSend: function(xhr){
	        	var token = window.sessionStorage.getItem("sessionToken");
	        	xhr.setRequestHeader('Authorization', 'Bearer ' + token);
	        },
	        success: function(response) {
	            $.each(response, function(key, value){
	            	$("#LedenLijstScore").append("<a href='#' class='list-group-item'>"+ value["Voornaam"] + " "+ value["Achternaam"] +"<form id='Scoren"+value["Bondsnummer"]+"'><input type='number' name='scoren'><select name='selectWhoWon'><option value='false'>Verloren</option><option value='true'>Gewonnen</option></select><input type='button' id='scorenInserten' class='btn-info pull-right' onclick='BondsnummerDoorsturen("+value["Bondsnummer"]+")' value='opslaan'></form></a>");
	            });
	        },
	        error: function(response) {
	            $("#response").text("RIP!");
	        }
	    });
}

var href5 = window.location.href.split("/");
if(href5[href5.length-1] == "ScoreInvoeren.html"){
	setScores(window.sessionStorage.getItem("WedstrijdId_2"));
}

function Redirect(WedstrijdId){
	window.sessionStorage.setItem("WedstrijdId_2", WedstrijdId);
	window.location.replace("ScoreInvoeren.html");
}

function BondsnummerDoorsturen(Bondsnummer){
	var uri = "restservices/Inschrijvingen/Update/"+Bondsnummer+"/"+window.sessionStorage.getItem("WedstrijdId_2");
	$.ajax(uri, { 
	    type: "PUT",
	    data: $("#Scoren"+Bondsnummer).serialize(),
	    beforeSend: function(xhr){
	    	var token = window.sessionStorage.getItem("sessionToken");
	    	xhr.setRequestHeader('Authorization', 'Bearer ' + token);
	    },
	    success: function(response) {
	        $("#response").text("Something Updated!");
	        location.reload();
	    },
	    error: function(response) {
	        $("#response").text("RIP!");
	    }
	}); 
}
