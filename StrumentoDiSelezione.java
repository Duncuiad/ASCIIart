/** Le sottoclassi di questa classe astratta implementano strumenti di selezione
 *
 *
 */
public abstract class StrumentoDiSelezione extends Strumento {
	//ATTRIBUTI
	/** Primo evento da registrare */
	protected DragStart pIniziale = null;
	/** Secondo evento da registrare */
	protected DragEnd pFinale = null;
	/** Action point */
	protected MouseClick actionPoint = null;
	/** Il rettangolo di canvas individuato dai due punti di inizio e fine di drag*/
	protected Canvas areaSelezionata = null;
	/** Il tratto del riquadro di selezione */
	protected final char trattoSelezione = '.';
	//COSTRUTTORI
	public StrumentoDiSelezione(Canvas canvas) {
		super(canvas);
	}

	//METODI
	
	/** Riceve un evento di mouse e costruisce l'area di selezione per trascinamento. Dopodich&egrave; agisce su tale area
	 * secondo il contratto della sottoclasse che chiama il metodo
	 * 
	 * @param e l'evento di mouse a cui rispondere
	 */
	public void ricevi(EventoDiMouse e) {
		
		if (e instanceof DragStart) {
			pIniziale = (DragStart) e;
			
		} else if (e instanceof DragEnd) {
			pFinale = (DragEnd) e;
			areaSelezionata = canvas.copia(pIniziale.posx(),pIniziale.posy(),pFinale.posx(),pFinale.posy());
			
			//disegna il riquadro di selezione
			canvas.addToHistory();
			canvas.modifica(pIniziale.posx(), pIniziale.posy(), pFinale.posx(), pIniziale.posy(), trattoSelezione);
			canvas.modifica(pIniziale.posx(), pFinale.posy(), pFinale.posx(), pFinale.posy(), trattoSelezione);
			canvas.modifica(pIniziale.posx(), pIniziale.posy(), pIniziale.posx(), pFinale.posy(), trattoSelezione);
			canvas.modifica(pFinale.posx(), pIniziale.posy(), pFinale.posx(), pFinale.posy(), trattoSelezione);
			
		} else if (e instanceof MouseClick) {
			actionPoint = (MouseClick) e;
			
			if (!(actionPoint.rightClick()) && pFinale != null) { //se click sinistro e c'&egrave; un'area selezionata
				canvas.undo(1); // rimuove il riquadro di selezione
				canvas.addToHistory();
				azione();
			}
			
			reset();
		}
	}
	
	/** Prototipo del metodo azione(int x1, int y1, int x2, int y2, int x, int y), da ridefinire nelle sottoclassi di StrumentoDiSelezione
	 * 
	 * @param x1 ascissa del primo estremo
	 * @param y1 ordinata del primo estremo
	 * @param x2 ascissa del secondo estremo
	 * @param y2 ordinata del secondo estremo
	 * @param x ascissa dell'action point
	 * @param y ordinata dell'action point
	 */
	public abstract void azione();
	
	@Override
	protected void reset() {
		pIniziale = null;
		pFinale = null;
		actionPoint = null;
		areaSelezionata = null;
	}
	
	@Override
	public String toString() {
		return "Strumento di selezione. " + super.toString();
		}
	}
