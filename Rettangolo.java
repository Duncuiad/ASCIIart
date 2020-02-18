/** Disegna un rettangolo trascinando il mouse tra due punti del canvas
 *
 *
 */
public final class Rettangolo extends StrumentoDiDisegnoDrag {

	//COSTRUTTORI
	/** Costruisce lo strumento Rettangolo associandolo a un canvas
	 *
	 * @param canvas il canvas da associare
	 */
	public Rettangolo(Canvas canvas) {
		super(canvas);
	}

	//METODI
	@Override
	protected void disegna(DragStart pIniziale, DragEnd pFinale) {
		canvas.modifica(pIniziale.posx(), pIniziale.posy(), pFinale.posx(), pIniziale.posy(), StrumentoDiDisegno.getTratto()); // lato orizzontale 1
		canvas.modifica(pIniziale.posx(), pFinale.posy(), pFinale.posx(), pFinale.posy(), StrumentoDiDisegno.getTratto()); // lato orizzontale 2
		canvas.modifica(pIniziale.posx(), pIniziale.posy(), pIniziale.posx(), pFinale.posy(), StrumentoDiDisegno.getTratto()); // lato verticale 1
		canvas.modifica(pFinale.posx(), pIniziale.posy(), pFinale.posx(), pFinale.posy(), StrumentoDiDisegno.getTratto()); // lato verticale 2
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": " + super.toString();
	}

}
