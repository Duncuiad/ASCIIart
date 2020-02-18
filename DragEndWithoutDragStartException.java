/**	Lanciata se un DragEnd non avviene dopo un DragStart seguito solo da MouseMove
 * 
 * 
 *
 */
public class DragEndWithoutDragStartException extends RuntimeException {
	
	/** @see Serializable */
	private static final long serialVersionUID = 1L;

	/** @see RuntimeException#RuntimeException()
	 * 
	 */
	public DragEndWithoutDragStartException() {
		//super();
	}

	/** @see RuntimeException#RuntimeException(String)
	 * @param message
	 */
	public DragEndWithoutDragStartException(String message) {
		super(message);
	}

	/** @see RuntimeException#RuntimeException(Throwable)
	 * @param cause
	 */
	public DragEndWithoutDragStartException(Throwable cause) {
		super(cause);
	}

	/** @see RuntimeException#RuntimeException(String, Throwable)
	 * @param message
	 * @param cause
	 */
	public DragEndWithoutDragStartException(String message, Throwable cause) {
		super(message, cause);
	}

	/** @see RuntimeException#RuntimeException(String, Throwable, boolean, boolean)
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DragEndWithoutDragStartException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
