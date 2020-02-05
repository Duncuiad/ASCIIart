/** Disegna un segmento trascinando il mouse tra due punti del canvas
 *
 *
 */
public class Segmento extends StrumentoDiDisegno {

  /** Coordinate del primo estremo del segmento */
  private int x1, y1;
  /** Coordinate del secondo estremo del segmento */
  private int x2, y2;
  /** Controlla se l'inizio del trascinamento &egrave; stato effettuato con il tasto sinistro */
  private boolean isLeftClick;

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
      DragStart evento = (DragStart) e; //cast
      if ( !(evento.right()) ) {
        this.isLeftClick = true;
        this.x1 = e.posx();
        this.y1 = e.posy();
      }
      else {
        this.isLeftClick = false;
      }
    }

    // caso DragEnd
    if (e instanceof DragEnd) {
      if (this.isLeftClick) {
        this.x2 = e.posx();
        this.y2 = e.posy();
        canvas.modifica(x1, y1, x2, y2, super.getTratto()); // stampa il segmento
        // &egrave; ben posto: DragEnd segue sempre un DragStart, dunque x1 e y1 sono inizializzati
      }
    }
  }

}
