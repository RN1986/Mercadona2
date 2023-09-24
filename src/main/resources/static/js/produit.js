   $(document).ready(function() {

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

        if (libelle === "" || idcategorie === "" || prixDeBase === "" || isNaN(prixDeBase) || description === "" ||file === "") {
        	$("#submit").prop("disabled", false);
        	 $("#error").html("Oops! dans le If");
        	  $('#error').delay(10000).fadeOut('slow');

        } else {
        	$("#submit").prop("disabled", false);
        	 $("#error").html("Oops! dans le else");
        	  $('#error').delay(10000).fadeOut('slow');

                    $.ajax({
                        type: 'POST',
                        enctype: 'multipart/form-data',
                        data: data,
                        url: "/administration/creerproduit",
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function(data, statusText, xhr) {
                        console.log(xhr.status);
                        if(xhr.status == "200") {
                                       	$("#form")[0].reset();
                        	$('#success').css('display','block');
                            $("#error").text("");
                            $("#success").html("Produit ajouté");
                            $('#success').delay(2000).fadeOut('slow');
                         }	   
                        },
                        error: function(e) {
                            console.error("Erreur AJAX :", e);
                            for (item of data){
                            console.log(item[0],item[1]);}

                            $('#error').delay(10000).fadeOut('slow');

                        }
                    });
        }
            });
        });
