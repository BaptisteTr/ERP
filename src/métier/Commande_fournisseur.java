package métier;

import java.util.Date;

public class Commande_fournisseur {

	double prix;
	int delai_livraison;
	Ressource_de_production item;
	
	public Commande_fournisseur(double prix, Ressource_de_production item, int delai_livraison){
		this.prix = prix;
		this.item = item;
		this.delai_livraison = delai_livraison;
	}
	
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getDelai_livraison() {
		return delai_livraison;
	}
	public void setDelai_livraison(int delai_livraison) {
		this.delai_livraison = delai_livraison;
	}
	public Ressource_de_production getItem() {
		return item;
	}
	public void setItem(Ressource_de_production item) {
		this.item = item;
	}
	
	
	public void passerCommande(){
		
	}
}
