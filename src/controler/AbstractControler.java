package controler;

import model.AbstractModel;

public abstract class AbstractControler {
	
	public AbstractControler(AbstractModel a){
		
	}
	
	
	public void reset(){
		
	}
	
	//v�rifie les donn�es et informe le mod�le
	abstract void control();
}
