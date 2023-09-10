package com.example.demoA.Produit;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

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
// Affiche la page Catalogue
	@GetMapping("/")
	public String catalogue(Model model) {

		List<Produit> produits = produitService.findAll();
		model.addAttribute("produits", produits);
		model.addAttribute("produitService",produitService);
		//System.out.println(hashPassword("mdp"));
		return "Catalogue";
	}
/*
	private String hashPassword(String mdp) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(mdp);
	}*/
/*
	// Affiche la page d'authentification pour accéder à l'espace d'administration
	@GetMapping(value = {"/administrationauthentification"})
	public String AuthentificationAdministrationGET(){
		return "authentification";
	}
*/
	/*
	Map<String, String> passwords = new HashMap(); //Contient le mot de passe associé à chaque utilisateur

	public void passwordsInit() {
			List<Administrateurs> administrateurs = administrateursService.findAll();
			for (Administrateurs administrateur : administrateurs) {
				passwords.put(administrateur.getNomutilisateur(), administrateur.getMotdepasse());
			}
		}
		*/
/*
	// Ne pas stocker les mots de passe dans une structure de données en mémoire

	// Modifier la méthode AuthentificationAdministration() pour utiliser le hachage cryptographique
	@PostMapping(value = {"/administrationauthentification"})
	public String AuthentificationAdministration(@RequestParam(value = "user") String user,
												 @RequestParam(value = "password") String password,
												 HttpSession session,
												 Model model) {
		// Récupérez le mot de passe haché associé à l'utilisateur depuis la base de données
		String motDePasseHacheEnBase = administrateursService.getMotDePasseHache(user);

		if (motDePasseHacheEnBase != null && bcrypt.checkpw(password, motDePasseHacheEnBase)) {
			// L'utilisateur est authentifié, enregistre l'état de l'authentification dans la session
			session.setAttribute("authenticated", true);
			return "AccueilAdministration";
		} else {
			model.addAttribute("erreurAuthentification", "Le nom d'utilisateur ou le mot de passe est incorrect. Veuillez essayer à nouveau");
			// L'utilisateur n'est pas authentifié, renvoie à la page d'authentification
			return "authentification";
		}
	}

*/
/*
//Verifie l'authentification du profil administrateur et dirige vers l'espace d'administration le cas échéant
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
*/
	/*
//Administration : Ferme la session d'administration
	@PostMapping(value = {"/administrationlogout"})
	public String DésauthentificationAdministration( HttpSession session) {

			// L'utilisateur est désauthentifié, enregistre l'état de l'authentification dans la session
			session.setAttribute("authenticated", null);
			return "authentification";

	}
*/
//Administration : Affiche la page d'administration
	@GetMapping(value = {"/administration"})
	public String AccueilAdministrationGET() {

		return "AccueilAdministration";
	}

/*
	@PostMapping(value = {"/administration"})
	public String AccueilAdministrationPOST() {
		return "AccueilAdministration";
	}
*/

//Administration : Affiche la page de création d'un produit
	@GetMapping(value = {"/administration/creerproduit"})
	public String ajoutProduit(Model model) {
		//model.addAttribute("produit_new", new Produit());
		//List<Produit> produits = produitService.findAll();
		//model.addAttribute("produits", produits);
		List <Categorie> categories = categorieService.findAll();
		model.addAttribute("categories", categories);
		return "CreerProduit";
	}

	//Administration : Affiche la page de recherche d'un produit
	@RequestMapping(path = "/administration/rechercherproduit", method = RequestMethod.GET)
	public String RechercherProduit(Model model) {
		List<Produit> produits = produitService.findAll();
		model.addAttribute("produits", produits);
		model.addAttribute("produit_new", new Produit());
		model.addAttribute("produitService",produitService);

		return "RechercherProduit";
	}

	//Administration : Affiche les résultats de recherche d'un produit
	@RequestMapping(path = "/administration/rechercherproduit", method = RequestMethod.POST)
	public String trouverProduit(Model model, @ModelAttribute("libelle") String libelle, @ModelAttribute("id") String id) {
		model.addAttribute("produit_new", new Produit());
		model.addAttribute("produitService", produitService);

		try {
			Long productId = null;
			if (id != null && !id.isEmpty()) {
				productId = Long.parseLong(id);
			}

			List<Produit> produitsTrouves = new ArrayList<>();
			if (productId == null && libelle.isEmpty()) {
				return "RechercherProduit";
			} else if (productId != null && libelle.isEmpty()) {
				Produit produitTrouve = produitService.getById(productId);
LOGGER.debug("produitTrouve : "+ produitTrouve.toString() );
				if (produitTrouve != null) {
					produitsTrouves.add(produitTrouve);
					model.addAttribute("produitsTrouves", produitsTrouves);
				}
				else return "RechercherProduit";

			} else if (productId==null && !libelle.isEmpty()) {
				produitsTrouves = produitService.getByLibelle(libelle);
				LOGGER.debug("produitsTrouves : "+ produitsTrouves.toString() );
				model.addAttribute("produitsTrouves", produitsTrouves);
			} else {
				return "RechercherProduit";
			}

			List<Categorie> categories = categorieService.findAll();
			model.addAttribute("categories", categories);
		} catch (Exception e) {
			return "RechercherProduit";
		}

		return "RechercherProduit";
	}

	//Administration : Affiche la fiche produit
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
//Administration : Modifie le produit
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

