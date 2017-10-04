package tests;

import static org.junit.Assert.*;

import java.util.Date;

import métier.Commande_fournisseur;
import métier.Stock_item;

import org.junit.Before;
import org.junit.Test;

public class TestCommandeFournisseur {
	
	Stock_item stockBobine;
	double prixAcier;
	String nomFournisseur;
	int joursLivraison;
	
	@Before
	public void setup(){
		stockBobine = new Stock_item("bobine",0);
		prixAcier = 10000;
		nomFournisseur = "Roger bobines d'aciers";
		joursLivraison = 10;
	}

	@Test
	public void passerCommande() {
		
		Date dateEcheance = new Date("15/11/1969");
		
		Commande_fournisseur commandeBobine = new Commande_fournisseur(stockBobine, prixAcier, nomFournisseur, joursLivraison);
		
		commandeBobine.process(dateEcheance);
		
		commandeBobine.getDateLancementCommande();
		
		assertTrue(dateEcheance.compareTo(commandeBobine.getDateLancementCommande()) == -2); // Trouver comment comparer correctement

	}

}
