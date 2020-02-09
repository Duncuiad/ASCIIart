import java.util.Stack;
import java.util.EmptyStackException;

/** Un canvas &egrave una griglia di caratteri su cui possono agire determinati strumenti
 * 
 * 
 *
 */
public class Canvas {

	/** Il numero di righe del canvas */
	private int R;
	
	/** Il numero di colonne del canvas */
	private int C;
	
	/** La griglia allo stato attuale */
	private Frame corrente;
	
	/** Il registro delle griglie precedenti. La griglia attuale &egrave; in cima a tale registro */
	private Stack<Frame> registroFrame;

	
	//COSTRUTTORI
	/** Costruisce un canvas date le dimensioni desiderate e ne inizializza il registro
	 * 
	 * @param righe il numero di righe del nuovo canvas
	 * @param colonne il numero di colonne del nuovo canvas
	 */
	public Canvas(int righe, int colonne) {
		this.R=righe;
		this.C=colonne;
		Frame nuovoFrame = new Frame(righe, colonne);
		this.registroFrame = new Stack<Frame>();
		registroFrame.push(nuovoFrame);
		corrente = registroFrame.peek();
	}

	public int getR() {
		return this.R;
	}

	public int getC() {
		return this.C;
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @return il carattere in posizione (x,y)
	 */
	public char car(int x, int y) {
		try {return this.corrente.caratteri[y][x];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Errore in canvas " + this.R + "x" + this.C +
			": non posso accedere al carattere in posizione " + x + ", " + y
			+ " (Out of bounds)");
			return '0';
		}
	}
	/**
	 * Restituisce il carattere corrispondente alla cella (x,y) del canvas
	 * @param x numero di riga del canvas
	 * @param y numero di colonna del canvas
	 * @return il carattere corrispondente all'entrata (x,y)
	 */

	public boolean vuota(int x, int y) {
		return this.car(x,y)==' ';
	}

	/**
	 * controlla se le coordinate rispettano le dimensioni del canvas
	 * @param x il numero di riga da modificare
	 * @param y il numero di colonna da modificare
	 * @return true se e solo se 0<x<R e 0<y<C
	 */

	public boolean inBounds(int x, int y) {
		return y>= 0 && y<R && x>=0 && x<C;
	}

	/** Sostituisce il carattere di canvas in (x,y) con il nuovo carattere c in ingresso se
	 * (x,y) sta all'interno del canvas
	 * 
	 * @param x l'ascissa dell'elemento da modificare
	 * @param y l'ordinata dell'elemento da modificare
	 * @param c il carattere da mettere nella posizione (x,y)
	 */
	public void modifica(int x, int y, char c) {
		if( this.inBounds(x, y) )
			this.corrente.caratteri[y][x]= c;
	}

	/** Traccia un segmento di estremi (x1,y1) e (x2,y2) con il carattere c
	 * 
	 * @param x1 ascissa del primo estremo
	 * @param y1 ordinata del primo estremo
	 * @param x2 ascissa del secondo estremo
	 * @param y2 ordinata del secondo estremo
	 * @param c carattere con cui tracciare il segmento
	 */
	public void modifica(int x1, int y1, int x2, int y2, char c) {
		int l=Math.abs(x1-x2);
		int h=Math.abs(y1-y2);
		if (l>h) {
			int x0 = Math.min(x1,x2);
			int y0 = (x0 == x1 ? y1 : y2);
			double yPosition = (double) y0;
			double angCoeff = ((double) (y2-y1))/(x2-x1); // il coefficiente angolare del segmento
			for (int i=0; i<l; i++) {
				this.modifica(x0+i, (int) Math.round(yPosition), c);
				yPosition += angCoeff; // sottinteso che l'incremento in x sia uguale a 1
			}
			// accade che con determinati arrotondamenti non stampi uno dei due estremi. 
			// qui lo rifaccio nel caso in cui sia necessario
			this.modifica(x1, y1, c);
			this.modifica(x2, y2, c);
		}
		else {
			int y0 = Math.min(y1,y2);
			int x0 = (y0 == y1 ? x1 : x2);
			double xPosition = (double) x0;
			double invAngCoeff = ((double) (x2-x1))/(y2-y1); // il reciproco del coefficiente angolare del segmento
			for (int i=0; i<h; i++) {
				this.modifica((int) Math.round(xPosition), y0+i, c);
				xPosition += invAngCoeff; // sottinteso che l'incremento in x sia uguale a 1
			}
		}
	}
	
	/** Ripristina il canvas allo stato in cui si trovava prima delle ultime k modifiche.
	 * Ripristina il canvas vuoto se sono state fatte meno di k modifiche
	 * 
	 * @param k il numero di modifiche da annullare
	 */
	public void undo(int k) {
		for (int i=0; i<k; i++) {
			try {
				this.registroFrame.pop();
			} catch (EmptyStackException e) {
				break; // esce dal ciclo for se ho tolto tutti i frame
			}
		}
		
		if (this.registroFrame.empty()) { // nel caso in cui il registro sia rimasto vuoto
			this.registroFrame.push(new Frame(this.getR(), this.getC()));		
		}
		
		corrente = registroFrame.peek();	

	}
	
