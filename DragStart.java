/** Descrive l'inizio di un drag del mouse
 * 
 * 
 *
 */
public class DragStart extends EventoDiMouse {

	//ATTRIBUTI
		
	/** true sse l'evento &egrave; stato chiamato col tasto destro del mouse */
	public boolean right;
	
	//COSTRUTTORI
	  
	/** Costruisce l'evento e memorizza da quale tasto del mouse &egrave; stato chiamato
	 *@see EventoDiMouse#EventoDiMouse(int, int)
	 *
	 * @param x la coordinata orizzontale dell'evento
	 * @param y la coordinata verticale dell'evento
	 * @param right true sse l'evento &egrave; stato chiamato col tasto destro del mouse
	 */
	public DragStart(int x, int y, boolean right) {
		super(x,y);
		
		if (EventoDiMouse.dragging) {
			throw new DragStartWithoutDragEndException("Click del mouse durante un drag");
			//Significa che a un DragStart non sono seguiti solo MouseMove e infine un DragEnd
			
			/** NOTA:
			 * Per come vengono gestiti gli eventi dalla libreria java.awt, e per come &egrave;
			 * implementata l'applizazione ASCIIArt, questo blocco non viene mai chiamato nella 
			 * sua esecuzione: se durante un drag viene iniziato un altro drag, prima di costruire
			 * un DragStart viene costruito un DragEnd che setta EventoDiMouse.dragging = false
			 * 
			 * Tuttavia, per un utilizzo diverso da quello di ASCIIArt, questo blocco di codice
			 * effettivamente gestisce il controllo di consequenzialit&agrave; dei drag e soddisfa
			 * la consegna dell'esame
			 */
		}
		
		EventoDiMouse.dragging = true;
		this.right = right;
	}
	
	//METODI
	  
	/** Determina se l'evento &egrave; stato chiamato col tasto destro del mouse
	 *
	 * @return true sse l'evento &egrave; stato chiamato col tasto destro del mouse
	 */
	public boolean right() {
		return this.right;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", di tipo DragStart. " + (right() ? "Tasto destro" : "Tasto Sinistro");
	}

}
