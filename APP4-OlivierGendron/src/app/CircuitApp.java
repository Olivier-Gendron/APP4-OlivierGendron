package app;

import electronique.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CircuitApp {
    private static final char fSep = File.separatorChar;
    private static final String pathIn = System.getProperty("user.dir") + fSep + "APP4-OlivierGendron" + fSep + "src" + fSep + "donnees" + fSep + "fichiers_json";
    private static String[] fichiersDisponibles;


    public CircuitApp() {

    }

    static void main(String[] args) {
        double valeurResistance;
        do {
            afficherFichiers(creationListeFichiers());
            String chemin = construireChemin(demanderNumero());
            CircuitBuilder circuitBuilder = new CircuitBuilder();
            try {
                Composant c = circuitBuilder.construireCircuit(chemin);
                valeurResistance = c.calculerResistance();
                System.out.println("\nRésistance équivalente calculée : " + valeurResistance + " Ω\n");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (boucleUtilisation() == 'R');
        System.out.println("------Le programme a bien été arrêté------");


    }


    private static String[] creationListeFichiers() {
        File dossier = new File(pathIn);
        if (!dossier.exists()) {
            System.out.println("Erreur: Le chemin n'existe pas.\n");
            return null;
        } else if (!dossier.isDirectory()) {
            System.out.println("Le chemin n'est pas un dossier.\n");
            return null;
        }
        fichiersDisponibles = dossier.list();
        return fichiersDisponibles;
    }

    private static void afficherFichiers(String[] fichiersDisponibles) {
        System.out.println("\n------Liste des fichiers disponibles------");
        if (fichiersDisponibles == null || fichiersDisponibles.length == 0) {
            System.out.println("Aucun fichier disponible\n");
        } else {
            for (int i = 0; i < fichiersDisponibles.length; i++) {
                System.out.println("\t[" + i + "] " + fichiersDisponibles[i]);
            }
        }

        System.out.println("-----------------------------------------");
    }

    private static int demanderNumero() {
        int numeroFichier;
        do {
            System.out.print("Entrez le numéro correspondant au dossier que vous voulez utiliser: ");
            try {
                Scanner sc = new Scanner(System.in);
                numeroFichier = sc.nextInt();
                if (numeroFichier >= fichiersDisponibles.length || numeroFichier < 0) {
                    System.out.println("ERREUR: Veuillez entrer une valeur entre 0 et " + (fichiersDisponibles.length - 1 + "\n"));
                }
            } catch (InputMismatchException e) {
                System.out.println("ERREUR: Veuillez entrer une valeur numérique valide.\n");
                numeroFichier = -1;
            }

        } while (numeroFichier < 0 || numeroFichier >= fichiersDisponibles.length);

        return numeroFichier;


    }

    private static String construireChemin(int numero) {
        return pathIn + fSep + fichiersDisponibles[numero];
    }

    private static char boucleUtilisation() {
        Scanner sc = new Scanner(System.in);
        String reponse;
        char repFinale;
        do {
            System.out.println("\t[R] Tester un autre fichier\n\t[Q] Quitter\n");
            try {
                System.out.print("Entrez la lettre correspondant à l'option que vous désirez: ");
                reponse = sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("ERREUR: Veuillez entrer un caractère valide.\n");
                reponse = "Erreur";
            }
            if (reponse.length() != 1){
                System.out.println("ERREUR: Veuillez entrer un seul caractère.\n");
                repFinale = 'E';
            } else{
              repFinale = reponse.charAt(0);
                repFinale = Character.toUpperCase(repFinale);
                if (repFinale != 'R' && repFinale != 'Q') {
                    System.out.println("ERREUR: Veuillez entrer un caractère valide.\n");
            }

            }
        } while (repFinale != 'R' && repFinale != 'Q');

        return repFinale;

    }
}






