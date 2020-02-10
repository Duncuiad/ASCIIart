public class Copia extends StrumentoDiSelezione {

  //COSTRUTTORI
  public Copia(Canvas canvas) {
	  super(canvas);
  }

  //METODI
  
  public void azione() {
	  
	  int l = Math.abs(pIniziale.posx() - pFinale.posx());
	  int h = Math.abs(pIniziale.posy() - pFinale.posy());
	  
	  for (int i = 0; i < l; i++) {
		  for (int j = 0; j < h ; j++) {
			  canvas.modifica(actionPoint.posx() + i, actionPoint.posy() - j, areaSelezionata.car(i,h-j-1));
		  }
	  }

  }

}