	/** Aggiunge in cima al registro una copia del frame attuale
	 * 
	 */
	public void addToHistory() {
		Frame copyOfCurrentFrame = new Frame(this.corrente); // fa una copia del frame corrente
		this.registroFrame.push(copyOfCurrentFrame); // registroFrame non &egrave; mai null perch&eacute; inizializzata sia dal costruttore,
											// sia da undo nel caso in cui rimanga vuota
		corrente = registroFrame.peek();
	}

	/** Restituisce un nuovo canvas, copia di quello che chiama il metodo
	 * Il registro del canvas restituito contiene solo il frame corrente e, sotto questo, il frame vuoto
	 * 
	 * @return
	 */
	public Canvas copia() {
		int c=this.getC();
		int r=this.getR();
		Canvas newCanvas = new Canvas(r,c);
		for(int i=0;i<c;i++) {
			for(int j=0;j<r;j++)
				newCanvas.modifica(i, j, this.car(i,j));
		}
		newCanvas.addToHistory();
		return newCanvas;
	}

	/** Restituisce un nuovo canvas, copia del rettangolo di estremi (x1,y1),(x2,y2) del canvas che invoca il metodo
	 * Il registro del canvas restituito contiene solo il frame corrente e, sotto questo, il frame vuoto
	 * 
	 * @param x1 numero di riga del primo punto
	 * @param y1 numero di colonna del primo punto
	 * @param x2 numero di riga del secondo punto
	 * @param y2 numero di colonna del secondo punto
	 * @return canvas di estremi (x1,y1),(x2,y2)
	 */
	public Canvas copia(int x1,int y1,int x2,int y2) {
		// ampiezza del rettangolo selezionato
		int c=Math.abs(x2-x1);
		int r=Math.abs(y2-y1);
		// estremo in basso a sinistra del rettangolo selezionato
		int x0=Math.min(x1, x2);
		int y0=Math.min(y1,y2);
		// corpo
		Canvas newCanvas = new Canvas(r,c);
		for (int i=0; i<c; i++) {
			for (int j=0; j<r; j++) {
				newCanvas.modifica(i, j, this.car(x0+i, y0+j));
			}
		}
		newCanvas.addToHistory();
		return newCanvas;
	}

	@Override
	public boolean equals(Object other) {
		if(other instanceof Canvas)
			return equals ((Canvas) other);
		else
			return false;
	}

	/** Restituisce true se i due canvas hanno le stesse dimensioni e lo stesso carattere in ogni posizione.
	 * Controlla soltanto l'ultimo frame nel registro (il frame corrente)
	 * 
	 * @param newCanvas un altro canvas
	 * @return true se i frame correnti dei due canvas sono uguali
	 */
	public boolean equals(Canvas newCanvas) {
		if(newCanvas==null)
			return false;

		if(! (this.R==newCanvas.R && this.C==newCanvas.C ) )
			return false;

		int i,j;
		for(i=0;i<this.getC();i++) {
			for(j=0;j<this.getR();j++)
				if( this.car(i,j) != newCanvas.car(i,j) )
					return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int sumEntries=0;
		for(int i=0;i<this.getC();i++) {
			for(int j=0;j<this.getR();j++)
				sumEntries+=this.car(i, j);
		}
		return sumEntries;
		
		/*
		 * &Egrave; verificato che se due canvas si equivalgono secondo equals, abbiano lo stesso hashCode
		 * Inoltre piccole modifiche al canvas modificano l'hashCode. &Egrave; molto improbabile che due canvas
		 * diversi abbiano lo stesso hashCode
		 */
	}

	@Override
	public String toString() {
		String stringa = "";
		for (int j=this.getR()-1; j>=0; j--) {
			for (int i=0; i<this.getC(); i++) {
				stringa += this.car(i, j);
			}
			stringa += "\n";
		}
		return stringa;
	}
	
	// CLASSE PRIVATA FRAME
	/** Un frame &egrave; una singola griglia presente nel registro di un canvas
	 * 
	 * 
	 *
	 */
	private class Frame {
		
		//ATTRIBUTI
		int righe;
		int colonne;
		public char[][] caratteri;
		
		//COSTRUTTORI
		/** Costruisce un frame vuoto di dimensioni date
		 * 
		 * @param righe il numero di righe del frame
		 * @param colonne il numero di colonne del frame
		 */
		public Frame(int righe, int colonne) {
			
			this.righe = righe;
			this.colonne = colonne;
			try{
				this.caratteri = new char[righe][colonne];
			} catch (NegativeArraySizeException e) {
				System.err.println("Errore: il canvas non pu&ograve; avere dimensioni negative. "
				 + "Dati inseriti: R=" + righe + ", C=" + colonne);
			}
			for(int i=0;i<righe;i++) {
				for(int j=0;j<colonne;j++) {
					caratteri[i][j]=' ';
				}
			}
		}
		
		/** Costruisce un nuovo frame identico a un altro
		 * 
		 * @param altro un altro frame
		 */
		public Frame(Frame altro) {
			this.righe = altro.righe;
			this.colonne = altro.colonne;
			try{
				this.caratteri = new char[righe][colonne];
			} catch (NegativeArraySizeException e) {
				System.err.println("Errore: il canvas non può avere dimensioni negative. "
				 + "Dati inseriti: R=" + righe + ", C=" + colonne);
			}
			for(int i=0;i<righe;i++) {
				for(int j=0;j<colonne;j++) {
					this.caratteri[i][j]=altro.caratteri[i][j];
				}
			}
		}
	}


}
