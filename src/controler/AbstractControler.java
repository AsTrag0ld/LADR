package controler;

import model.AbstractModel;

public abstract class AbstractControler {
	
	public AbstractControler(AbstractModel a){
		
	}
	
	
	public void reset(){
		
	}
	
	//vérifie les données et informe le modèle
	abstract void control();
}
