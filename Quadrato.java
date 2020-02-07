public class Quadrato extends StrumentoDiDisegno {
		
  //ATTRIBUTI
  DragStart pIniziale;
  DragEnd pFinale;

  //COSTRUTTORI
  public Quadrato(Canvas canvas) {
	  super(canvas);
  }

  //METODI
  @Override
  public void ricevi(EventoDiMouse e) {

    // caso DragStart
    if (e instanceof DragStart) {
    	this.pIniziale = (DragStart) e;
    	this.canvas.addToHistory();
    }

    // caso DragEnd
    if (e instanceof DragEnd) {
      if (!(this.pIniziale.right())) {
    	  this.pFinale = (DragEnd) e;
    	  canvas.modifica(pIniziale.posx(), pIniziale.posy(), pFinale.posx(), pIniziale.posy(), super.getTratto()); // lato orizzontale 1
    	  canvas.modifica(pIniziale.posx(), pFinale.posy(), pFinale.posx(), pFinale.posy(), super.getTratto()); // lato orizzontale 2
    	  canvas.modifica(pIniziale.posx(), pIniziale.posy(), pIniziale.posx(), pFinale.posy(), super.getTratto()); // lato verticale 1
    	  canvas.modifica(pFinale.posx(), pIniziale.posy(), pFinale.posx(), pFinale.posy(), super.getTratto()); // lato verticale 2
        // &egrave; ben posto: DragEnd segue sempre un DragStart, dunque pIniziale non è null
      }
      
      // torna allo stato neutro
      this.pIniziale = null;
      this.pFinale = null;
    }
  }


}
