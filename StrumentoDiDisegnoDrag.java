public abstract class StrumentoDiDisegnoDrag extends StrumentoDiDisegno {

  //ATTRIBUTI
  /** Primo evento da registrare */
  protected DragStart pIniziale;
  /** Secondo evento da registrare */
  protected DragEnd pFinale;
  /** il carattere presente in precedenza nel punto iniziale */
  protected char precedente;

  //COSTRUTTORI
  public StrumentoDiDisegnoDrag(Canvas canvas) {
	  super(canvas);
  }

  //METODI
  @Override
  public void ricevi(EventoDiMouse e) {

    // caso DragStart
	    if (e instanceof DragStart) {
	    	this.pIniziale = (DragStart) e;
	    	if (!(this.pIniziale.right())) {
				this.canvas.addToHistory();
				precedente = canvas.car(pIniziale.posx(), pIniziale.posy()); // registra il carattere presente nel punto di partenza
				canvas.modifica(pIniziale.posx(), pIniziale.posy(), super.getTratto()); // evidenzia il punto di partenza
	    	}
	    }

    // caso DragEnd
    if (e instanceof DragEnd) {
      if ((this.pIniziale != null) && !(this.pIniziale.right())) {
    	  this.pFinale = (DragEnd) e;
    	  disegna();
        // &egrave; ben posto: DragEnd segue sempre un DragStart, dunque pIniziale non � null
      }

      // torna allo stato neutro senza togliere il carattere presente nel punto di partenza
      // (cosa che farebbe reset())
      this.pIniziale = null;
      this.pFinale = null;
    }
  }

  protected abstract void disegna();

  @Override
  protected void reset() {
	  if (pIniziale != null) {
		  canvas.modifica(pIniziale.posx(), pIniziale.posy(), precedente); // rimuove l'evidenziatura
	  }
      this.pIniziale = null;
      this.pFinale = null;
  }

}
