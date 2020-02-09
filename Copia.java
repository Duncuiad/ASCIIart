public class Copia extends StrumentoDiSelezione {

  //COSTRUTTORI
  public Copia(Canvas canvas) {
	  super(canvas);
  }

  //METODI
  
  public void azione() {
	  int x0 = Math.max(pIniziale.posx(), pFinale.posx());
	  int y0 = Math.min(pIniziale.posy(), pFinale.posy());
	  int h = Math.abs(pIniziale.posx() - pFinale.posx());
	  int l = Math.abs(pIniziale.posy() - pFinale.posy());
	  
	  for (int i = 0; i < h; i++) {
		  for (int j = 0; j < l ; j++) {
			  canvas.modifica(actionPoint.posx() - i, actionPoint.posy() + j, canvas.car(x0 - i,y0 + j));
		  }
	  }

  }

}
