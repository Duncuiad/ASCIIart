public class FlipHoriz extends StrumentoDiSelezione {
	
  //COSTRUTTORI
  public FlipHoriz(Canvas canvas) {
	  super(canvas);
  }

  //METODI

  public void azione() {
	  int h = Math.abs(pIniziale.posx() - pFinale.posx());
	  int l = Math.abs(pIniziale.posy() - pIniziale.posy());
	  
	  int x0 = Math.min(pIniziale.posx(), pFinale.posx());
	  int y0 = (int) Math.round(l / 2.0);
	  
	  char c;
	  
	  for(int i=0; i<h; i++) {
		  for(int j=0; j<y0; j++) {
			  c=canvas.car(x0+i, y0+j);
			  canvas.modifica(x0+i, y0+j, canvas.car(x0 + i, y0 - j));
			  canvas.modifica(x0+i, y0-j, c);
			  }
		  }
	  }
  }
