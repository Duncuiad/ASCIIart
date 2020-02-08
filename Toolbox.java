import java.util.Map; //Map<K,V>, HashMap<K,V>
import java.util.HashMap;

/** Insieme di strumenti, ciascuno associato a un carattere in modo univoco, e in cui al pi&ugrave; uno strumento &egrave; in ogni istante attivo
 *
 *
 */
public class Toolbox {

  //ATTRIBUTI
  /** Il canvas su cui agiscono gli strumenti della toolbox */
  private Canvas canvas;
  /** Il dizionario che associa a ogni etichetta uno strumento */
  private HashMap<Character,Strumento> strumenti = null;
  /** Lo strumento attualmente selezionato */
  private Strumento attivo = null;

  //COSTRUTTORI
  /** Associa alla toolbox il suo canvas e inizializza la lista degli strumenti
   *
   * @param canvas il canvas su cui la toolbox deve agire
   */
  public Toolbox(Canvas canvas) {
    this.canvas = canvas;
    strumenti = new HashMap<Character,Strumento>(); // crea una HashMap inizialmente lunga 16 elementi, che viene espansa dopo i primi 12 (loading factor 0.75)
  }

  //METODI
  /** Aggiunge uno strumento alla toolbox
   *
   * @param s lo strumento da aggiungere
   * @param c l'etichetta con cui si vuole identificare lo strumento
   */
  public void aggiungi(Strumento s, char c) {
	  if (strumenti.containsKey(c)) { // se c e' gia' l'etichetta di uno strumento, sovrascrivi lo strumento precedente
		  strumenti.replace(c, s);
	  }
	  else {
		  strumenti.put(c, s); //autoboxing di c
	  }
  }

  /** Riceve un evento e agisce di conseguenza
   * 1. se l'evento &egrave; di tastiera (&egrave; un carattere):
   * 	1.1 se il tasto premuto identifica uno strumento, cambia lo strumento
   * 	1.2 se invece non identifica uno strumento ma si &egrave; premuto il tasto "u", annulla l'ultima azione sul canvas
   * 	1.3 se invece non &egrave; nulla di tutto ci&ograve, e lo strumento attivo &egrave; di disegno, usa il carattere come tratto
   * 2. se l'evento &egrave; di mouse (&egrave; un click, un move o un drag) e c'&egrave; uno strumento attivo, passalo a quello strumento
   *
   * @param e l'evento registrato
   */
  public void ricevi(Evento e) {
	  if (e instanceof EventoDiTastiera) { // 1. caso tastiera
		  EventoDiTastiera eTast = (EventoDiTastiera) e;
		  char tastoPremuto = eTast.car();
		  if (strumenti.containsKey(tastoPremuto)) { // 1.1 se il tasto indica uno strumento nel toolbox
			  this.attiva(tastoPremuto); // cambia strumento attivo
		  } else if (tastoPremuto == 'u') { // 1.2 se 'u' non &egrave; nel toolbox
				  canvas.undo(1); // torna indietro di un'azione
		  } else if (this.attivo instanceof StrumentoDiDisegno) { // 1.3 se lo strumento attivo � di disegno
					  StrumentoDiDisegno.setTratto(tastoPremuto); // cambia il tratto
		  }
	  }
	  else if ((e instanceof EventoDiMouse) && (this.attivo != null)) { // 2. caso mouse
		  EventoDiMouse eMouse = (EventoDiMouse) e;
		  this.attivo.ricevi(eMouse); // passa l'evento allo strumento attivo
	  }
  }

  /** Restituisce la lista degli strumenti come mappa tra le etichette e gli strumenti
   *
   * @return la mappa (etichetta, strumento)
   */
  public Map<Character,Strumento> strumenti() {
	  return strumenti;
  }

  /** Restituisce lo strumento attualmente selezionato
   *
   * @return lo strumento attualmente selezionato
   */
  public Strumento attivo() {
    return attivo;
  }

  /** Se il carattere passato identifica uno strumento nella toolbox, rende attivo tale strumento
   *
   * @param c l'etichetta dello strumento da attivare
   */
  public void attiva(char c) {
	  if (strumenti.containsKey(c)) {
		  try {
			  this.attivo.reset(); //prima di tutto resetta lo strumento che sto lasciando
		  } catch (NullPointerException e) {
			  //salta il reset
		  }
		  this.attivo = strumenti.get(c);
	  }
  }

  @Override
  public String toString() {
    String stringa = "Toolbox collegata al canvas " + this.canvas.hashCode() + ". ";
    if (strumenti != null) {
		stringa += "Strumenti collegati: ";
		for (Character key: this.strumenti.keySet()) {
			stringa += (this.strumenti.get(key).getClass().getSimpleName()) + " (" + key + "), "; //nome dello strumento + etichetta
		}
    }
    if (attivo != null) {
    	stringa += "strumento attivo: " + this.attivo.getClass().getSimpleName();
    } else {
    	stringa += "nessuno strumento attivo";
    }
    return stringa;
  }

}
