
public abstract class StrumentoDiDisegnoLibero extends StrumentoDiDisegno {
	
	//ATTRIBUTI
	/**true se il disegno a mano libera e' in modalita' attiva*/
	private boolean stato = false;
	
	/**click di mouse che puo' far cambiare lo stato se e' semplice e corrispondente
	 * al tasto sinistro*/
	private MouseClick click = null;
	
	protected MouseMove move = null;
	
	//COSTRUTTORI
	public StrumentoDiDisegnoLibero(Canvas canvas) {
		super(canvas);
	}

	//METODI
	public void ricevi(EventoDiMouse e) {
		if(e instanceof MouseClick) {
			if(!stato) {
				canvas.addToHistory();
			}
			click = (MouseClick) e;
			if(!click.rightClick() && !click.doubleClick())
			stato=!stato;
	}
		if(stato) {
			if(e instanceof MouseMove) {
				move = (MouseMove) e;
				disegna();
			}
		}

	}
	
	protected abstract void disegna();
	
	@Override
	public boolean equals(Object altro) {
		if (altro instanceof StrumentoDiDisegnoLibero)
			return true;
		else
	    	return false;
	    
	}
	
}
