/** Disegna una linea spessa seguendo i movimenti del mouse. Lo strumento &egrave; attivato da un click sinistro e disattivato allo stesso modo
 * 
 * 
 *
 */
public class Pennello extends StrumentoDiDisegnoLibero {
	
	//ATTRIBUTI
	/** Raggio del pennello */
	private final int raggio = 2; // sempre >0

	//COSTRUTTORI
	/** @see Strumento#Strumento(Canvas)
	*/
	public Pennello(Canvas canvas) {
		super(canvas);
	}

	//METODI
	@Override
	protected void disegna(MouseMove move) {
		
		for (int i = 1-raggio; i < raggio; i++) {
			for (int j = 1-raggio; j < raggio; j++) {
				canvas.modifica(move.posx() + i, move.posy() + j, StrumentoDiDisegno.getTratto());
			}
		}

	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": " + super.toString();
	}

}
