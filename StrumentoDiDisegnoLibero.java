/** Uno StrumentoDiDisegnoLibero traccia una figura seguendo i movimenti del mouse. Viene attivato e disattivato da un click col tasto sinistro del mouse
 * 
 * 
 *
 */
public abstract class StrumentoDiDisegnoLibero extends StrumentoDiDisegno {
	
	//ATTRIBUTI
	/** true se il disegno a mano libera &egrave; in modalita' attiva */
	private boolean stato = false;
	
	/** Click col mouse */
	private MouseClick click = null;
	
	/** Movimento del mouse */
	private MouseMove move = null;
	
	//COSTRUTTORI
	/** @see Strumento#Strumento(Canvas)
	*/
	public StrumentoDiDisegnoLibero(Canvas canvas) {
		super(canvas);
	}

	//METODI
	@Override
	public void ricevi(EventoDiMouse e) {
		if(e instanceof MouseClick) {
			if(!stato) {
				canvas.addToHistory();
			}
			click = (MouseClick) e;
			if(!click.rightClick() && !click.doubleClick())
			stato=!stato;
	}
		if(stato) {
			if(e instanceof MouseMove) {
				move = (MouseMove) e;
				disegna(move);
			}
		}

	}
	
	/** Disegna una linea continua seguendo il movimento del mouse
	 * 
	 * @param move il punto su cui si trova attualmente il mouse
	 */
	protected abstract void disegna(MouseMove move);
	
	@Override
	protected void reset() {
		stato = false;
		click = null;
		move = null;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", con input di tipo disegno libero";
	}
	
}
