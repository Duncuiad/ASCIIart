/** Descrive il termine di un drag del mouse
 *
 *
 *
 */
public class DragEnd extends EventoDiMouse {

	//COSTRUTTORI

	/**Costruisce un DragEnd in (x,y) se EventoDiMouse.dragging &egrave true, altrimenti lancia un'eccezione non controllata
	 * @see EventoDiMouse#EventoDiMouse(int, int)
	 *
	 * @param x l'ascissa dell'evento
	 * @param y l'ordinata dell'evento
	 * @throws DragEndWithoutDragStartException se DragStart non &egrave avvenuto o non &egrave stato seguito solo da MoveMouse
	 */
	public DragEnd(int x, int y) {
		super(x,y);

		if (!EventoDiMouse.dragging) {
			throw new DragEndWithoutDragStartException("DragEnd non ha seguito un DragStart");
			
			/** NOTA:
			 * Per come vengono gestiti gli eventi dalla libreria java.awt, e per come &egrave;
			 * implementata l'applizazione ASCIIArt, questo blocco non viene mai chiamato nella 
			 * sua esecuzione: un DragEnd viene chiamato sempre dopo un DragStart seguito da MouseMove
			 * 
			 * Tuttavia, per un utilizzo diverso da quello di ASCIIArt, questo blocco di codice
			 * effettivamente gestisce il controllo di consequenzialit&agrave; dei drag e soddisfa
			 * la consegna dell'esame
			 */
		}

		EventoDiMouse.dragging = false;
	}

	//METODI

	@Override
	public String toString() {
		return super.toString() + ", di tipo DragEnd";
	}

}
