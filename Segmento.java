/** Disegna un segmento trascinando il mouse tra due punti del canvas
 *
 *
 */
public class Segmento extends StrumentoDiDisegno {

  //ATTRIBUTI
  /** Primo evento da registrare */
  private DragStart pIniziale;
  /** Secondo evento da registrare */
  private DragEnd pFinale;

  //COSTRUTTORI
  public Segmento(Canvas canvas) {
    super(canvas);
  }

  //METODI
  /** Si occupa di gestire gli input da mouse
  * @param e evento corrente
  *
  * instanceof DragStart: memorizza il primo estremo del segmento
  * instanceof DragEnd: memorizza l'altro estremo del segmento e stampa il segmento
  */
  @Override
  public void ricevi(EventoDiMouse e) {

    // caso DragStart
    if (e instanceof DragStart) {
    	this.pIniziale = (DragStart) e;
    	if (!(this.pIniziale.right())) {
			this.canvas.addToHistory();
			canvas.modifica(pIniziale.posx(), pIniziale.posy(), super.getTratto()); // evidenzia il punto di partenza
    	}
    }

    // caso DragEnd
    if (e instanceof DragEnd) {
      if (!(this.pIniziale.right())) {
    	this.pFinale = (DragEnd) e;
        canvas.modifica(pIniziale.posx(), pIniziale.posy(), pFinale.posx(), pFinale.posy(), super.getTratto()); // stampa il segmento
        // &egrave; ben posto: DragEnd segue sempre un DragStart, dunque pIniziale non è null
      }
      
      // torna allo stato neutro
      this.pIniziale = null;
      this.pFinale = null;
    }
  }

  @Override
  public boolean equals(Object altro) {
    if (altro instanceof Segmento) {
      return true;
    } else {
      return false;
    }
  } // hashCode overridden in classe Strumento

  @Override
  public String toString() {
    return "Segmento: " + super.toString();
  }

}
