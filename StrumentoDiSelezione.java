/** Le sottoclassi di questa classe astratta implementano strumenti di selezione
 *
 *
 */
public abstract class StrumentoDiSelezione extends Strumento {
	
  //COSTRUTTORI
  public StrumentoDiSelezione(Canvas canvas) {
	  super(canvas);
  }

  //METODI
  /** Prototipo del metodo azione(int x1, int y1, int x2, int y2, int x, int y), da ridefinire nelle sottoclassi di StrumentoDiSelezione
   *
   * @param e l'EventoDiMouse a cui lo Strumento deve rispondere
   */
  public abstract void azione(int x1, int y1, int x2, int y2, int x, int y);

  @Override
  public String toString() {
    return "Strumento di selezione. " + super.toString();
  }
}
