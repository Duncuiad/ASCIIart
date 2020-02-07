/** Gestisce il premere un tasto della tastiera
 *
 *
 */
public class EventoDiTastiera implements Evento {

	//ATTRIBUTI
	/** Il carattere premuto */
	private char caratterePremuto;

	//COSTRUTTORI
	/** Associa all'evento il tasto premuto
	 *
	 * @param c il carattere che identifica il tasto premuto
	 */
	public EventoDiTastiera(char c) {
		this.caratterePremuto = c;
	}

	//METODI
	/** Restituisce il carattere premuto
	 *
	 * @return il carattere premuto
	 */
	public char car() {
		return caratterePremuto;
	}

  @Override
  public String toString() {
    return "Evento di tastiera, carattere premuto: " + this.car();
  }
}
