/** Questa classe astratta &egrave; la superclasse (diretta o indiretta) di tutti gli strumenti
 *
 *
 */
public abstract class Strumento {

  /** Questo &egrave; il riferimento al canvas su cui opera uno Strumento */
  private Canvas canvas;

  //COSTRUTTORI
  /** Associa allo Strumento il canvas su cui agire
   *
   * @param canvas riferimento al canvas su cui operare
   */
  public Strumento(Canvas canvas) {
    this.canvas = canvas;
  }

  /** Il costruttore senza parametri inizializza this.canvas a null */
  public Strumento() {
    this(null);
  }

  //METODI
  /** Prototipo del metodo ricevi(EventoDiMouse e), da ridefinire nelle sottoclassi di Strumento
   *
   * @param e l'EventoDiMouse a cui lo Strumento deve rispondere
   */
  public abstract void ricevi(EventoDiMouse e);

}
