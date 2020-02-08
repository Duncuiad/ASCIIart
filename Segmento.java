/** Disegna un segmento trascinando il mouse tra due punti del canvas
 *
 *
 */
public class Segmento extends StrumentoDiDisegnoDrag {

  //COSTRUTTORI
  public Segmento(Canvas canvas) {
    super(canvas);
  }

  protected void azione() {
        canvas.modifica(pIniziale.posx(), pIniziale.posy(), pFinale.posx(), pFinale.posy(), super.getTratto()); // stampa il segmento
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
