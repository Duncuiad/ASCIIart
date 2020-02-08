public class DragStart extends EventoDiMouse {

  //ATTRIBUTI
  /** true sse l'evento &egrave; stato chiamato col tasto destro del mouse */
  public boolean right;

  //COSTRUTTORI
  /** Costruisce l'evento e memorizza da quale tasto del mouse &egrave; stato chiamato
   *
   * @param x la coordinata orizzontale dell'evento
   * @param y la coordinata verticale dell'evento
   * @param right true sse l'evento &egrave; stato chiamato col tasto destro del mouse
   */
  public DragStart(int x, int y, boolean right) {
    super(x,y);
    this.right = right;
  }
  
  /** Costruisce l'evento copiandone un altro gi&agrave; esistente
   * 
   * @param e l'evento DragStart da copiare
   */
  public DragStart(DragStart e) {
	  super(e);
	  this.right = e.right;
  }

  //METODI
  /** Determina se l'evento &egrave; stato chiamato col tasto destro del mouse
   *
   * @return true sse l'evento &egrave; stato chiamato col tasto destro del mouse
   */
  public boolean right() {
    return this.right;
  }

  @Override
  public String toString() {
    return super.toString() + ", di tipo DragStart. " + (right() ? "Tasto destro" : "Tasto Sinistro");
  }

}
