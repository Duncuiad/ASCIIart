/** Riflette orizzontalmente l'area selezionata
 * 
 *
 *
 */
public class FlipHoriz extends StrumentoDiSelezione {
	
	//COSTRUTTORI

	/** @see Strumento#Strumento(Canvas)
	*/
	public FlipHoriz(Canvas canvas) {
		super(canvas);
	}
	
	//METODI
	
	@Override
	public void azione() {
		int l = (int) Math.round( Math.abs(pIniziale.posx() - pFinale.posx()) / 2.0); //larghezza partendo dall'asse di simmetria
		int h = Math.abs(pIniziale.posy() - pFinale.posy());
		
		int x0 = l+Math.min(pIniziale.posx(),pFinale.posx()); //asse di simmetria
		int y0 = Math.min(pIniziale.posy(), pFinale.posy()); // ordinata minima del rettangolo selezionato
		
		char c;
		
		for(int i=0; i<l; i++) {
			for(int j=0; j<h; j++) {
				c=canvas.car(x0+i, y0+j);
				canvas.modifica(x0+i, y0+j, canvas.car(x0 - i, y0 + j));
				canvas.modifica(x0-i, y0+j, c);
			}
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": " + super.toString();
	}
	
}
