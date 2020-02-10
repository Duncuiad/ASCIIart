/** Le sottoclassi di questa classe astratta implementano strumenti che selezionano e agiscono su un'area del canvas
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
	private final char TRATTOSELEZIONE = '.';

	//COSTRUTTORI
	/** @see Strumento#Strumento(Canvas)
	*/
	public StrumentoDiSelezione(Canvas canvas) {
		super(canvas);
	}

	//METODI

	@Override
	public void ricevi(EventoDiMouse e) {

		if (e instanceof DragStart) {
			if (pIniziale != null) { // stai facendo un nuovo drag subito dopo un drag precedente
				canvas.undo(1); // rimuove il riquadro di selezione
			}
			pIniziale = (DragStart) e;

		} else if (e instanceof DragEnd) {
			pFinale = (DragEnd) e;
			areaSelezionata = canvas.copia(pIniziale.posx(),pIniziale.posy(),pFinale.posx(),pFinale.posy());

			//disegna il riquadro di selezione
			canvas.addToHistory();
			canvas.modifica(pIniziale.posx(), pIniziale.posy(), pFinale.posx(), pIniziale.posy(), TRATTOSELEZIONE);
			canvas.modifica(pIniziale.posx(), pFinale.posy(), pFinale.posx(), pFinale.posy(), TRATTOSELEZIONE);
			canvas.modifica(pIniziale.posx(), pIniziale.posy(), pIniziale.posx(), pFinale.posy(), TRATTOSELEZIONE);
			canvas.modifica(pFinale.posx(), pIniziale.posy(), pFinale.posx(), pFinale.posy(), TRATTOSELEZIONE);

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

	/** Applica una trasformazione all'area selezionata
	 *
	 */
	public abstract void azione();

	@Override
	protected void reset() {

		if ( areaSelezionata != null) {
			if ( actionPoint == null || (actionPoint != null && actionPoint.rightClick()) ) {
				canvas.undo(1); // rimuove il riquadro di selezione
			}
		}

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
