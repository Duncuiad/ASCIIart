public class DisegnoAManoLibera extends StrumentoDiDisegnoLibero {
	
	//COSTRUTTORI
	public DisegnoAManoLibera (Canvas canvas) {
		super(canvas);
	}
	
	//METODI
	protected void disegna() {
		canvas.modifica(move.posx(), move.posy() , super.getTratto());
	}
}