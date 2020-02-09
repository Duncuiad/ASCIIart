public class Cerchio extends StrumentoDiDisegnoCentro {



  //COSTRUTTORI
  public Cerchio(Canvas canvas) {
	  super(canvas);
  }

  protected void disegna() {
	  
	  int deltaXSquared = (centro.posx()-altro.posx());
	  deltaXSquared *= deltaXSquared;
	  int deltaYSquared = (centro.posy()-altro.posy());
	  deltaYSquared *= deltaYSquared;
	  int distanza = (int) Math.round( Math.sqrt( (double) (deltaXSquared + deltaYSquared) ) );
	  
	  // per ogni quadrante, stampo un quarto di circonferenza
	  for (int signX = 1; signX >= -1; signX -= 2) { //fa solo i casi +1 e -1
		  for (int signY = 1; signY >= -1; signY -= 2) { //come sopra, per la selezione del quadrante
			  
			  //per non avere buchi, ripasso il quarto di circonferenza due volte
			  //una in orizzontale, una in verticale
			  for (int i = 0; i <= distanza; i++) { //disegna un pixel per colonna
				  int yIncrement = (int) Math.round(Math.sqrt((double) (distanza*distanza - i*i)));
				  int currentX = centro.posx() + (signX*i);
				  int currentY = centro.posy() + signY*yIncrement;
				  canvas.modifica(currentX, currentY, super.getTratto());
			  }
			  for (int i = 0; i <= distanza; i++) { //disegna un pixel per riga
				  int xIncrement = (int) Math.round(Math.sqrt((double) (distanza*distanza - i*i)));
				  int currentY = centro.posy() + (signY*i);
				  int currentX = centro.posx() + signX*xIncrement;
				  canvas.modifica(currentX, currentY, super.getTratto());
			  }
		  }
		  
	  }
	  
  }
  
  
}
