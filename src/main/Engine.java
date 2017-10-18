package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Engine {

	public static void main(String[] args) throws InterruptedException {
		//Ajout des dates de livraison à la liste pour les livraisons de 15000 unitées
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
		//Ajout des dates de livraison à la liste pour les livraisons de 5000 unitées
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

		//Creation de la date de départ, creation de 2 dates supplémentaires
		//pour le jour 1 et 2 car le temps de production de départ est de 12h (temps pour 1000 unitées)
		//puis 6h car les machines fonctionnent en parallèles
		//(il devrait être possible de le faire plus joliement
		Calendar dateCourante = Calendar.getInstance();
		dateCourante.setTime(new Date("10/01/1969"));
		Calendar jour1 = Calendar.getInstance();
		jour1.setTime(new Date("10/01/1969"));
		Calendar jour2 = Calendar.getInstance();
		jour2.setTime(new Date("10/02/1969"));
		int production = 0;
		int tpsProduction = 0;
		while (true) {
			//Verification si la date courante est une date de livraison pour les dates à 15000 unitées
			for (int i = 0; i < lesLivraisons5000.size(); i++) {
				// System.out.println(dateCourante.compareTo(lesLivraisons5000.get(i)));
				if (dateCourante.compareTo(lesLivraisons5000.get(i)) == 0)
					production -= 5000;
			}
			//Verification si la date courante est une date de livraison pour les dates à 5000 unitées
			//Une fois dans un objet, les 2 boucles seront mutialisées
			for (int i = 0; i < lesLivraisons15000.size(); i++) {

				if (dateCourante.compareTo(lesLivraisons15000.get(i)) == 0)
					production -= 15000;
			}
			//Verification si la date courante est bien un jour ouvré de la semaine (jour travaillé pour la production)
			if (dateCourante.get(Calendar.DAY_OF_WEEK) != 1 && dateCourante.get(Calendar.DAY_OF_WEEK) != 7) {
				//On ajoute le temps de production de la journée
				tpsProduction += 7;
				//On verifie si on se trouve dans le premier ou deuxième jour de prodution(depuis le départ de la prod)
				//car la première production met 12h à sortir les premières pièces
				if (dateCourante.compareTo(jour1) == 0 || dateCourante.compareTo(jour2) == 0) {
					if (tpsProduction > 12) {
						//AJout des 1000 unitées si la chaines de production à terminé (12h ici)
						production += 1000;
						//On retire les 12h pour avoir un temps de production exact pour la suite
						tpsProduction -= 12;
						Calendar dateCommande = Calendar.getInstance();
						dateCommande = (Calendar) dateCourante.clone();
						dateCommande.add(Calendar.DATE, -10);
						Date jour = dateCommande.getTime();
						//Si la production à sorti 1000 unitées, 1 bobine à été consommé il faut donc en recommander 1
						//On affiche la date à laquelle elle doit etre commandé pour arriver a temps pour la continuité de la production
						System.out.println("Commande de bobine à passer le :" + jour);
					}
				} else {
					if (tpsProduction > 6) {
						//AJout des 1000 unitées si la chaines de production à terminé (6 ici)
						production += 1000;
						//On retire les 6h pour avoir un temps de production exact pour la suite
						tpsProduction -= 6;
						Calendar dateCommande = Calendar.getInstance();
						dateCommande = (Calendar) dateCourante.clone();
						dateCommande.add(Calendar.DATE, -10);
						Date jour = dateCommande.getTime();
						//Si la production à sorti 1000 unitées, 1 bobine à été consommé il faut donc en recommander 1
						//On affiche la date à laquelle elle doit etre commandé pour arriver a temps pour la continuité de la production
						System.out.println("Commande de bobine à passer le :" + jour);
						//on verifie si une deuxième bobine à été produite dans la même journée
						if (tpsProduction > 6) {
							production += 1000;
							tpsProduction -= 6;
							//Si la production à sorti 1000 unitées, 1 bobine à été consommé il faut donc en recommander 1
							//On affiche la date à laquelle elle doit etre commandé pour arriver a temps pour la continuité de la production
							System.out.println("Commande de bobine à passer le :" + jour);
						}
					}
				}
				Date jour = dateCourante.getTime();
				//On affiche la date courante et le nombre d'unitées en stock
				System.out.println("jour : " + jour + " production : " + production);
				Thread.sleep(100);
			}
			//on continu vers le prochain jour
			dateCourante.add(Calendar.DATE, 1);
			
			//On arrete la simulation le 27/02 car il n'est pas utile d'aller plus loin
			Calendar stop = Calendar.getInstance();
			stop.setTime(new Date("02/27/1970"));
			if (dateCourante.compareTo(stop) == 0)
				break;
		}
	}

}
