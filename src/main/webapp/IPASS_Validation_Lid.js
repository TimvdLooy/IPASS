$("input, textarea").alphanum({
    allow              : '!@#$%&*(){}|:.,<>~+=_-?/',
    disallow           : '',
    allowSpace         : true,
    allowNumeric       : true,
    allowUpper         : true,
    allowLower         : true,
    allowCaseless      : true,
    allowLatin         : true,
    allowOtherCharSets : true,
    forceUpper         : false,
    forceLower         : false,
    maxLength          : NaN
});
$("#LidForum").validate({
       rules: {
           Voornaam: {
               required: true,
               maxlength:  30
           },
           Achternaam:{
               required: true,
               maxlength: 30
           },
           Bondsnummer:{
               required: true,
               maxlength:6
           },
           Leeftijd:{
               required:true,
               maxlength:3
           },
           email:{
        	   required:true,
               maxlength:60,
               email: true
           },
           Telefoonnummer:{
               required:true,
               maxlength:10
           },


       },

       messages: {
           Voornaam:{
               required: "Voornaam niet ingevoerd!",
               maxlength: "Je voornaam is te lang sorry",

           },
           Achternaam:{
               required: "Achternaam niet ingevoerd!",
               maxlength: "Je achternaam is lang sorry"
           },
           Bondsnummer:{
               required: "Voer een bondsnummer in. Deze is te vinden op de lidmaatschaps pas.",
               maxlength: "De lengte van dit bondsnummer is incorrect!"

           },
           Leeftijd:{
               required: "Voer de leeftijd in",
               maxlength: "Niemand is ouder dan 1000 jaar"
           },
           email:{
        	   required: "email is niet ingevoerd!",
               maxlength:"De email van dit persoon is te lang",
               email: "Voer een geldig email adres in"
           },
           Telefoonnummer:{
               required: "Telefoonnummer is niet ingevoerd!",
               maxlength: "Dit telefoonnummer is te lang voor een geldig Nederlands telefoonnummer"
           },
       },
       
       errorElement : 'div',
       errorPlacement: function(error, element) {
         var placement = $(element).data('error');
         if (placement) {
           $(placement).append(error);
         } else {
           error.insertAfter(element);
         }
     },
     
     submitHandler: function(form) {
       var uri = "http://localhost:4711/IPASS/restservices/Lid/Insert";
	    $.ajax(uri, { 
	        type: "POST", 
	        data: $("#LidForum").serialize(),
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

       event.preventDefault();
   }
     
    });