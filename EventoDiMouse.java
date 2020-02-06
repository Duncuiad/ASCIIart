public class EventoDiMouse implements Evento {

  //ATTRIBUTI
  /** La coordinata orizzontale dell'evento */
  private int x;

  /** La coordinata verticale dell'evento */
  private int y;

  //COSTRUTTORI
  /** Non fa nulla, serve alle sottoclassi per la chiamata implicita di super() */
  public EventoDiMouse() {
    // nulla
  }

  /** Costruisce un evento mouse nella posizione (x,y)
   * @param x la coordinata orizzontale dell'evento
   * @param y la coordinata verticale dell'evento
   */
  public EventoDiMouse(int x, int y) {
    this.x = x;
    this.y = y;
  }

  //METODI
  /** Restituisce la coordinata orizzontale dell'evento
   *
   * @return la coordinata orizzontale dell'evento
   */
  public int posx() {
    return this.x;
  }

  /** Restituisce la coordinata verticale dell'evento
   *
   * @return la coordinata verticale dell'evento
   */
  public int posy() {
    return this.y;
  }

}
