package com.example.demoA.Produit;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demoA.Administrateurs.Administrateurs;
import com.example.demoA.Administrateurs.AdministrateursService;
import com.example.demoA.Categorie.Categorie;
import com.example.demoA.Categorie.CategorieRepository;
import com.example.demoA.Categorie.CategorieService;
import com.example.demoA.Promotion.Promotion;
import com.example.demoA.Promotion.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class ProduitController {

	@Value("${uploadDir}")
	private String uploadFolder;
@Autowired
	private final ProduitService produitService;
private final AdministrateursService administrateursService;
	private final CategorieService categorieService;

	private final PromotionService promotionService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final ProduitRepository produitRepository;
	private final CategorieRepository categorieRepository;

	public ProduitController(ProduitService produitService, AdministrateursService administrateursService, CategorieService categorieService, PromotionService promotionService,
							 ProduitRepository produitRepository,
							 CategorieRepository categorieRepository) {
		//this.uploadFolder = uploadFolder;
		this.produitService = produitService;
		this.administrateursService = administrateursService;
		this.categorieService = categorieService;
		this.promotionService = promotionService;
		this.produitRepository = produitRepository;
		this.categorieRepository = categorieRepository;
	}

	@GetMapping("/")
	public String catalogue(Model model) {

		List<Produit> produits = produitService.findAll();
		model.addAttribute("produits", produits);
		model.addAttribute("produitService",produitService);
		return "Catalogue";
	}

	@GetMapping(value = {"/administrationauthentification"})
	public String AuthentificationAdministrationGET(){
		return "authentification";
	}
/*
	@PostMapping(value = {"/administrationauthentification"})
	public String AuthentificationAdministration(@RequestParam(value = "user") String user,@RequestParam(value = "password") String password, Model model ) {
		//passwordsInit();
	//	if (passwords.get(user).equals(password)){
			return "AccueilAdministration";
	}
*/


	Map<String, String> passwords = new HashMap(); //Contient le mot de passe associé à chaque utilisateur

	public void passwordsInit() {
			List<Administrateurs> administrateurs = administrateursService.findAll();
			for (Administrateurs administrateur : administrateurs) {
				passwords.put(administrateur.getNomutilisateur(), administrateur.getMotdepasse());
			}
		}



@PostMapping(value = {"/administrationauthentification"})
public String AuthentificationAdministration(@RequestParam(value = "user") String user,@RequestParam(value = "password") String password, HttpSession session, Model model) {
	passwordsInit();

	if (passwords.containsKey(user) && passwords.get(user).equals(password)) {
		// L'utilisateur est authentifié, enregistre l'état de l'authentification dans la session
		session.setAttribute("authenticated", true);
		return "AccueilAdministration";
	} else {
		model.addAttribute("erreurAuthentification", "Le nom d'utilisateur ou le mot de passe est incorrect. Veuillez essayer à nouveau");
		// L'utilisateur n'est pas authentifié, renvoie à la page d'authentification
		return "authentification";
	}
}

	@PostMapping(value = {"/administration/quit"})
	public String DésauthentificationAdministration( HttpSession session) {

			// L'utilisateur est désauthentifié, enregistre l'état de l'authentification dans la session
			session.setAttribute("authenticated", null);
			return "authentification";

	}


	@GetMapping(value = {"/administration"})
	public String AccueilAdministrationGET() {

		return "AccueilAdministration";
	}


	@PostMapping(value = {"/administration"})
	public String AccueilAdministrationPOST() {
		return "AccueilAdministration";
	}
/*
	@PostMapping(value = {"/administration"})
	public String AccueilAdministration(@RequestParam(value = "user") String user,@RequestParam(value = "password") String password, Model model ) {
		passwordsInit();

		if (!passwords.containsKey(user)) {
			return "authentification";}

			if (passwords.get(user).equals(password)) {
			return "AccueilAdministration";
		}
		 if ((!passwords.get(user).equals(password))) {
			//model.addAttribute("erreurAuthentification","Le nom d'utilisateur ou le mot de passe est incorrect. Veuillez essayer à nouveau");
			return "authentification";
		} else 	return "authentification";


	}
*/





	@GetMapping(value = {"/administration/creerproduit"})
	public String ajoutProduit(Model model) {
		//model.addAttribute("produit_new", new Produit());
		//List<Produit> produits = produitService.findAll();
		//model.addAttribute("produits", produits);
		List <Categorie> categories = categorieService.findAll();
		model.addAttribute("categories", categories);
		return "CreerProduit";
	}

	@RequestMapping(path = "/administration/rechercherproduit", method = RequestMethod.GET)
	public String RechercherProduit(Model model) {
		List<Produit> produits = produitService.findAll();
		model.addAttribute("produits", produits);
		model.addAttribute("produit_new", new Produit());
		model.addAttribute("produitService",produitService);

		return "RechercherProduit";
	}

	@RequestMapping(path = "/administration/rechercherproduit", method = RequestMethod.POST)
	public String TrouverProduit(Model model, @ModelAttribute("libelle") String libelle,@ModelAttribute("id") Long id) {
		model.addAttribute("produit_new", new Produit());
		model.addAttribute("produitService",produitService);

		if (id!=null && libelle.isEmpty()){
			Produit produitTrouve = produitService.getById(id);
			List <Produit> produitsTrouves = new ArrayList<>();
			produitsTrouves.add(produitTrouve);
			model.addAttribute("produitsTrouves", produitsTrouves);
		} else if (!libelle.isEmpty()) {
		List <Produit> produitsTrouves = produitService.getByLibelle(libelle);
			model.addAttribute("produitsTrouves", produitsTrouves);
		}
		else return "RechercherProduit" ;
System.out.println("ZZZZZ ID : "+id);
		List <Categorie> categories = categorieService.findAll();
		model.addAttribute("categories", categories);
		return "RechercherProduit";
	}

	@RequestMapping(path = "/administration/produit/{id}", method = RequestMethod.GET)
	public String FicheProduit(Model model, @PathVariable("id") Long id) {

		model.addAttribute("produit", produitService.findById(id));
		model.addAttribute("updatedProduit", produitService.findById(id));
		List <Categorie> categories = categorieService.findAll();
		categories.remove(categorieService.getSelonLibelle(produitService.findById(id).getCategorie().getLibelle()));
		model.addAttribute("categories", categories);
		model.addAttribute("produitService",produitService);

		return "ficheProduit";
	}

	@RequestMapping(path = "/administration/produit/{id}", method = RequestMethod.POST)
	public RedirectView ModifierProduit(RedirectAttributes redirectAttributes,Model model, @PathVariable("id") Long id, @RequestParam("libelle")String libelle,
										@RequestParam("description")String description, @RequestParam("categorie")String categorie, @RequestParam("prixDeBase")String prixDeBase) {

		model.addAttribute("produitService", produitService);
		Produit updatedProduit = produitService.findById(id);
		model.addAttribute("updatedProduit", updatedProduit);
		List <Categorie> categories = categorieService.findAll();
		model.addAttribute("categories", categories);


		updatedProduit.setLibelle(libelle);
		updatedProduit.setDescription(description);

		//updatedProduit.setPromotion(new Promotion(datedebut, datefin, remise));
		updatedProduit.setCategorie(categorieService.getSelonLibelle(categorie));
		updatedProduit.setPrixDeBase(Double.parseDouble(prixDeBase));

		produitService.update(updatedProduit);

		RedirectView redirectView = new RedirectView("/administration", true);
		return redirectView;
	}


	@RequestMapping(path = "/administration/produit/{id}/promotion", method = RequestMethod.POST)
	public RedirectView AppliquerPromo(Model model, @PathVariable("id") Long id, @RequestParam("datedebut")@DateTimeFormat(pattern = "yyyy-MM-dd") String datedebutStr,
										@RequestParam("datefin")@DateTimeFormat(pattern = "yyyy-MM-dd") String datefinStr, @RequestParam("remise")int remise) {

		LocalDate datedebut = LocalDate.parse(datedebutStr);
		LocalDate datefin = LocalDate.parse(datefinStr);
		model.addAttribute("produitService", produitService);
		Produit promoProduit = produitService.findById(id);
		model.addAttribute("promoProduit", promoProduit);

		promoProduit.setPromotion(new Promotion(datedebut, datefin, remise));

		produitService.update(promoProduit);

		RedirectView redirectView = new RedirectView("/administration/produit/{id}", true);
		return redirectView;
	}

	@RequestMapping(path = "/administration/produit/{id}/supprimer", method = RequestMethod.POST)
	public RedirectView SupprimerProduit(Model model, @PathVariable("id") Long id) {


		model.addAttribute("produitService", produitService);
		Produit produit = produitService.findById(id);
		model.addAttribute("produit", produit);

		produitService.deleteById(id);

		RedirectView redirectView = new RedirectView("/administration/produit/{id}", true);
		return redirectView;
	}

/*
	@PostMapping("/image/saveImageDetails")
	public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("name") String name,
			@RequestParam("price") double price, @RequestParam("description") String description, Model model, HttpServletRequest request
			,final @RequestParam("image") MultipartFile file) {


		try {
			//String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
			}

			//logs
			String[] names = name.split(",");
			String[] descriptions = description.split(",");
			Date createDate = new Date();
			log.info("Name: " + names[0]+" "+filePath);
			log.info("description: " + descriptions[0]);
			log.info("price: " + price);
			//

			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			ImageGallery imageGallery = new ImageGallery();
			imageGallery.setName(names[0]);
			imageGallery.setImage(imageData);
			imageGallery.setPrice(price);
			imageGallery.setDescription(descriptions[0]);
			imageGallery.setCreateDate(createDate);
			imageGalleryService.saveImage(imageGallery);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


	}
	*/
@PostMapping("/image/saveImageDetails")
public @ResponseBody ResponseEntity<?> creerProduit(@RequestParam("idcategorie") Long idcategorie,
													 @RequestParam("prixDeBase") double prixDeBase, @RequestParam("description") String description, @RequestParam("libelle") String libelle,
													 Model model, HttpServletRequest request,
													final @RequestParam("image") MultipartFile file) {

	try {

		String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
		String fileName = file.getOriginalFilename();
		String filePath = Paths.get(uploadDirectory, fileName).toString();
		if (fileName == null || fileName.contains("..")) {
			model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence " + fileName);
			return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
		}
		/*String[] libelles = libelle.split(",");
		String[] descriptions = description.split(",");*/
		try {
			File dir = new File(uploadDirectory);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(Files.newOutputStream(new File(filePath).toPath()));
			stream.write(file.getBytes());
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();

		}

		byte[] image = file.getBytes();
		Produit produit = new Produit();
		//produit.setLibelle(libelles[0]);
		produit.setLibelle(libelle);
		produit.setImage(image);
		produit.setPrixDeBase(prixDeBase);
		produit.setCategorie(categorieService.getById(idcategorie));
		//produit.setCategorie(categorieService.getSelonLibelle(categorie));
		produit.setPromotion(null);
		produit.setDescription(description);
		//produit.setDescription(descriptions[0]);
		produit.setDatecreation(new Date());
		produitService.save(produit);

		return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
	} catch (Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}


}

	@GetMapping("/image/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Id :: " + id);
		Optional<Produit> image = produitService.getImageById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(image.get().getImage());
		response.getOutputStream().close();
	}
