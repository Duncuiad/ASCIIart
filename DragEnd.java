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
//			throw new DragEndWithoutDragStartException();
			
			/* NOTA
			 * 
			 * Il lancio dell'eccezione &egrave disattivato perch&eacute; se clicco l'altro
			 * tasto del mouse mentre sto trascinando, desidero che il programma continui a
			 * funzionare. In ogni caso, se in una determinata situazione fosse desiderato
			 * il lancio dell'eccezione dato EventoDiMouse.dragging == false, basterebbe
			 * togliere il commento alla riga qui sopra
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
