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
$("#WedstrijdForum").validate({
       rules: {
           Wedstrijdnaam: {
               required: true,
               maxlength:  49
           },
           Begintijd:{
               required: true,
               maxlength: 8
           },
           Eindtijd:{
               required: true,
               maxlength:8
           },
           Datum:{
               required:true,
               maxlength:10
           },
           Minimumleeftijd:{
               maxlength: 3
           },
           TypeBoog:{
               required:true,
               maxlength:19
           },
           Wedstrijdbeschrijving:{
        	   maxlength:300
           },


       },

       messages: {
           Wedstrijdnaam:{
               required: "Wedstrijdnaam niet correct ingevoerd.",
               maxlength: "Je wedstrijdnaam is te lang. hou het binnen 49 tekens.",

           },
           Begintijd:{
               required: "U heeft geen begintijd ingevoerd",
               maxlength: "De begintijd is te lang"
           },
           Eindtijd:{
               required: "Voer een Eindtijd in",
               maxlength: "De eindtijd is te lang"

           },
           Datum:{
               required: "Voer een datum in",
               maxlength: "De datum is te lang"
           },
           Minimimumleeftijd:{
               maxlength:"De maxlength mag niet langer zijn dan 3 nummers. niemand is ouder dan 1000 jaar."
           },
           TypeBoog:{
               required: "Wat voor type boog",
               maxlength: "Je plaatsnaam mag maximaal 60 tekens bevatten."
           },
           Wedstrijdbeschrijving:{
               maxlength: "Probeer de beschrijving korter te houden dan 300 tekens"
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
       var uri = "http://localhost:4711/IPASS/restservices/Wedstrijd/insert";
	    $.ajax(uri, { 
	        type: "POST", 
	        data: $("#WedstrijdForum").serialize(),
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
    $('#Begintijd').formatter({
          'pattern': '{{99}}:{{99}}:{{99}}',
});
    $('#Eindtijd').formatter({
        'pattern': '{{99}}:{{99}}:{{99}}',
});
    $('#Datum').formatter({
        'pattern': '{{9999}}-{{99}}-{{99}}',
});