public class Poligonale extends StrumentoDiDisegno {
	
	//ATTRIBUTI
	/** Primo evento da registrare*/
	private MouseClick primoClick = null;
	
	/** Secondo evento da registrare */
	private MouseClick secondoClick = null;
	
	/** true se si e' effettuato un primoClick */
	private boolean start = false;
	
	/**L'ascissa del punto iniziale della poligonale*/
	private int x0=-1; //inizializzazione con un valore impossibile di ascissa
	
	/**L'ordinata del punto iniziale della poligonale**/
	private int y0=-1;//inizializzazione con un valore impossibile di ascissa
	
	//COSTRUTTORI
  public Poligonale(Canvas canvas) {
	  super(canvas);
  }
  
  @Override
  public void ricevi(EventoDiMouse e) {
	  if(e instanceof MouseClick) {
		  if(!start) {	//finché non faccio un click semplice sinisstro entro in questo if  
			  primoClick= (MouseClick) e;
			  
			  if(!primoClick.rightClick() && !primoClick.doubleClick()) {
				  start=true;
				  x0 = primoClick.posx();
				  y0 = primoClick.posy();
			  }
	  }else {
			  secondoClick = (MouseClick) e;
			  //traccio un segmento tra primoClick e secondoClick e ogni volta copio l'informazione di secondoClick in primoClick
			  if(!secondoClick.rightClick() && !secondoClick.doubleClick()) {
				  canvas.modifica(primoClick.posx(), primoClick.posy(), secondoClick.posx(), secondoClick.posy(), super.getTratto());
				  primoClick=secondoClick;
				  }
			  //termino la poligonale corrente unendo secondoClick con il punto iniziale di coordinate(x0,y0)
			  else if(!secondoClick.rightClick() && secondoClick.doubleClick()) { 
				  canvas.modifica(x0, y0, secondoClick.posx(), secondoClick.posy(), super.getTratto());
				  start = false;
			  }
			  //resetto lo stato dello strumento
			  else if(secondoClick.rightClick()) {
			  start=false;
			  primoClick = null;
			  secondoClick = null;
			  x0=-1;
			  y0=-1;
			  }
	  	}
	  }
  }


@Override
public boolean equals(Object altro) {
  if (altro instanceof Poligonale) {
    return true;
  } else {
    return false;
  }
} // hashCode overridden in classe Strumento

@Override
public String toString() {
	return "Poligonale" + super.toString();
	}

}