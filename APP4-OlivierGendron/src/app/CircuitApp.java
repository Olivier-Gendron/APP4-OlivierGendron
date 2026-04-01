package app;

import electronique.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CircuitApp {
    private static final char fSep = File.separatorChar;
    private static final String pathIn = System.getProperty("user.dir") + fSep + "APP4-OlivierGendron" + fSep + "src" + fSep + "donnees" + fSep + "fichiers_json";

    private List<String> fichiersDisponibles = new ArrayList<>();

    public CircuitApp() {

    }

    static void main() {
        System.out.println();
    }

    private void creationListeFichiers() {

        try {
            Files.list(Path.of(pathIn));
        } catch (IOException e) {
            System.err.println("Package vide: Aucun fichier disponible");
        }


    }
}
