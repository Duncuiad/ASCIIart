
public abstract class StrumentoDiDisegnoCentro extends StrumentoDiDisegno {
	
	  //ATTRIBUTI
	  /** Primo evento da registrare */
	  protected MouseClick centro;
	  /** Secondo evento da registrare */
	  protected MouseClick altro;
	  /** Carattere presente in precedenza nel centro */

	public StrumentoDiDisegnoCentro(Canvas canvas) {
		super(canvas);
	}

	@Override
	public void ricevi(EventoDiMouse e) {

		  if (e instanceof MouseClick) {

			  if (centro == null) { // primo click

				  centro = (MouseClick) e;

				  if ( !(centro.rightClick()) ) { // se click sinistro
					  canvas.addToHistory();
					  //precedente = canvas.car(centro.posx(), centro.posy());
					  canvas.modifica(centro.posx(), centro.posy(), '*'); // mette l'evidenziatore al centro
				  } else {
					  reset(); // se click destro, non fa nulla e torna allo stato iniziale
				  }

			  } else { // secondo click

				  altro = (MouseClick) e;

				  if ( !(altro.rightClick()) ) { // se secondo click sinistro

					  canvas.undo(1); // toglie l'evidenziatore
					  canvas.addToHistory(); 
					  disegna();

				  } 			  
				  
				  // fase di cancellatura
				  // avviene anche se il secondo click &egrave; destro (come voluto)
				  
				  /* se il secondo click viene fatto nella stessa posizione del primo click,
				   * reset, togliendo l'evidenziatore, cancellerebbe il cerchio (ridotto
				   * a un solo punto). Per evitare ci&ograve;, memorizzo le coordinate e
				   * ridisegno il punto dopo il reset
				   */
				  
				  if ( (centro.posx() == altro.posx()) && (centro.posy() == altro.posy())) {
					  int x = centro.posx();
					  int y = centro.posy();
					  reset();
					  canvas.modifica(x, y, StrumentoDiDisegno.getTratto());
				  } else {
					  reset(); // altrimenti basta un reset semplice
				  }
			  }
		  }
	  }
	  
	@Override
	protected void reset() {
		  
		try{
			if (altro != null && altro.rightClick()) {
				canvas.undo(1); // annulla l'evidenziatore
			}
		} catch (NullPointerException e) {
			// non c'&egrave ancora un canvas, non fare nulla
		}
	
		centro = null;
		altro = null;
	}
	
	protected abstract void disegna();

}
