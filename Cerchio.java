public class Cerchio extends StrumentoDiDisegno {
	
  //ATTRIBUTI
  /** Primo evento da registrare */
  private MouseClick primoClick = null;
  /** Secondo evento da registrare */
  private MouseClick secondoClick = null;
  /** true se &egrave; gi&agrave; avvenuto il primo click */
  boolean gotCenter = false;
  /** il carattere presente in precedenza nel centro */
  char precedente;
	
  //COSTRUTTORI
  public Cerchio(Canvas canvas) {
	  super(canvas);
  }


  @Override
  public void ricevi(EventoDiMouse e) {
	  
	  if (e instanceof MouseClick) {
		   
		  if (!gotCenter) { // primo click
			  
			  primoClick = (MouseClick) e;
			  
			  if ( !(primoClick.rightClick()) ) { // se click sinistro
			  precedente = canvas.car(primoClick.posx(), primoClick.posy());
			  canvas.modifica(primoClick.posx(), primoClick.posy(), '*'); // mette l'evidenziatore al centro
			  gotCenter = true;
			  } // se click destro, non fa nulla
			  
		  } else { // secondo click
			  
			  secondoClick = (MouseClick) e;
			  
			  if ( !(secondoClick.rightClick()) ) { // se click sinistro
				  
				  int deltaXSquared = (primoClick.posx()-secondoClick.posx());
				  deltaXSquared *= deltaXSquared;
				  int deltaYSquared = (primoClick.posy()-secondoClick.posy());
				  deltaYSquared *= deltaYSquared;
				  int distanza = (int) Math.round( Math.sqrt( (double) (deltaXSquared + deltaYSquared) ) );
				  
			  }
			  
			  // fase di cancellatura
			  // avviene anche se il secondo click è destro (come voluto)
			  canvas.modifica(primoClick.posx(), primoClick.posy(), precedente); // toglie l'evidenziatore
			  
			  primoClick = null; // fine
			  secondoClick = null;
			  gotCenter = false;
		  }
	  }
  }

  @Override
  public boolean equals(Object altro) {
    if (altro instanceof Cerchio) {
      return true;
    } else {
      return false;
    }
  } // hashCode overridden in classe Strumento
}
