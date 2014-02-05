package graphique;



import java.util.Random;

/**
 * I N F 1 1 2 0 - TP2 hiver 2009
 *
 * Classe fournie pour le Tp2
 *
 * ==============================> Placez cette classe dans votre projet
 *
 * @author Louise Laforest
 */

public class JeuArithmetique {
    
	private static final int FACILE = 1;
	private static final int MOYEN = 3;
	private static final int DIFFICILE = 4;
	private static final int EXTREME = 5;
	private static int degreDifficulte = FACILE;
	private static int germe = 25;
	private static Random generateur = new Random(germe);
    
	private static final int OP_MIN_FACILE = 1;
	private static final int OP_MAX_FACILE = 20;
	private static final int OP_MIN_MOYEN = 1;
	private static final int OP_MAX_MOYEN = 50;
	private static final int OP_MIN_DIFFICILE = -25;
	private static final int OP_MAX_DIFFICILE = 25;
	private static final int OP_MIN_EXTREME = 2;
	private static final int OP_MAX_EXTREME = 10;
    
	private static String operations = "+-*/%^";
   
	private static final int caree = 2;
	private static final int cube = 3;
    
	
	public static int getFacile() {
		return FACILE;
	}
    
	public static int getMoyen() {
		return MOYEN;
	}
    
	public static int getDifficile() {
		return DIFFICILE;
	}
    
	public static int getExtreme() {
		return EXTREME;
	}
	
    
	/**
	 * Permet de definir le degre de difficulte. FACILE : operations + et -,
	 * operandes entre 1 et 20 inclusivement MOYEN : operations +, -, *, et /,
	 * operandes entre 1 et 50 inclusivement DIFFICILE : operations +, -, *, /
	 * et %, operandes entre -25 et 25 inclusivement (sauf 0)
	 *
	 * @param degre
	 *            degre de difficulte. Si autre que FACILE, MOYEN ou DIFFICILE,
	 *            FACILE sera choisi.
	 */
	public static void choisirDegreDifficulte(int degre) {
		if (degre == MOYEN || degre == DIFFICILE) {
			degreDifficulte = degre;
		} else if (degre == FACILE) {
			degreDifficulte = FACILE;
		} else {
			degreDifficulte = EXTREME;
		}
	}
    
	
    
	/**
	 * Retourne une operation choisie au hasard en fonction du degre de
	 * difficulte choisi
	 *
	 * @return le caractere correspondant a l'operation
	 */
	public static char operationAuHasard() {
		return operationAuHasardSelonLevel(0);
	}
	
	/**
	 * Retourne une operation excluant "exposant" choisie au hasard en fonction du degre de
	 * difficulte choisi
	 *
	 * @return le caractere correspondant a l'operation
	 */
	public static char operationAuHasardSansExposant() {
		return operationAuHasardSelonLevel(-1);
	}
    
	/**
	 * Retourne une operation choisie au hasard en fonction du degre de
	 * difficulte choisi
	 * @param int indice, entier negatif ou 0.
	 * @return le caractere correspondant a l'operation
	 */
	public static char operationAuHasardSelonLevel(int indice) {
		
		return operations.charAt(nombreAleatoire(0, degreDifficulte + indice));
	}
	
	/**
	 * Retourne un nombre choisi au hasard en fonction du degre de difficulte
	 * choisi
	 *
	 * @return le nombre choisi au hasard
	 */
	public static int operandeAuHasard() {
		int reponse = 0;
		if (degreDifficulte == FACILE) {
			reponse = nombreAleatoire(OP_MIN_FACILE, OP_MAX_FACILE);
		} else if (degreDifficulte == MOYEN) {
			reponse = nombreAleatoire(OP_MIN_MOYEN, OP_MAX_MOYEN);
		} else if (degreDifficulte == DIFFICILE){
			do {
				reponse = nombreAleatoire(OP_MIN_DIFFICILE, OP_MAX_DIFFICILE);
			} while (reponse == 0);
		}else {
            
            do {
                reponse = nombreAleatoire(OP_MIN_DIFFICILE, OP_MAX_DIFFICILE);
            } while (reponse == 0);
            
		}
        
		return reponse;
	}
	
	
	/**
	 * Retourne un nombre choisi au hasard entre 2 et 3
	 *
	 * @return le nombre choisi au hasard
	 */
	public static int operandeCareeCubeAuHasard() {
		return nombreAleatoire(caree, cube);
	}
	
	/**
	 * Retourne un nombre choisi au hasard entre 2 et 10
	 *
	 * @return le nombre choisi au hasard
	 */
	public static int operandeExposantAuHasard() {
		return nombreAleatoire(OP_MIN_EXTREME, OP_MAX_EXTREME);
	}
	
    
	/**
	 * Retourne un nombre choisi au hasard entre min et max
	 *
	 * @return le nombre choisi au hasard
	 */
	private static int nombreAleatoire(int min, int max) {
		int reponse;
		if (min > max) {
			reponse = 0;
		} else {
            
			reponse = (int) Math.floor((max - min + 1)
                                       * generateur.nextDouble())
            + min;
		}
		return reponse;
	} 
    
} 
