public class Canvas {
	
	private int R;
	private int C;
	private char[][] caratteri;
	
	public Canvas(int righe, int colonne) {
		this.R=righe;
		this.C=colonne;
		this.caratteri = new char[R][C];
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++)
				caratteri[i][j]=' ';
		}
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
		return x<R && x>0 && y<C && y>0;
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
}