package com.example.demoA.categorie;


import com.example.demoA.produit.ProduitService;
import com.example.demoA.promotion.PromotionService;
import org.springframework.stereotype.Controller;


@Controller
public class CategorieController {
    private final ProduitService produitService;
    private final PromotionService promotionService;
    private final CategorieService categorieService;

    public CategorieController(ProduitService produitService, PromotionService promotionService, CategorieService categorieService) {
        this.produitService = produitService;
        this.promotionService = promotionService;
        this.categorieService = categorieService;
    }

/*
    //Créer Paiements
    @RequestMapping(path = "/createPaiement", method = RequestMethod.GET)
    public String getPaiementsCreated(Model model) {
        List<Paiement> paiements = paiementService.getPaiements();
        model.addAttribute("paiements", paiements);
        model.addAttribute("paiement_new", new Paiement());
        List<Appartement> appartements = appartementService.getAppartements();
        model.addAttribute("appartements", appartements);
           return "CreerPaiement";
    }

    @RequestMapping(path = "/createPaiement", method = RequestMethod.POST)
    public RedirectView createPaiement(RedirectAttributes redirectAttributes,
                                       @ModelAttribute Paiement paiement_new,
                                       @ModelAttribute ("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String dateStr) {
        System.out.println("dateStr=" + dateStr);

        try {
            LocalDate date = LocalDate.parse(dateStr);
            paiement_new.setDate(date);
            paiementService.creerPaiement(paiement_new);
            String message = "Le paiement de <b>" + paiement_new.getMontant() + " € </b> a été ajouté ✨.";
            RedirectView redirectView = new RedirectView("/createPaiement", true);
            redirectAttributes.addFlashAttribute("paiementMessage", message);
            return redirectView;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /*
    @RequestMapping(path = "/createPaiement", method = RequestMethod.POST)
    public RedirectView createPaiement(RedirectAttributes redirectAttributes,
                                       @ModelAttribute Paiement paiement_new,
                                       @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String dateStr) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr,formatter);


        paiement_new.setDate(date);
        paiementService.creerPaiement(paiement_new);
        String message = "Le paiement de <b>" + paiement_new.getMontant() + " € </b> a été ajouté ✨.";
        RedirectView redirectView = new RedirectView("/createPaiement", true);
        redirectAttributes.addFlashAttribute("paiementMessage", message);
        return redirectView;
    }
/*
/*
    @RequestMapping(path = "/createPaiement", method = RequestMethod.POST)
    public RedirectView createPaiement(RedirectAttributes redirectAttributes, @ModelAttribute Paiement paiement_new,
                                       @ModelAttribute LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        //String date = "16/08/2016";

        date = LocalDate.parse(date, formatter);
        paiementService.creerPaiement(paiement_new);
        String message = "Le paiement de <b>" + paiement_new.getMontant() + " € </b> a été ajouté ✨.";
        RedirectView redirectView = new RedirectView("/createPaiement", true);
        redirectAttributes.addFlashAttribute("paiementMessage", message);
        return redirectView;
    }
    */
}
