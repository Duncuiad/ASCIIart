/** Descrive i click del mouse
 *
 *
 *
 */
public class MouseClick extends EventoDiMouse {

	//ATTRIBUTI

	/** true se il click &egrave; stato fatto con il tasto destro del mouse */
	private boolean rightClick;

	/** true se l'evento &egrave; un doppio click */
	private boolean doubleClick;

	//COSTRUTTORI

	/** Costruisce l'evento nella posizione (x,y) e memorizza il tipo di click
	 * @see EventoDiMouse#EventoDiMouse(int, int)
	 *
	 * @param x l'ascissa dell'evento
	 * @param y l'ordinata dell'evento
	 * @param rightClick true se si &egrave; premuto il tasto destro del mouse
	 * @param doubleClick true se si &egrave; fatto doppio click
	 */
	public MouseClick(int x, int y, boolean rightClick, boolean doubleClick) {
		super(x,y);
		if (EventoDiMouse.dragging) {
			throw new DragStartWithoutDragEndException("Click del mouse durante un drag");
			//Significa che a un DragStart non sono seguiti solo MouseMove e infine un DragEnd
			
			/** NOTA:
			 * Per come vengono gestiti gli eventi dalla libreria java.awt, e per come &egrave;
			 * implementata l'applizazione ASCIIArt, questo blocco non viene mai chiamato nella 
			 * sua esecuzione: se durante un drag avviene un click del mouse, prima di costruire
			 * un MouseClick viene costruito un DragEnd che setta EventoDiMouse.dragging = false
			 * 
			 * Tuttavia, per un utilizzo diverso da quello di ASCIIArt, questo blocco di codice
			 * effettivamente gestisce il controllo di consequenzialit&agrave; dei drag e soddisfa
			 * la consegna dell'esame
			 */
		}
		this.rightClick = rightClick;
		this.doubleClick = doubleClick;
	}

	//METODI

	/** Restituisce true se si &egrave; premuto il tasto destro del mouse
	 *
	 * @return true se si &egrave; premuto il tasto destro del mouse
	 */
	public boolean rightClick() {
		return rightClick;
	}

	/** Restituisce true se si &egrave; fatto doppio click
	 *
	 * @return true se si &egrave; fatto doppio click
	 */
	public boolean doubleClick() {
		return doubleClick;
	}

	@Override
	public String toString() {
		return super.toString() + ", di tipo MouseClick. " + (doubleClick ? "Doppio click. " : "") + (rightClick ? "Tasto destro" : "Tasto sinistro");
	}

}
