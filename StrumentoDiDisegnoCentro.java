/** Uno StrumentoDiDisegnoCentro associa al primo click il centro di una figura e al secondo un punto sul suo bordo, disegnando la figura
 * 
 * 
 *
 */
public abstract class StrumentoDiDisegnoCentro extends StrumentoDiDisegno {
	
	//ATTRIBUTI
	
	/** Primo evento da registrare */
	private MouseClick centro;
	  
	/** Secondo evento da registrare */
	private MouseClick altro;
	
	//COSTRUTTORI
	  
	/** @see Strumento#Strumento(Canvas)
	*/
	public StrumentoDiDisegnoCentro(Canvas canvas) {
		super(canvas);
	}
	
	//METODI
	
	/** Disegna la figura corrispondente allo strumento, dato il centro e un punto sul bordo
	 * 
	 * @param centro il centro della figura
	 * @param altro il punto sul bordo
	 */
	protected abstract void disegna(MouseClick centro, MouseClick altro);

	@Override
	public void ricevi(EventoDiMouse e) {

		if (e instanceof MouseClick) {

			if (centro == null) { // primo click

				centro = (MouseClick) e;

				if ( !(centro.rightClick()) ) { // se click sinistro
					canvas.addToHistory();
					//precedente = canvas.car(centro.posx(), centro.posy());
					canvas.modifica(centro.posx(), centro.posy(), StrumentoDiDisegno.EVIDENZIATORE); // mette l'evidenziatore al centro
				} else {
					reset(); // se click destro, non fa nulla e torna allo stato iniziale
				}

			} else { // secondo click

				altro = (MouseClick) e;

				if ( !(altro.rightClick()) ) { // se secondo click sinistro

					canvas.undo(1); // toglie l'evidenziatore
					canvas.addToHistory(); 
					disegna(centro, altro);

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
			if ((centro != null && !(centro.rightClick()) && altro == null) || (altro != null && altro.rightClick())) { // lazy evaluation
				// nel primo caso, ho preso il centro ma non ho ancora preso l'altro punto
				// avviene per esempio quando cambio strumento dopo aver preso soltanto il centro
				// nel secondo caso, ho preso il centro ma dopo ho cliccato il tasto destro del mouse
				canvas.undo(1); // togli l'evidenziatore
			}
		} catch (NullPointerException e) {
			// non c'&egrave ancora un canvas, non fare nulla
		}
	
		centro = null;
		altro = null;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", con input di tipo centro";
	}

}
