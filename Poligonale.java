/** Disegna una linea spezzata tramite successivi click del mouse. La spezzata &egrave chiusa se l'ultimo &egrave; un doppio click
 * 
 *
 *
 */
public class Poligonale extends StrumentoDiDisegno {
	
	//ATTRIBUTI
	/** Primo evento da registrare. Una volta che &egrave; stato segnato il primo punto, contiene il primo estremo del segmento corrente */
	private MouseClick primoClick = null;
	
	/** Secondo evento da registrare. Una volta che &egrave; stato segnato il primo punto, contiene il secondo estremo del segmento corrente */
	private MouseClick secondoClick = null;
	
	/** true se si e' effettuato un primoClick */
	private boolean start = false;
	
	/**L'ascissa del punto iniziale della poligonale*/
	private int x0=-1; //inizializzazione con un valore impossibile di ascissa
	
	/**L'ordinata del punto iniziale della poligonale**/
	private int y0=-1;//inizializzazione con un valore impossibile di ascissa
	
	//COSTRUTTORI
	/** @see Strumento#Strumento(Canvas)
	*/
	public Poligonale(Canvas canvas) {
		super(canvas);
	}
	  
	//METODI
	@Override
	public void ricevi(EventoDiMouse e) {
		  
		if (e instanceof MouseClick) {
			  
			if(!start) {	//finché non faccio un click semplice sinistro entro in questo if  
				primoClick= (MouseClick) e;
				  
				if(!primoClick.rightClick() && !primoClick.doubleClick()) {
					start = true;
					x0 = primoClick.posx();
					y0 = primoClick.posy();
					canvas.addToHistory();
					canvas.modifica(x0, y0, StrumentoDiDisegno.getTratto());
				}
				  
			} else {
				secondoClick = (MouseClick) e;
				  
				if(!secondoClick.rightClick() && !secondoClick.doubleClick()) {
					//traccio un segmento tra primoClick e secondoClick e ogni volta copio l'informazione di secondoClick in primoClick
					canvas.modifica(primoClick.posx(), primoClick.posy(), secondoClick.posx(), secondoClick.posy(), StrumentoDiDisegno.getTratto());
					primoClick=secondoClick;
				} else if(!secondoClick.rightClick() && secondoClick.doubleClick()) {  
					//termino la poligonale corrente unendo il punto del doppio click con il punto iniziale di coordinate (x0,y0)
					canvas.modifica(x0, y0, secondoClick.posx(), primoClick.posy(), StrumentoDiDisegno.getTratto());
					start = false;
				} else if(secondoClick.rightClick()) { 
					reset(); // lascio disegnata la poligonale fino a questo punto
				}
			}
		}
	}


	@Override
	protected void reset() {
		start=false;
		primoClick = null;
		secondoClick = null;
		x0=-1;
		y0=-1;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": " + super.toString();
	}

}