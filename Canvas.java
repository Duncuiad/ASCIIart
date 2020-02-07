import java.util.*;
public class Canvas {

	private int R;
	private int C;
	private char[][] caratteri;
	private Stack<Canvas> pila;

	public Canvas(int righe, int colonne) {
		this.R=righe;
		this.C=colonne;
		try{
			this.caratteri = new char[R][C];
		} catch (NegativeArraySizeException e) {
			System.err.println("Errore: il canvas non può avere dimensioni negative. "
			 + "Dati inseriti: R=" + righe + ", C=" + colonne);
		}
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				caratteri[i][j]=' ';
			}
		}
		this.pila = null;
	}

	public Canvas(int righe, int colonne, Stack<Canvas> pila) {
		this(righe, colonne);
		this.linkPila(pila);
	}

	private void linkPila(Stack<Canvas> pila) {
		this.pila = pila;
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
		try {return this.caratteri[x][y];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Errore in canvas " + this.R + "x" + this.C +
			": non posso accedere al carattere in posizione " + x + ", " + y
			+ " (Out of bounds)");
		}
	}

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
		return x>= 0 && x<R && y>=0 && y<R;
	}

	/**
	 * sostituisce il carattere di canvas conil nuovo carattere c in ingresso se
	 * le dimensioni sono rispettate
	 * @param x il numero di riga da modificare
	 * @param y il numero di colonna da modificare
	 * @param c il carattere da mettere nella posizione (x,y)
	 */
	public void modifica(int x, int y, char c) {
		if( this.inBounds(x, y) )
			this.caratteri[x][y]= c;
	}

	public void modifica(int x1, int y1, int x2, int y2, char c) {
		int l=Math.abs(x1-x2);
		int h=Math.abs(y1-y2);
		if (l>h) {
			int x0 = Math.min(x1,x2);
			int y0 = (x0 == x1 ? y1 : y2);
			double yPosition = (double) y0;
			double angCoeff = ((double) (y2-y1))/(x2-x1); // il coefficiente angolare del segmento
			for (int i=0; i<l; i++) {
				this.modifica(x0+i, (int) yPosition, c);
				yPosition += angCoeff; // sottinteso che l'incremento in x sia uguale a 1
			}
		}
		else {
			int y0 = Math.min(y1,y2);
			int x0 = (y0 == y1 ? x1 : x2);
			double xPosition = (double) x0;
			double invAngCoeff = ((double) (x2-x1))/(y2-y1); // il reciproco del coefficiente angolare del segmento
			for (int i=0; i<h; i++) {
				this.modifica((int) xPosition, y0+i, c);
				xPosition += invAngCoeff; // sottinteso che l'incremento in x sia uguale a 1
			}
		}
	}

	public void undo(int k) {
		Canvas buffer = this.copia();
		for (int i=0; i<k; i++) {
			try {
				buffer=this.pila.pop();
			} catch (EmptyStackException e) {
				// nulla
				break; // esce dal for
			} catch (NullPointerException e) { // se non si è collegata una pila al canvas
				System.err.println("Errore in canvas " + this.R + "x"
				+ this.C + ": non ho ancora collegato la storia del canvas");
			}
		}
		for (int i=0; i<this.R; i++) {
			for (int j=0; j<this.C; j++) {
				this.modifica(i, j, buffer.car(i, j)); //deep copy
			}
		}
	}

	public void addToHistory() {
		Canvas copyOfThisCanvas = this.copia();
		copyOfThisCanvas.linkPila(this.pila);
		try {
			this.pila.push(copyOfThisCanvas);
		} catch (NullPointerException e) {
			System.err.println("Errore in canvas " + this.R + "x" + this.C
			+ ": non ho ancora definito a quale storia collegare questo canvas");
		}
	}

		public Canvas copia() {
			int r=this.getR();
			int c=this.getC();
			Canvas newCanvas = new Canvas(r,c);
			for(int i=0;i<r;i++) {
				for(int j=0;j<c;j++)
					newCanvas.modifica(i, j, this.car(i,j));
			}
			return newCanvas;
		}

		/**
		 * restituisce un nuovo canvas, copia del rettangolo di estremi (x1,y1),(x2,y2)
		 * del canvas su cui e' incocato
		 * @param x1 numero di riga del primo punto
		 * @param y1 numero di colonna del primo punto
		 * @param x2 numero di riga del secondo punto
		 * @param y2 numero di colonna del secondo punto
		 * @return canvas di estremi (x1,y1),(x2,y2)
		 */

		public Canvas copia(int x1,int y1,int x2,int y2) {
			// ampiezza del rettangolo selezionato
			int r=Math.abs(x2-x1);
			int c=Math.abs(y2-y1);
			// estremo in basso a sinistra del rettangolo selezionato
			int x0=Math.min(x1, x2);
			int y0=Math.min(y1,y2);
			// corpo
			Canvas newCanvas = new Canvas(r,c);
			for (int i=0; i<r; i++) {
				for (int j=0; j<c; j++) {
					newCanvas.modifica(i, j, this.car(x0+i, y0+j));
				}
			}
			return newCanvas;
		}
		
		@Override 
		public int hashCode() {
			int sumEntries=0;
			for(int i=0;i<this.R;i++) {
				for(int j=0;j<this.C;j++)
					sumEntries+=this.car(i, j);
			}
			return sumEntries;
		}

		@Override
		public boolean equals(Object other) {
			if(other instanceof Canvas)
				return equals ((Canvas) other);
			else
				return false;
		}

		public boolean equals(Canvas newCanvas) {
			if(newCanvas==null)
				return false;

			if(! (this.R==newCanvas.R && this.C==newCanvas.C ) )
				return false;

			int i,j;
			for(i=0;i<this.R;i++) {
				for(j=0;j<this.C;j++)
					if( this.car(i,j) != newCanvas(i,j) )
						break;
			}
			return i==this.R && j==this.C;
		}

		@Override
		public String toString() {
			String stringa = "";
			for (int i=0; i<this.R; i++) {
				for (int j=0; j<C; j++) {
					stringa += this.car(i, j);
				}
				stringa += "\n";
			}
			return stringa;
		}


}
