   $(document).ready(function() {
   // $('#loader').hide();
    $("#submit").on("click", function() {
    	$("#submit").prop("disabled", true);
        var libelle = $("#libelle").val();
       var idcategorie = $("#idcategorie").val();
       var prixDeBase = $("#prixDeBase").val();
       var file = $("#image").val();
       var description = $("#description").val();
       var form = $("#form").serialize();
        var data = new FormData($("#form")[0]);
    	data.set('libelle', libelle);
    	data.set('prixDeBase', prixDeBase);
    	data.set('idcategorie', idcategorie);
    	data.set('description', description);
        //data.set('file', file);
    	//alert(data);
     //   $('#loader').show();
        if (libelle === "" || idcategorie === "" || prixDeBase === "" || isNaN(prixDeBase) || description === "" ||file === "") {
        	$("#submit").prop("disabled", false);
        	 $("#error").html("Oops! dans le If");
        	  $('#error').delay(10000).fadeOut('slow');
           // $('#loader').hide();
           // $("#libelle").css("border-color", "red");
          //  $("#image").css("border-color", "red");
           // $("#idcategorie").css("border-color", "red");
           // $("#prixDeBase").css("border-color", "red");
           // $("#description").css("border-color", "red");
           // $("#error_name").html("Champ à renseigner");
          //  $("#error_file").html("Champ à renseigner");
          //  $("#error_price").html("Champ à renseigner");
           // $("#error_description").html("Champ à renseigner");
        } else {
        	$("#submit").prop("disabled", false);
        	 $("#error").html("Oops! dans le else");
        	  $('#error').delay(10000).fadeOut('slow');
          //  $("#libelle").css("border-color", "");
          //  $("#image").css("border-color", "");
          //  $("#idcategorie").css("border-color", "");
          //  $("#prixDeBase").css("border-color", "");
          //  $("#description").css("border-color", "");
          //  $('#error_name').css('opacity', 0);
          //  $('#error_file').css('opacity', 0);
          //  $('#error_price').css('opacity', 0);
          //  $('#error_description').css('opacity', 0);
                    $.ajax({
                        type: 'POST',
                        enctype: 'multipart/form-data',
                        data: data,
                        url: "/image/saveImageDetails",
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function(data, statusText, xhr) {
                        console.log(xhr.status);
                        if(xhr.status == "200") {
                        //	$('#loader').hide();
                        	$("#form")[0].reset();
                        	$('#success').css('display','block');
                            $("#error").text("");
                            $("#success").html("Product Inserted Succsessfully.");
                            $('#success').delay(2000).fadeOut('slow');
                         }	   
                        },
                        error: function(e) {
                            console.error("Erreur AJAX :", e);
                            for (item of data){
                            console.log(item[0],item[1]);}
                        	//$('#loader').hide();
                        //	$('#error').css('display','block');
                          //  $("#error").html("Oops! something went wrong.");
                            $('#error').delay(10000).fadeOut('slow');
                            //location.reload();
                        }
                    });
        }
            });
        });
