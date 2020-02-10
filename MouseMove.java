/** Descrive i movimenti del mouse
 * 
 * 
 *
 */
public class MouseMove extends EventoDiMouse {

	//COSTRUTTORI
	
	/**@see EventoDiMouse#EventoDiMouse(int, int)
	 */
	public MouseMove(int x, int y) {
		super(x,y);
		//lascia invariato EventoDiMouse.dragging
	}
	
	//METODI
	
	@Override
	public String toString() {
		return super.toString() + ", di tipo MouseMove";
	}

}
