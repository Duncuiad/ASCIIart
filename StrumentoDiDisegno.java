/** Le sottoclassi di questa classe astratta implementano strumenti di disegno
 *
 *
 */
public abstract class StrumentoDiDisegno extends Strumento {

  /** Questo &egrave; il tratto di disegno corrente, comune a tutte le istanze di StrumentoDiDisegno */
  private static char trattoDiDisegno = '*';
  /* protected perché viene usato dalle istanze di ogni sottoclasse concreta */

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

}
