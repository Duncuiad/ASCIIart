/** Gestisce i click del mouse
 * 
 *
 */
public class MouseClick extends EventoDiMouse {

  //ATTRIBUTI
  /** true se il click &egrave; stato fatto con il tasto destro del mouse */
  private boolean rightClick;
  /** true se l'evento &egrave; un doppio click */
  private boolean doubleClick;

  //COSTRUTTORI
  /** Costruisce l'evento nella posizione (x,y) e memorizza il tipo di click
   * 
   * @param x l'ascissa dell'evento
   * @param y l'ordinata dell'evento
   * @param rightClick true se si &egrave; premuto il tasto destro del mouse
   * @param doubleClick true se si &egrave; fatto doppio click
   */
  public MouseClick(int x, int y, boolean rightClick, boolean doubleClick) {
    super(x,y);
    this.rightClick = rightClick;
    this.doubleClick = doubleClick;
  }
  
  /** Restituisce true se si &egrave; premuto il tasto destro del mouse
   * 
   * @return true se si &egrave; premuto il tasto destro del mouse
   */
  //METODI
  public boolean rightClick() {
	  return rightClick;
  }
  
  /** Restituisce true se si &egrave; fatto doppio click
   * 
   * @return true se si &egrave; fatto doppio click
   */
  public boolean doubleClick() {
	  return doubleClick;
  }
}
