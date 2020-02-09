/** Disegna un quadrato centrato nel primo click e passante per il secondo click
 * 
 *
 *
 */
public class Quadrato extends StrumentoDiDisegnoCentro {

	//COSTRUTTORI
	/** @see Strumento#Strumento(Canvas)
	*/
	public Quadrato(Canvas canvas) {
		super(canvas);
	}

	//METODI
	@Override
	protected void disegna(MouseClick centro, MouseClick altro) {
		
		  int semiLato = Math.max(Math.abs(centro.posx() - altro.posx()), Math.abs(centro.posy() - altro.posy()));
		  
		  int xMin = centro.posx() - semiLato;
		  int xMax = centro.posx() + semiLato;
		  int yMin = centro.posy() - semiLato;
		  int yMax = centro.posy() + semiLato;

		  canvas.modifica(xMin, yMax, xMax, yMax, StrumentoDiDisegno.getTratto()); //lato orizzontale superiore
		  canvas.modifica(xMin, yMin, xMax, yMin, StrumentoDiDisegno.getTratto()); //lato orizzontale inferiore
		  canvas.modifica(xMin, yMin, xMin, yMax, StrumentoDiDisegno.getTratto()); //lato verticale sinistro
		  canvas.modifica(xMax, yMin, xMax, yMax, StrumentoDiDisegno.getTratto()); //lato verticale destro
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": " + super.toString();
	}
	
}
