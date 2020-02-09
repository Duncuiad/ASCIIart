public class FlipVert extends StrumentoDiSelezione {
	
  //COSTRUTTORI
  public FlipVert(Canvas canvas) {
	  super(canvas);
  }

  //METODI

  public void azione() {	  
  int l = Math.abs(pIniziale.posx() - pFinale.posx());
  int h = Math.abs(pIniziale.posy() - pFinale.posy());
  
  int x0 = Math.min(pIniziale.posx(), pFinale.posx()); 
  int y0 = (int) Math.round( h / 2.0);
  
  char c;
  
  for(int i=0; i<l; i++) {
	  for(int j=0; j<y0; j++) {
		  c =canvas.car(x0 + i, y0 + j);
		  canvas.modifica(x0 + i, y0 + j, canvas.car(x0 + i,y0 - j));
		  canvas.modifica(x0 + i, y0 - j, c);
		  }
	  }
  }
}


