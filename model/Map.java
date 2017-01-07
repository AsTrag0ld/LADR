package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Map extends Observable {
    private static Map instance = null;
    private LinkedList<Ville> villes;
    private LinkedList<Route> routes;

    private Map() {
    	this.villes = new LinkedList<Ville>();
    	this.routes = new LinkedList<Route>();
    }

    public static Map getInstance() {
    	if (null == instance) {
    		instance = new Map();
    	}
    	return instance;
    }

	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(LinkedList<Ville> villes) {
		this.villes = villes;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(LinkedList<Route> routes) {
		this.routes = routes;
	}

    
}
