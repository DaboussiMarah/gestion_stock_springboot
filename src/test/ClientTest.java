package test;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import entites.Produit;

public class ClientTest {

    public static void main(String[] args) {
        Client client = Client.create(new DefaultClientConfig());

        // Remplacez l'URL par celle de votre service web
        URI uri = UriBuilder.fromUri("http://localhost:8082/RestApi/").build();

        ClientResponse reponse = client.resource(uri).path("api").path("/produits").get(ClientResponse.class);

        // Afficher la réponse brute pour le débogage
        String bodyRepHttp = reponse.getEntity(String.class);
        System.out.println("Réponse brute du service web :");
        System.out.println(bodyRepHttp);

        ObjectMapper mapper = new ObjectMapper();

        try {
            // Vérifier si la réponse commence par '<', indiquant une page HTML (erreur)
            if (bodyRepHttp.trim().startsWith("<")) {
                System.err.println("Erreur : La réponse du service web est une page HTML.");
                return;
            }

            JsonNode produits = mapper.readTree(bodyRepHttp);
            JsonNode productsNode = produits.get("produit");

            // Mapper directement en un tableau d'objets Produit
            Produit[] lesProduits = mapper.readValue(productsNode, Produit[].class);

            for (Produit elt : lesProduits) {
                System.out.println(elt.getDescription());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
