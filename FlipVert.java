/** Riflette verticalmente l'area selezionata
 * 
 *
 *
 */
public class FlipVert extends StrumentoDiSelezione {
	
	//COSTRUTTORI
	/** @see Strumento#Strumento(Canvas)
	*/
	public FlipVert(Canvas canvas) {
		super(canvas);
	}
	
	//METODI
	
	@Override
	public void azione(DragStart pIniziale, DragEnd pFinale, MouseClick actionPoint) {	  
	int l = Math.abs(pIniziale.posx() - pFinale.posx()); //larghezza
	int h = (int) Math.round(Math.abs(pIniziale.posy() - pFinale.posy()) / 2.0); //altezza partendo dall'asse di simmetria
	  
	int x0 = Math.min(pIniziale.posx(), pFinale.posx()); 
	int y0 = Math.min(pIniziale.posy(), pFinale.posy()) + h;
	  
	char c;
	  
	for(int i=0; i<l; i++) {
		for(int j=0; j<h; j++) {
			c =canvas.car(x0 + i, y0 + j);
			canvas.modifica(x0 + i, y0+ j, canvas.car(x0 + i,y0 - j));
			canvas.modifica(x0 + i, y0 - j, c);
			}
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": " + super.toString();
	}
}


