public class FlipHoriz extends StrumentoDiSelezione {
	
  //COSTRUTTORI
  public FlipHoriz(Canvas canvas) {
	  super(canvas);
  }

  //METODI

  public void azione() {
	  int l = Math.abs(pIniziale.posx() - pFinale.posx());
	  int h = Math.abs(pIniziale.posy() - pFinale.posy());
	  
	  int x0 = (int) Math.round( l / 2.0);
	  int y0 = Math.min(pIniziale.posy(), pFinale.posy());
	  
	  char c;
	  
	  for(int i=0; i<x0; i++) {
		  for(int j=0; j<h; j++) {
			  c=canvas.car(x0+i, y0+j);
			  canvas.modifica(x0+i, y0+j, canvas.car(x0 - i, y0 + j));
			  canvas.modifica(x0-i, y0+j, c);
			  }
		  }
	  canvas.modifica(x0,y0 , 'p');
	  }
  }
