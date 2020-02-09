/** Uno StrumentoDiDisegnoDrag disegna una figura trascinando il mouse tra un punto di partenza e un punto di arrivo
 * 
 * 
 *
 */
public abstract class StrumentoDiDisegnoDrag extends StrumentoDiDisegno {

	//ATTRIBUTI
	/** Primo evento da registrare */
	private DragStart pIniziale;
	/** Secondo evento da registrare */
	private DragEnd pFinale;

	
	//COSTRUTTORI
	/** @see Strumento#Strumento(Canvas)
	*/
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
				canvas.modifica(pIniziale.posx(), pIniziale.posy(), StrumentoDiDisegno.getTratto()); // evidenzia il punto di partenza
		    }
		}
	
		// caso DragEnd
		if (e instanceof DragEnd) {
			if ((this.pIniziale != null) && !(this.pIniziale.right())) {
		    	this.pFinale = (DragEnd) e;
		    	disegna(pIniziale, pFinale);
		        // &egrave; ben posto: DragEnd segue sempre un DragStart, dunque pIniziale non &egrave; null
		    }
		
		    // torna allo stato neutro senza togliere il carattere presente nel punto di partenza
		    // (cosa che farebbe reset())
		    this.pIniziale = null;
		    this.pFinale = null;
		}
	}
	
	/** Disegna la figura per trascinamento, dato un punto iniziale e un punto finale
	 * 
	 * @param pIniziale il punto iniziale del trascinamento
	 * @param pFinale il punto finale del trascinamento
	 */
	protected abstract void disegna(DragStart pIniziale, DragEnd pFinale);
	
	@Override
	protected void reset() {
		if (pIniziale != null) {
			canvas.undo(1); // rimuove l'evidenziatore
		}
	    this.pIniziale = null;
	    this.pFinale = null;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", con input di tipo trascinamento";
	}

}
