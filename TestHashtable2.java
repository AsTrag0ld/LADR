import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import model.Route;
import model.Ville;

public class TestHashtable2 {
	private Hashtable tableCoord;
	private LinkedList<Route> lesRoutes;
	
	public TestHashtable2() {
		this.tableCoord = new Hashtable();
		this.lesRoutes = new LinkedList<>();
		this.lesRoutes.add(new Route(5, "bleu", new Ville("Bavay"), new Ville("Maubeuge")));
		this.lesRoutes.add(new Route(5, "bleu", new Ville("blbl"), new Ville("Maubeuge")));
		this.lesRoutes.add(new Route(5, "bleu", new Ville("Bavay"), new Ville("blblbl")));
		remplirTable();
	}

	private ArrayList<int[]> construireListeRoute(int[] coordonnees) {
        ArrayList<int[]> res = new ArrayList<>();
        for (int i = 0; i < coordonnees.length; i = i + 2) {
            int[] tmp = new int[2];
            tmp[0] = i;
            tmp[1] = i + 1;
            res.add(tmp);
        }
        return res;
    }

    private void remplirTable() {
        int[] coordonnees = {34,23,34,24,33,25,34,25,32,26,33,26,30,27,31,27,29,28,30,28,28,29,29,29,30,29,28,30,28,30};
        ArrayList<int[]> tmp = construireListeRoute(coordonnees);
        this.tableCoord.put(tmp, this.lesRoutes.get(0));
        
        int[] coordonnees2 = {34,23,34,24,33,25,34,25,32,26,33,26,30,27,31,27,29,28,30,28,28,29,29,29,30,29,28,30,28,30};
        ArrayList<int[]> tmp2 = construireListeRoute(coordonnees2);
        this.tableCoord.put(tmp, this.lesRoutes.get(1));
        
        int[] coordonnees3 = {34,23,34,24,33,25,34,25,32,26,33,26,30,27,31,27,29,28,30,28,28,29,29,29,30,29,28,30,28,30};
        ArrayList<int[]> tmp3 = construireListeRoute(coordonnees3);
        this.tableCoord.put(tmp, this.lesRoutes.get(2));
    }

    Route rechercherRoute(float x, float y) {
        int[] tmp = new int[2];
        tmp[0] = (int) x;
        tmp[1] = (int) y;

        for (Enumeration<ArrayList<int[]>> e = tableCoord.keys(); e.hasMoreElements();) {
            System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEe : " + e.nextElement());
            if (e.nextElement().contains(tmp)) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> DANS LE IF <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                return (Route) tableCoord.get(e);
            }
        }
        return null;
    }
    
    
}

