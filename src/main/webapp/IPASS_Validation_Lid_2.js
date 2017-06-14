$("input, textarea").alphanum({
    allow              : '',
    disallow           : '!@#$%&*(){}|:.,<>~+=_-?/',
    allowSpace         : false,
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

$("#Bondsnummer").alphanum({
    allow              : '1234567890',
    disallow           : '!@#$%&*(){}|:.,<>~+=_-?/qwertyuiopasdfghjklzxcvbnm',
    allowSpace         : false,
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

$("#LidForum2").validate({
       rules: {
           Bondsnummer: {
               required: true,
               maxlength:  6
           },
           Wachtwoord:{
               required: true,
               maxlength: 30
           },
           WachtwoordBevestiging:{
               required: true,
               equalTo:"#Wachtwoord"
           },

       },

       messages: {
           Bondsnummer:{
               required: "Bondsnummer niet ingevoerd!",
               maxlength: "Bondsnummer is maar 6 nummers lang",

           },
           Wachtwoord:{
               required: "Wachtwoord niet ingevoerd!",
               maxlength: "je wachtwoord mag niet langer zijn dan 30 tekens"
           },
           WachtwoordBevestiging:{
               equalTo: "Dit wachtwoord komt niet overeen met het eerst ingevoerde wachtwoord.",
               required:"Wachtwoord bevestiging niet ingevoerd!"
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
    	 console.log("check");
       var uri = "http://localhost:4711/IPASS/restservices/Lid/Update";
	    $.ajax(uri, { 
	        type: "PUT", 
	        data: $("#LidForum2").serialize(),
	    }); 

       event.preventDefault();
   }
     
    });