package app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import electronique.CircuitParallele;
import electronique.CircuitSerie;
import electronique.Composant;
import electronique.Resistance;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CircuitBuilder {


    public CircuitBuilder() {

    }

    public Composant construireCircuit(String cheminFichier) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode donneesCircuit = mapper.readTree(new File(cheminFichier));
            return lireComposant(donneesCircuit);

        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        }

        return null;
    }

    private Composant lireComposant(JsonNode node) {
        String type = node.get("type").asText();
        if ("resistance".equals(type)) {
            return new Resistance(node.get("valeur").asInt());
        } else if ("serie".equals(type)) {
            List<Composant> composants = new ArrayList<>();
            for (JsonNode composantNode : node.get("composants")) {
                composants.add(lireComposant(composantNode));
            }
            return new CircuitSerie(composants);
        } else if ("parallele".equals(type)) {
            List<Composant> composants = new ArrayList<>();
            for (JsonNode composantNode : node.get("composants")) {
                composants.add(lireComposant(composantNode));
            }
            return new CircuitParallele(composants);

        }

        throw new IllegalArgumentException("Type de circuit non reconnu.");
    }
}