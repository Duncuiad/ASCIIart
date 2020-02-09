/** Le sottoclassi di questa classe astratta implementano strumenti che tracciano figure su un canvas
 *
 *
 */
public abstract class StrumentoDiDisegno extends Strumento {

	//ATTRIBUTI
	/** Il tratto di disegno corrente, comune a tutte le istanze di StrumentoDiDisegno */
	private static char trattoDiDisegno = '*';
	
	/** L'evidenziatore utilizzato da alcuni strumenti di disegno per mostrare sul canvas il punto di partenza */
	 protected static final char evidenziatore = '*';
	
	//COSTRUTTORI
	/** Costruisce lo StrumentoDiDisegno associandolo a un canvas
	 * 
	 * @param canvas
	 */
	public StrumentoDiDisegno(Canvas canvas) {
		super(canvas);
	}
	  
	//METODI
	/** Modifica il tratto di disegno
	 *
	 * @param c Il carattere che si vuole utilizzare
	 */
	public static void setTratto(char c) {
	  StrumentoDiDisegno.trattoDiDisegno = c;
	}
	
	/** Restituisce il carattere con cui si sta disegnando
	 *
	 * @return il carattere con cui si sta disegnando
	 */
	public static char getTratto() {
	  return trattoDiDisegno;
	}
	  
	@Override
	protected void reset() {
		// a meno che non venga ridefinita nelle sottoclassi, non fa nulla
	}
	
	@Override
	public String toString() {
	  return "Strumento di disegno, tratto: " + getTratto() + ". " + super.toString();
	}

}
