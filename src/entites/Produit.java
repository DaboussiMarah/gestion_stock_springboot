package entites;

public class Produit {

	private int id;
	private float prix;
	private String description;
	private Catégorie catégorie;
	

	public Produit(int id, String description, Catégorie catégorie) {
		super();
		this.id = id;
		this.description = description;
		this.catégorie = catégorie;
	}

	public Produit() {
		super();
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public float getPrix() {
		return prix;
	}


	public void setPrix(float prix) {
		this.prix = prix;
	}


	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Catégorie getCatégorie() {
		return catégorie;
	}


	public void setCatégorie(Catégorie catégorie) {
		this.catégorie = catégorie;
	}
	
	
	

}
