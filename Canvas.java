public class Canvas {
	
	private int R;
	private int C;
	private char[][] caratteri;
	
	public Canvas(int righe, int colonne) {
		this.R=righe;
		this.C=colonne;
		this.caratteri = new char[C][R];
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
	
	public boolean vuota(int x, int y) {
		return this.caratteri[x][y]==' ';
	}
	
	public boolean modifica(int x, int y, char c) {
		if( !(x<C && x>0 && y<R && y>0))
			return false; //Non posso modificare
		else {
		this.caratteri[x][y]='c';
		return true;
		}
	}
	
	public void modifica(int x1, int y1, int x2, int y2, char c) {
		int l=Math.abs(x1-x2);
		int h=Math.abs(y1-y2);
		
	}
	
}
