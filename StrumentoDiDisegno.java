/** Le sottoclassi di questa classe astratta implementano strumenti di disegno
 *
 *
 */
public abstract class StrumentoDiDisegno extends Strumento {

  //ATTRIBUTI
  /** Questo &egrave; il tratto di disegno corrente, comune a tutte le istanze di StrumentoDiDisegno */
  private static char trattoDiDisegno = '*';
  /* protected perché viene usato dalle istanze di ogni sottoclasse concreta */
  
  //COSTRUTTORI
  public StrumentoDiDisegno(Canvas canvas) {
	  super(canvas);
  }
  
  //METODI
  /** Modifica il tratto di disegno
   *
   * @param c Il carattere che si vuole utilizzare
   */
  public static void setTratto(char c) {
    StrumentoDiDisegno.trattoDiDisegno = c;
  }

  /** Restituisce il carattere con cui si sta disegnando
   *
   * @return il carattere con cui si sta disegnando
   */
  public static char getTratto() {
    return trattoDiDisegno;
  }

  @Override
  public String toString() {
    return "Strumento di disegno, tratto: " + this.getTratto() + ". " + super.toString();
  }

}
