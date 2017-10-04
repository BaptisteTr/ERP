package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class testProduction {

	Process_metier decoupe;
	Process_metier forgeage;
	Process_metier traitement_de_surface;
	Process_metier emballage;
	
	
	@Before
	public void setUp() throws Exception {
		decoupe = new Process_metier(2); //Nombre d'heures nécessaires
		forgeage = new Process_metier(6);
		traitement_de_surface = new Process_metier(2);
		emballage = new Process_metier(2);
	}

	@Test
	public void testProduction() {
		Date dateEcheance = new Date("15/11/1969");
		
		emballage.produire(dateEcheance);
		
		assertTrue(dateEcheance.compareTo(emballage.getDateLancement()) < 0); // Trouver comment comparer correctement

		traitement_de_surface.produire(emballage.getDateLancement());
		
		assertTrue(emballage.getDateLancement().compareTo(traitement_de_surface.getDateLancement()) < 0);
		
		forgeage.produire(traitement_de_surface.getDateLancement());
		
		assertTrue(traitement_de_surface.getDateLancement().compareTo(forgeage.getDateLancement()) < 0);
		
		decoupe.produire(forgeage.getDateLancement());
		
		assertTrue(forgeage.getDateLancement().compareTo(decoupe.getDateLancement()));
	}

}
