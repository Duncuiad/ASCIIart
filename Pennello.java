/**
 *
 *
 */
public class Pennello extends StrumentoDiDisegnoLibero {
	
	//ATTRIBUTI
	/** Raggio del pennello */
	private final static int raggio = 2; // sempre >0

	/**
	 * @param canvas
	 */
	public Pennello(Canvas canvas) {
		super(canvas);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void disegna() {
		
		for (int i = 1-raggio; i < raggio; i++) {
			for (int j = 1-raggio; j < raggio; j++) {
				canvas.modifica(move.posx() + i, move.posy() + j, StrumentoDiDisegno.getTratto());
			}
		}

	}

}
