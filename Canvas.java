import java.util.*;
public class Canvas {

	private int R;
	private int C;
	private char[][] caratteri;
	private Stack<Canvas> pila;

	public Canvas(int righe, int colonne) {
		this.R=righe;
		this.C=colonne;
		this.caratteri = new char[R][C];
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++)
				caratteri[i][j]=' ';
		}
		this.pila= new Stack<Canvas>();
	}


	public int getR() {
		return this.R;
	}

	public int getC() {
		return this.C;
	}

	public Stack<Canvas> getStack() {
		return this.pila;
	}
	/**
	 *
	 * @param x
	 * @param y
	 * @return il carattere in posizione (x,y)
	 */


	public char car(int x, int y) {
		return this.caratteri[x][y];
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

	public boolean possoModifica(int x, int y) {
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
		if( this.possoModifica(x, y) )
			this.caratteri[x][y]= c;
	}

	public void modifica(int x1, int y1, int x2, int y2, char c) {
		int l=Math.abs(x1-x2);
		int h=Math.abs(y1-y2);
		int i,j=Math.min(y1, y2);
		int passoRighe=0;//bisogna capire come approssimare i passi
		int passoColonne=0;
		for(i=Math.min(x1, x2);i<l;i+=passoRighe) {
			if( this.possoModifica(i,j) )
				this.modifica(i, j, c);
			j+=passoColonne;
		}
	}

	public void undo(int k) {
		Canvas buffer = this.copia();
		for (int i=0; i<k; i++) {
			try {
				buffer=this.pila.pop();
			} catch(EmptyStackException e) {
				// nulla
				break; // esce dal for
			}
		}
		for (int i=0; i<this.R; i++) {
			for (int j=0; j<this.C; j++) {
				this.modifica(i, j, buffer.car(i, j)); //deep copy
			}
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
