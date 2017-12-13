package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Engine {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		
		double prixAcierCourant;
		int moisCourant;
		double prixProd = 6000;
		double questionQuatre = 1.1;
		double resteAConsommer = 0;
		
		int moisDateDepart = new Date("10/01/1969").getMonth();
		int anneeDateDepart = new Date ("10/01/1969").getYear();
		
		// On crée notre queue de calcul de prix de commande et on ajoute le coût des deux bobines de départ
		Queue<Double[]> commandesPassees = new LinkedList<Double[]>();
		commandesPassees.add(new Double[] {1.0, 10000.0});
		commandesPassees.add(new Double[] {1.0, 10000.0});
		
		//On crée une liste pour stocker les commandes fournisseurs à passer
		List<String> datesCommandesFournisseurs = new ArrayList<String>();
		
		//On crée une liste pour stocker les livraisons clients
		List<String> datesEtCoutLivraisonClient = new ArrayList<String>();
		
		//Ajout des dates de livraison � la liste pour les livraisons de 15000 unit�es
		//A mettre dans un objet
		List<Calendar> lesLivraisons15000 = new ArrayList<Calendar>();
		Calendar dateLivraison = Calendar.getInstance();
		dateLivraison.setTime(new Date("11/15/1969"));
		lesLivraisons15000.add((Calendar) dateLivraison.clone());
		dateLivraison.setTime(new Date("12/01/1969"));
		lesLivraisons15000.add((Calendar) dateLivraison.clone());
		dateLivraison.setTime(new Date("12/15/1969"));
		lesLivraisons15000.add((Calendar) dateLivraison.clone());
		dateLivraison.setTime(new Date("01/01/1970"));
		lesLivraisons15000.add((Calendar) dateLivraison.clone());
		dateLivraison.setTime(new Date("01/15/1970"));
		lesLivraisons15000.add((Calendar) dateLivraison.clone());
		dateLivraison.setTime(new Date("02/01/1970"));
		lesLivraisons15000.add((Calendar) dateLivraison.clone());
		//Ajout des dates de livraison � la liste pour les livraisons de 5000 unit�es
		//A mettre dans un objet
		List<Calendar> lesLivraisons5000 = new ArrayList<Calendar>();
		dateLivraison.setTime(new Date("02/01/1970"));
		lesLivraisons5000.add((Calendar) dateLivraison.clone());
		dateLivraison.setTime(new Date("02/05/1970"));
		lesLivraisons5000.add((Calendar) dateLivraison.clone());
		dateLivraison.setTime(new Date("02/10/1970"));
		lesLivraisons5000.add((Calendar) dateLivraison.clone());
		dateLivraison.setTime(new Date("02/15/1970"));
		lesLivraisons5000.add((Calendar) dateLivraison.clone());
		dateLivraison.setTime(new Date("02/20/1970"));
		lesLivraisons5000.add((Calendar) dateLivraison.clone());
		dateLivraison.setTime(new Date("02/25/1970"));
		lesLivraisons5000.add((Calendar) dateLivraison.clone());

		//Creation de la date de d�part, creation de 2 dates suppl�mentaires
		//pour le jour 1 et 2 car le temps de production de d�part est de 12h (temps pour 1000 unit�es)
		//puis 6h car les machines fonctionnent en parall�les
		Calendar dateCourante = Calendar.getInstance();
		dateCourante.setTime(new Date("10/01/1969"));
		Calendar jour1 = Calendar.getInstance();
		jour1.setTime(new Date("10/01/1969"));
		Calendar jour2 = Calendar.getInstance();
		jour2.setTime(new Date("10/02/1969"));
		int production = 0;
		int tpsProduction = 0;
		while (true) {
			//Verification si la date courante est une date de livraison pour les dates � 15000 unit�es
			for (int i = 0; i < lesLivraisons5000.size(); i++) {
				// System.out.println(dateCourante.compareTo(lesLivraisons5000.get(i)));
				if (dateCourante.compareTo(lesLivraisons5000.get(i)) == 0) {
					production -= 5000*questionQuatre;
					resteAConsommer = 5*questionQuatre;
					
					// Comment gérer la bobine à moitié consommée
					while(resteAConsommer > 0){

						if(commandesPassees.peek()[0] < resteAConsommer){
							resteAConsommer -= commandesPassees.peek()[0];
							prixProd += commandesPassees.poll()[1];
						} else {
							commandesPassees.peek()[0] -= resteAConsommer;
							prixProd += resteAConsommer*commandesPassees.peek()[1];
							
							resteAConsommer = 0;
						}
						
						if(!commandesPassees.isEmpty() && commandesPassees.peek()[0] == 0){
							commandesPassees.poll();
						} else if (commandesPassees.isEmpty()){
							resteAConsommer = 0;
							System.out.println("Impossible de respecter la commande : pas assez de capacité de production.");
						}
					}
					
					prixProd += 5*questionQuatre*6000;
					
					datesEtCoutLivraisonClient.add(dateCourante.getTime().getDate()+"/"+(dateCourante.getTime().getMonth()+1)+"/"+dateCourante.getTime().getYear()+"  \t"+5*questionQuatre*1000+" boulons\tProduction : "+prixProd+"€\tVente : "+prixProd*1.7+"€");
					System.out.println("Livraison d'une commande de "+5*questionQuatre*1000+" boulons au "+dateCourante.getTime()+", coût de prod : "+ prixProd +"€ prix de vente "+ prixProd*1.7+"€");

					prixProd = 0;
				}
			}
			//Verification si la date courante est une date de livraison pour les dates � 5000 unit�es
			//Une fois dans un objet, les 2 boucles seront mutialis�es
			for (int i = 0; i < lesLivraisons15000.size(); i++) {

				if (dateCourante.compareTo(lesLivraisons15000.get(i)) == 0){
					production -= 15000*questionQuatre;
					resteAConsommer = 15*questionQuatre;
					
					// Comment gérer la bobine à moitié consommée
					while(resteAConsommer > 0){

						if(commandesPassees.peek()[0] < resteAConsommer){
							resteAConsommer -= commandesPassees.peek()[0];
							prixProd += commandesPassees.poll()[1];
						} else {
							commandesPassees.peek()[0] -= resteAConsommer;
							prixProd += resteAConsommer*commandesPassees.peek()[1];
							
							resteAConsommer = 0;
						}	
						if(!commandesPassees.isEmpty() && commandesPassees.peek()[0] <= 0){
							commandesPassees.poll();
						} else if (commandesPassees.isEmpty()){
							resteAConsommer = 0;
							System.out.println("Impossible de respecter la commande : pas assez de capacité de production.");
						}
					}
					
					
					prixProd += 15*questionQuatre*6000;
					
					datesEtCoutLivraisonClient.add(dateCourante.getTime().getDate()+"/"+(dateCourante.getTime().getMonth()+1)+"/"+dateCourante.getTime().getYear()+"  \t"+15*questionQuatre*1000+" boulons\tProduction : "+prixProd+"€\tVente : "+prixProd*1.7+"€");
					System.out.println("Livraison d'une commande de 15,000 boulons au "+dateCourante.getTime()+", coût de prod : "+ prixProd +"€ prix de vente "+ prixProd*1.7+"€");

					prixProd = 0;
				}
			}
			//Verification si la date courante est bien un jour ouvr� de la semaine (jour travaill� pour la production)
			if (dateCourante.get(Calendar.DAY_OF_WEEK) != 1 && dateCourante.get(Calendar.DAY_OF_WEEK) != 7) {
				//On ajoute le temps de production de la journ�e
				tpsProduction += 7;
				//On verifie si on se trouve dans le premier ou deuxi�me jour de prodution(depuis le d�part de la prod)
				//car la premi�re production met 12h � sortir les premi�res pi�ces
				if (dateCourante.compareTo(jour1) == 0 || dateCourante.compareTo(jour2) == 0) {
					if (tpsProduction > 12) {
						//AJout des 1000 unit�es si la chaines de production � termin� (12h ici)
						production += 1000;
						//On retire les 12h pour avoir un temps de production exact pour la suite
						tpsProduction -= 12;
						Calendar dateCommande = Calendar.getInstance();
						dateCommande = (Calendar) dateCourante.clone();
						dateCommande.add(Calendar.DATE, -10);
						Date jour = dateCommande.getTime();
						
						//Calcul du prix de la commande
						if(dateCommande.YEAR > anneeDateDepart){
							moisCourant = dateCommande.MONTH + 12;
						} else {
							moisCourant = dateCommande.MONTH;
						}
						prixAcierCourant = (moisCourant-moisDateDepart) * 0.02 * 10000;
						
						commandesPassees.add(new Double[] {1.0,prixAcierCourant});
						
						
						datesCommandesFournisseurs.add(jour.getDate()+"/"+(jour.getMonth()+1)+"/"+jour.getYear());
						//Si la production � sorti 1000 unit�es, 1 bobine � �t� consomm� il faut donc en recommander 1
						//On affiche la date � laquelle elle doit etre command� pour arriver a temps pour la continuit� de la production
						System.out.println("Commande de bobine � passer le :" + jour);
					}
				} else {
					while (tpsProduction > 6) {
						//AJout des 1000 unit�es si la chaines de production � termin� (6 ici)
						production += 1000;
						//On retire les 6h pour avoir un temps de production exact pour la suite
						tpsProduction -= 6;
						Calendar dateCommande = Calendar.getInstance();
						dateCommande = (Calendar) dateCourante.clone();
						dateCommande.add(Calendar.DATE, -10);
						Date jour = dateCommande.getTime();
						
						//Calcul du prix de la commande
						if(jour.getYear() > anneeDateDepart){
							moisCourant = jour.getMonth() + 14;
						} else {
							moisCourant = jour.getMonth()+2;
						}
						//System.out.println(moisCourant-moisDateDepart);
						
						prixAcierCourant = 10000 + (moisCourant-moisDateDepart) * 0.02 * 10000;
						
						commandesPassees.add(new Double[] {1.0, prixAcierCourant});
						datesCommandesFournisseurs.add(jour.getDate()+"/"+(jour.getMonth()+1)+"/"+jour.getYear());
						//Si la production � sorti 1000 unit�es, 1 bobine � �t� consomm� il faut donc en recommander 1
						//On affiche la date � laquelle elle doit etre command� pour arriver a temps pour la continuit� de la production
						System.out.println("Commande de bobine � passer le :" + jour);
					}
				}
				Date jour = dateCourante.getTime();
				//On affiche la date courante et le nombre d'unit�es en stock
				System.out.println("jour : " + jour + " production : " + production);
				Thread.sleep(5);
			}
			//on continu vers le prochain jour
			dateCourante.add(Calendar.DATE, 1);
			
			//On arrete la simulation le 27/02 car il n'est pas utile d'aller plus loin
			Calendar stop = Calendar.getInstance();
			stop.setTime(new Date("02/27/1970"));
			if (dateCourante.compareTo(stop) == 0)
				break;
		}
		
		System.out.println("\n\nDates des commandes de bobines à passer :\n\n");
		
		for(Iterator it = datesCommandesFournisseurs.iterator(); it.hasNext();){
			System.out.println(it.next());
		}
		
		System.out.println("\n\nDates, coûts et prix des commandes clients à réaliser :\n\n");
		
		for(Iterator it = datesEtCoutLivraisonClient.iterator(); it.hasNext();){
			System.out.println(it.next());
		}
	}

}
