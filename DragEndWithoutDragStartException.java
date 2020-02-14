/**	Lanciata se un DragEnd non avviene dopo un DragStart seguito solo da MouseMove
 * 
 * 
 *
 */
public class DragEndWithoutDragStartException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DragEndWithoutDragStartException() {
		//super();
	}

	public DragEndWithoutDragStartException(String message) {
		super(message);
	}

	public DragEndWithoutDragStartException(Throwable cause) {
		super(cause);
	}

	public DragEndWithoutDragStartException(String message, Throwable cause) {
		super(message, cause);
	}

	public DragEndWithoutDragStartException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
