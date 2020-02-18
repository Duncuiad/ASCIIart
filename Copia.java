/** Copia l'area selezionata in una posizione indicata da un click del mouse
 * 
 *
 *
 */
public class Copia extends StrumentoDiSelezione {

	//COSTRUTTORI
	/** @see Strumento#Strumento(Canvas)
	*/
	public Copia(Canvas canvas) {
		super(canvas);
	}
	
	//METODI
	  
	@Override
	public void azione() {
		  
		int l = Math.abs(pIniziale.posx() - pFinale.posx());
		int h = Math.abs(pIniziale.posy() - pFinale.posy());
		  
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < h ; j++) {
				canvas.modifica(actionPoint.posx() + i, actionPoint.posy() - j, areaSelezionata.car(i,h-j-1));
			}
		}
	
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": " + super.toString();
	}

}
