package ladr.projet.application.lesaventuriersdurail.model;

/**
 * Created by 'Anthony' on 08/12/2016.
 */


public class Route {
    private boolean doubleRoute;
    private int taille;
    private boolean disponible;
    public Ville villeA;
    public Ville villeB;

    public Route() {

    }

    public boolean estDisponible() {
        return this.disponible;
    }

}

