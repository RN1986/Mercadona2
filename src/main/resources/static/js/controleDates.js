//Ce script permet de contrôler la saisie des dates lors de la création d'une promotion
      document.getElementById("submitBtn").addEventListener("click", function(event){
            var datedebut = new Date(document.getElementById("datedebut").value);
            var datefin = new Date(document.getElementById("datefin").value);
            var aujourdhui = new Date();

            // Définir l'heure à zéro pour toutes les dates afin de ne pas prendre en compte les heures dans la comparaison avec la date actuelle
            datedebut.setHours(0, 0, 0, 0);
            datefin.setHours(0, 0, 0, 0);
            aujourdhui.setHours(0, 0, 0, 0);

            console.log("aujourdhui : " + aujourdhui);
            console.log("datedebut : " + datedebut);
            console.log("datefin : " + datefin);

          //cache le div "succes" en cas de relance du script sans rechargement de la page
            document.getElementById("succes").style.display = "none";

            if (datedebut < aujourdhui) {
                dateError.innerHTML = "La date de début doit être postérieure ou égale à la date actuelle.";
                 event.preventDefault();
            } else if (datefin < datedebut) {
                dateError.innerHTML = "La date de fin doit être postérieure à la date de début.";
                 event.preventDefault();
            } else {
                dateError.innerHTML = "";


            }
        });