//Administration : Applique une promotion au produit
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
//Administration : Supprime le produit
	@RequestMapping(path = "/administration/produit/{id}/supprimer", method = RequestMethod.POST)
	public RedirectView SupprimerProduit(Model model, @PathVariable("id") Long id) {


		model.addAttribute("produitService", produitService);
		Produit produit = produitService.findById(id);
		model.addAttribute("produit", produit);

		produitService.deleteById(id);

		RedirectView redirectView = new RedirectView("/administration", true);
		return redirectView;
	}

	//Administration : Crée un produit
@PostMapping("/image/saveImageDetails")
public @ResponseBody ResponseEntity<?> creerProduit(@RequestParam("idcategorie") Long idcategorie,
													 @RequestParam("prixDeBase") double prixDeBase, @RequestParam("description") String description, @RequestParam("libelle") String libelle,
													 Model model, HttpServletRequest request
		,final @RequestParam("image") MultipartFile file
) {
	log.debug("entrée dans le controleur");

	try {

		String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
		log.info("uploadDirectory:: " + uploadDirectory);
		String fileName = file.getOriginalFilename();
		String filePath = Paths.get(uploadDirectory, fileName).toString();
		log.info("FileName: " + file.getOriginalFilename());
		if (fileName == null || fileName.contains("..")) {
			model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence " + fileName);
			return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
		}
		/*
		String[] libelles = libelle.split(",");
		String[] descriptions = description.split(",");
		*/

		try {
			File dir = new File(uploadDirectory);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			// Save the file locally
			//BufferedOutputStream stream = new BufferedOutputStream(Files.newOutputStream(new File(filePath).toPath()));
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
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
		log.info("image : " + produit.getImage().toString());
		produit.setPrixDeBase(prixDeBase);
		produit.setCategorie(categorieService.getById(idcategorie));
		//produit.setCategorie(categorieService.getSelonLibelle(categorie));
		produit.setPromotion(null);
		produit.setDescription(description);
		//produit.setDescription(descriptions[0]);
		produit.setDatecreation(new Date());
		produitService.save(produit);

		return new ResponseEntity<>("Product Saved With File - " +
				//fileName
				"0"
				, HttpStatus.OK);
	} catch (Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}

/*
	//Administration : Crée un produit
	@PostMapping("/image/saveImageDetailsmaj/{id}")
	public @ResponseBody ResponseEntity<?> creerProduit( @PathVariable("id") Long id,@RequestParam("datedebut")@DateTimeFormat(pattern = "yyyy-MM-dd") String datedebutStr,
														@RequestParam("datefin")@DateTimeFormat(pattern = "yyyy-MM-dd") String datefinStr,
														@RequestParam("remise")int remise,@RequestParam("idcategorie") Long idcategorie,
														@RequestParam("prixDeBase") double prixDeBase, @RequestParam("description") String description,
														@RequestParam("libelle") String libelle,
														Model model, HttpServletRequest request
			,final @RequestParam("image") MultipartFile file
	) {
		log.debug("entrée dans le controleur");

		try {

			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence " + fileName);
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
			}
		/*
		String[] libelles = libelle.split(",");
		String[] descriptions = description.split(",");


			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				// Save the file locally
				//BufferedOutputStream stream = new BufferedOutputStream(Files.newOutputStream(new File(filePath).toPath()));
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
			model.addAttribute("produitService", produitService);
			Produit produitMaj = produitService.getById(id);
			model.addAttribute("ProduitMaj", produitMaj);
			if (datedebutStr != null && datefinStr != null && remise != 0) {
				LocalDate datedebut = LocalDate.parse(datedebutStr);
				LocalDate datefin = LocalDate.parse(datefinStr);
				produitMaj.setPromotion(new Promotion(datedebut, datefin, remise));
			}
			else 		produitMaj.setPromotion(null);
			produitService.update(produitMaj);

	//		RedirectView redirectView = new RedirectView("/administration/produit/{id}", true);

			byte[] image = file.getBytes();

			//produit.setLibelle(libelles[0]);
			produitMaj.setLibelle(libelle);
			produitMaj.setImage(image);
			log.info("image : " + produitMaj.getImage().toString());
			produitMaj.setPrixDeBase(prixDeBase);
			produitMaj.setCategorie(categorieService.getById(idcategorie));
			//produit.setCategorie(categorieService.getSelonLibelle(categorie));

			produitMaj.setDescription(description);
			//produit.setDescription(descriptions[0]);
			//produit.setDatecreation(new Date());
			produitService.save(produitMaj);

			return new ResponseEntity<>("Product Saved With File - " +
					//fileName
					"0"
					, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
*/
//Affiche l'image du produit
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


}

