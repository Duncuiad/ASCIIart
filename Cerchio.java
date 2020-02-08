public class Cerchio extends StrumentoDiDisegno {

  //ATTRIBUTI
  /** Primo evento da registrare */
  private MouseClick primoClick;
  /** Secondo evento da registrare */
  private MouseClick secondoClick;
  /** il carattere presente in precedenza nel centro */
  char precedente;

  //COSTRUTTORI
  public Cerchio(Canvas canvas) {
	  super(canvas);
  }


  @Override
  public void ricevi(EventoDiMouse e) {

	  if (e instanceof MouseClick) {

		  if (primoClick == null) { // primo click

			  primoClick = (MouseClick) e;

			  if ( !(primoClick.rightClick()) ) { // se click sinistro
				  canvas.addToHistory();
				  precedente = canvas.car(primoClick.posx(), primoClick.posy());
				  canvas.modifica(primoClick.posx(), primoClick.posy(), '*'); // mette l'evidenziatore al centro
			  } // se click destro, non fa nulla

		  } else { // secondo click

			  secondoClick = (MouseClick) e;

			  if ( !(secondoClick.rightClick()) ) { // se click sinistro

				  int deltaXSquared = (primoClick.posx()-secondoClick.posx());
				  deltaXSquared *= deltaXSquared;
				  int deltaYSquared = (primoClick.posy()-secondoClick.posy());
				  deltaYSquared *= deltaYSquared;
				  int distanza = (int) Math.round( Math.sqrt( (double) (deltaXSquared + deltaYSquared) ) );
				  
				  // per ogni quadrante, stampo un quarto di circonferenza
				  for (int signX = 1; signX >= -1; signX -= 2) { //fa solo i casi +1 e -1
					  for (int signY = 1; signY >= -1; signY -= 2) { //come sopra, per la selezione del quadrante
						  
						  //per non avere buchi, ripasso il quarto di circonferenza due volte
						  //una in orizzontale, una in verticale
						  for (int i = 0; i <= distanza; i++) { //disegna un pixel per colonna
							  int yIncrement = (int) Math.round(Math.sqrt((double) (distanza*distanza - i*i)));
							  int currentX = primoClick.posx() + (signX*i);
							  int currentY = primoClick.posy() + signY*yIncrement;
							  canvas.modifica(currentX, currentY, super.getTratto());
						  }
						  for (int i = 0; i <= distanza; i++) { //disegna un pixel per riga
							  int xIncrement = (int) Math.round(Math.sqrt((double) (distanza*distanza - i*i)));
							  int currentY = primoClick.posy() + (signY*i);
							  int currentX = primoClick.posx() + signX*xIncrement;
							  canvas.modifica(currentX, currentY, super.getTratto());
						  }
					  }
					  
				  }

			  }

			  // fase di cancellatura
			  // avviene anche se il secondo click &egrave; destro (come voluto)
			  reset();
		  }
	  }
  }
  
  protected void reset() {
	  try{
		  canvas.modifica(primoClick.posx(), primoClick.posy(), precedente); // toglie l'evidenziatore
	  } catch (NullPointerException e) {
		  // non c'&egrave ancora un canvas, non fare nulla
	  }

	  primoClick = null;
	  secondoClick = null;
  }

  @Override
  public boolean equals(Object altro) {
    if (altro instanceof Cerchio) {
      return true;
    } else {
      return false;
    }
  } // hashCode overridden in classe Strumento
}
