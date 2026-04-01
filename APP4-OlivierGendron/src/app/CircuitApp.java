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

    public static void main(String[] args) {
        String reponse;
        Scanner sc = new Scanner(System.in);
        do {
            afficherFichiers(creationListeFichiers());
           String chemin = construireChemin(demanderNumero());
           CircuitBuilder circuitBuilder = new CircuitBuilder();
          double valeurResistance = circuitBuilder.construireCircuit(chemin).calculerResistance();
            System.out.println("Résistance équivalente calculée : " + valeurResistance + "Ω");




        }



    }


    private static String[] creationListeFichiers() {
        File dossier = new File(pathIn);
        if (!dossier.exists()) {
            System.out.println("Erreur: Le chemin n'existe pas.");
        } else if (!dossier.isDirectory()) {
            System.out.println("Le chemin n'est pas un dossier.");
        }
        fichiersDisponibles = dossier.list();
        if (fichiersDisponibles == null) {
            System.out.println("Le dossier est vide.");
        }

        return fichiersDisponibles;
    }

    private static void afficherFichiers(String[] fichiersDisponibles) {
        System.out.println("---Liste des fichiers disponibles---");
        for (int i = 0; i <= fichiersDisponibles.length - 1; i++) {
            System.out.println("[" + i + "] " + fichiersDisponibles[i]);
        }
        System.out.println("-----------------------------------");
    }

    private static int demanderNumero() {
        int numeroFichier = -1;
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez le numéro correspondant au dossier que vous voulez utiliser: ");
        try {
            numeroFichier = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERREUR: Veuillez entrer une valeur numérique valide.");
            demanderNumero();
        }
        if (numeroFichier >= fichiersDisponibles.length || numeroFichier < 0) {
            System.out.println("ERREUR: Veuillez entrer une valeur entre 0 et " + (fichiersDisponibles.length - 1));
            demanderNumero();
        }
        return numeroFichier;


    }

    private static String construireChemin(int numero){
        return pathIn + fSep + fichiersDisponibles[numero];
    }
}


