public class DisegnoAManoLibera extends StrumentoDiDisegno {
	
	//ATTRIBUTI
	/**true se disegno a mano libera e' in modalita' attiva*/
	private boolean stato = false;
	
	/**click di mouse che puo' far cambiare lo stato se e' semplice e corrispondente
	 * al tasto sinistro*/
	private MouseClick click = null;
	
	private MouseMove move =null;
	
	//COSTRUTTORI
	public DisegnoAManoLibera(Canvas canvas) {
		super(canvas);
	}

	//METODI
	public void ricevi(EventoDiMouse e) {
		if(e instanceof MouseClick) {
			click = (MouseClick) e;
			if(!click.rightClick() && !click.doubleClick())
				stato = (stato==true) ? true : false;
	}
		if(stato) {
			if(e instanceof MouseMove) {
				move = (MouseMove) e;
				canvas.modifica(move.posx(), move.posy() , super.getTratto());
			}
		}

	}
	@Override
	public boolean equals(Object altro) {
		if (altro instanceof DisegnoAManoLibera)
			return true;
		else
	    	return false;
	    
	}
	
}