/** Disegna un segmento trascinando il mouse tra due punti del canvas
 *
 *
 */
public class Segmento extends StrumentoDiDisegnoDrag {

	//COSTRUTTORI
	/** @see Strumento#Strumento(Canvas)
	*/
	public Segmento(Canvas canvas) {
	  super(canvas);
	}
	
	//METODI
	@Override
	protected void disegna(DragStart pIniziale, DragEnd pFinale) {
	      canvas.modifica(pIniziale.posx(), pIniziale.posy(), pFinale.posx(), pFinale.posy(), StrumentoDiDisegno.getTratto()); // stampa il segmento
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": " + super.toString();
	}

}
