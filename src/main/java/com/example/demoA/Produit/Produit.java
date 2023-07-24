package com.example.demoA.Produit;

import com.example.demoA.Categorie.Categorie;
import com.example.demoA.Promotion.Promotion;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "produit")
public class Produit {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "libelle", nullable = false)
	private String libelle;
	
	@Column(name = "description", nullable = false)
	private String description;	
	
	@Column(name = "prix",nullable = false, precision = 10, scale = 2)
    private double prixDeBase;

	@ManyToOne
	@JoinColumn(name = "categorie_idcategorie")
	private Categorie categorie;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "promotion_idpromotion")
	private Promotion promotion;

	@Lob
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datecreation", nullable = false)
    private Date datecreation;

	public Produit() {

	}

	public Produit(String libelle, String description, double prixDeBase, Categorie categorie, Promotion promotion, byte[] image, Date datecreation) {
		this.libelle = libelle;
		this.description = description;
		this.prixDeBase = prixDeBase;
		this.categorie = categorie;
		this.promotion = promotion;
		this.image = image;
		this.datecreation = datecreation;
	}

	// Vérifie ou met à jour une promotion associée à un produit après son chargement à partir de la base de données
	@PostLoad
	public void updatePromotion() {
		// Si la promotion n'est plus valide, elle est retirée du produit en la définissant à null
			if(promotion != null ) {
			if (promotion.getDatedebut().isAfter(LocalDate.now()) || promotion.getDatefin().isBefore(LocalDate.now()) || promotion.getRemise() == 0) {
				this.promotion=null;
				}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

		public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrixDeBase() {
		return prixDeBase;
	}

	public void setPrixDeBase(double prixDeBase) {
		this.prixDeBase = prixDeBase;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", nom=" + libelle + ", description=" + description + ", prix=" + prixDeBase + ", image="
				+ Arrays.toString(image) + ", createDate=" + datecreation + "]";
	}
   
}


