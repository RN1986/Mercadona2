package com.example.demoA.etatDesLieux;

import com.example.demoA.appartements.AppartementService;
import com.example.demoA.locataires.LocataireService;
import com.example.demoA.paiement.PaiementService;
import org.springframework.stereotype.Controller;

@Controller
public class EtatDesLieuxController {

    private final LocataireService locataireService;
    private final AppartementService appartementService;
    private final PaiementService paiementService;
    private final EtatDesLieuxService etatDesLieuxService;

    public EtatDesLieuxController(LocataireService locataireService, AppartementService appartementService, PaiementService paiementService, EtatDesLieuxService etatDesLieuxService) {
        this.locataireService = locataireService;
        this.appartementService = appartementService;
        this.paiementService = paiementService;
        this.etatDesLieuxService = etatDesLieuxService;
    }
}
