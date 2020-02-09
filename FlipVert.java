public class FlipVert extends StrumentoDiSelezione {
	
  //COSTRUTTORI
  public FlipVert(Canvas canvas) {
	  super(canvas);
  }

  //METODI

  public void azione() {	  
  int h = Math.abs(pIniziale.posx() - pFinale.posx());
  int l = Math.abs(pIniziale.posy() - pIniziale.posy());
  
  int x0 = (int) Math.round( h /2.0);
  int y0 = Math.min(pIniziale.posy(), pFinale.posy());
  
  char c;
  
  for(int i=0; i<x0; i++) {
	  for(int j=0; j<l; j++) {
		  c =canvas.car(x0+i, y0+j);
		  canvas.modifica(x0+i, y0+j, canvas.car(x0 - i, y0 + j));
		  canvas.modifica(x0 - i, y0 + j, c);
		  }
	  }
  }
}