/*
	@GetMapping("/image/imageDetails")
	String showProductDetails(@RequestParam("id") Long id, Model model) {
		try {
			log.info("Id :: " + id);
			if (id != 0) {
				Optional<Produit> produit = produitService.getImageById(id);

				log.info("products :: " + produit);
				if (produit.isPresent()) {
					model.addAttribute("id", produit.get().getId());
					model.addAttribute("description", produit.get().getDescription());
					model.addAttribute("libelle", produit.get().getLibelle());
					model.addAttribute("prix", produitService.calculerPrix(produitService.findById(id)));
					//model.addAttribute("promotion", promotionService.getSelonProduit());
					model.addAttribute("catégorie", produit.get().getCategorie().getLibelle());
					return "detailProduit";
				}
				return "redirect:/home";
			}
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
	}
*/


/*
	@GetMapping("/")
	String catalogue(Model model) {
		List<Produit> produits = produitService.findAll();

		for (Produit produit : produits) {
			try {
				if (produit.getPromotion() != null && produit.getPromotion().getDatedebut().isBefore(LocalDate.now()) && produit.getPromotion().getDatefin().isAfter(LocalDate.now())) {
					model.addAttribute("promotion_" + produit.getId(), "Promotion de " + promotionService.afficherRemiseEnPourcentage(produit.getPromotion().getRemise()) + " jusqu'au " + produit.getPromotion().getDatefin());
					model.addAttribute("prix_" + produit.getId(), produit.getPrix() - produit.getPrix() * produit.getPromotion().getRemise());
				} else {
					model.addAttribute("promotion_" + produit.getId(), "");
					model.addAttribute("prix_" + produit.getId(), produit.getPrix());
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		model.addAttribute("produits", produits);
		return "Catalogue";
	}

*/

/*
	@GetMapping("/")
	String catalogue(Model model) {
		List<Produit> produits = produitService.findAll();

		model.addAttribute("produits", produits);

		//model.addAttribute("remise",promotionService.afficherRemiseEnPourcentage(produit.getPromotion().getRemise()));
		try {
			if (produit.getPromotion() != null && produit.getPromotion().getDatedebut().isBefore(LocalDate.now()) && produit.getPromotion().getDatefin().isAfter(LocalDate.now())) {
				model.addAttribute ("promotion", "Promotion de"+promotionService.afficherRemiseEnPourcentage(produit.getPromotion().getRemise())+"jusqu'au "+produit.getPromotion().getDatefin());
				model.addAttribute ("prix",produit.getPrix()-produit.getPrix()*produit.getPromotion().getRemise());
			} else {
				model.addAttribute ("promotion", "");
				model.addAttribute ("prix",produit.getPrix());
			}
		} catch (Exception e)
		{
System.out.println(e);
		}
		return "Catalogue";

}
*/
}

