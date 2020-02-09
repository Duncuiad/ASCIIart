/** Disegna una linea seguendo i movimenti del mouse. Lo strumento &egrave; attivato da un click sinistro e disattivato allo stesso modo
 * 
 * 
 *
 */
public class DisegnoAManoLibera extends StrumentoDiDisegnoLibero {
	
	//COSTRUTTORI
	/** @see Strumento#Strumento(Canvas)
	*/
	public DisegnoAManoLibera (Canvas canvas) {
		super(canvas);
	}
	
	//METODI
	@Override
	protected void disegna(MouseMove move) {
		canvas.modifica(move.posx(), move.posy() , StrumentoDiDisegno.getTratto());
	}
	
	@Override
	public String toString() {
		return "Disegno a mano libera: " + super.toString();
	}
}