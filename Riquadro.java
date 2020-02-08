/** Disegna un riquadro trascinando il mouse tra due punti del canvas
 *
 *
 */
public class Riquadro extends StrumentoDiDisegnoDrag {

  //COSTRUTTORI
  public Riquadro(Canvas canvas) {
    super(canvas);
  }

  protected void azione() {
	  canvas.modifica(pIniziale.posx(), pIniziale.posy(), pFinale.posx(), pIniziale.posy(), super.getTratto()); // lato orizzontale 1
	  canvas.modifica(pIniziale.posx(), pFinale.posy(), pFinale.posx(), pFinale.posy(), super.getTratto()); // lato orizzontale 2
	  canvas.modifica(pIniziale.posx(), pIniziale.posy(), pIniziale.posx(), pFinale.posy(), super.getTratto()); // lato verticale 1
	  canvas.modifica(pFinale.posx(), pIniziale.posy(), pFinale.posx(), pFinale.posy(), super.getTratto()); // lato verticale 2
  }

  @Override
  public boolean equals(Object altro) {
    if (altro instanceof Riquadro) {
      return true;
    } else {
      return false;
    }
  } // hashCode overridden in classe Strumento

  @Override
  public String toString() {
    return "Riquadro: " + super.toString();
  }

}
