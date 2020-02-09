/** Cancella l'area selezionata
 * 
 *
 *
 */
public class Cancella extends StrumentoDiSelezione {
	
	//COSTRUTTORI
	/** @see Strumento#Strumento(Canvas)
	*/
	public Cancella(Canvas canvas) {
		super(canvas);
	}
	
	@Override
	public void azione() {
		int x0 = Math.min(pIniziale.posx(), pFinale.posx());
		int y0 = Math.min(pIniziale.posy(), pFinale.posy());
		int l = Math.abs(pIniziale.posx() - pFinale.posx());
		int h = Math.abs(pIniziale.posy() - pFinale.posy());
		  
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < h; j++) {
				canvas.modifica(x0+i, y0+j, ' ');
			}
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": " + super.toString();
	}

}
