/** Questa classe astratta &egrave; la superclasse indiretta di tutti gli strumenti
 *
 *
 */
public abstract class Strumento {

  //ATTRIBUTI
  /** Il canvas su cui opera lo strumento */
  protected Canvas canvas;
  /* protected perché viene usato dalle istanze di ogni sottoclasse concreta */

  //COSTRUTTORI
  /** Associa allo Strumento il canvas su cui agire
   *
   * @param canvas canvas su cui operare
   */
  public Strumento(Canvas canvas) {
    this.canvas = canvas;
    this.reset();
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
  
  protected abstract void reset();

  @Override
  public int hashCode() {
    return this.getClass().hashCode();
  }

  @Override
  public String toString() {
    return "Strumento collegato a canvas " + canvas.hashCode();
  }

}
