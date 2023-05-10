package com.example.demoA.Produit;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.demoA.Categorie.CategorieRepository;
import com.example.demoA.Promotion.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduitService {
	private final CategorieRepository categorieRepository;
	private final PromotionRepository promotionRepository;

	private final ProduitRepository produitRepository;
	@Autowired
	public ProduitService(CategorieRepository categorieRepository, PromotionRepository promotionRepository, ProduitRepository produitRepository) {
		this.categorieRepository = categorieRepository;
		this.promotionRepository = promotionRepository;
		this.produitRepository = produitRepository;
	}


	public void saveImage(Produit produit) {
		produitRepository.save(produit);
	}

	public List<Produit> getAllActiveImages() {
		return produitRepository.findAll();
	}

	public Optional<Produit> getImageById(Long id) {
		return produitRepository.findById(id);
	}

    public List<Produit> findAll() {return produitRepository.findAll();
    }

    public Produit findById(Long i) {
		return produitRepository.getSelonId(i);
    }
	public double calculerPrixEnCours(Produit produit) {
		if (produit.getPromotion() != null) {
			if ((produit.getPromotion().getDatedebut().isBefore(LocalDate.now()) || produit.getPromotion().getDatedebut().isEqual(LocalDate.now())) &&
					(produit.getPromotion().getDatefin().isAfter(LocalDate.now())|| produit.getPromotion().getDatefin().isEqual(LocalDate.now()))) {
					return produit.getPrixDeBase() - produit.getPrixDeBase() * produit.getPromotion().getRemise() / 100;
			}else return produit.getPrixDeBase();
		}else return produit.getPrixDeBase();
	}

    public List<Produit> getByLibelle(String libelle) {
		return produitRepository.getSelonLibelle(libelle);
    }

	public void update(Produit updatedProduit) {
		produitRepository.save(updatedProduit);
	}

	public void deleteById(Long id) {produitRepository.deleteById(id);
	}
	public void save(Produit produit) {produitRepository.save(produit);
	}

    public Produit getById(Long id) {return produitRepository.getById(id);
    }
}

