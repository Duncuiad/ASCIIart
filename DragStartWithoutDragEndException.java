/** Lanciata se a un DragStart non seguono solo MouseMove e infine un DragEnd
 * 
 *
 *
 */
public class DragStartWithoutDragEndException extends RuntimeException {
	
	/** @see Serializable */
	private static final long serialVersionUID = 1L;

	/** @see RuntimeException#RuntimeException()
	 * 
	 */
	public DragStartWithoutDragEndException() {
		super();
	}

	/** @see RuntimeException#RuntimeException(String)
	 * @param message
	 */
	public DragStartWithoutDragEndException(String message) {
		super(message);
	}

	/** @see RuntimeException#RuntimeException(Throwable)
	 * @param cause
	 */
	public DragStartWithoutDragEndException(Throwable cause) {
		super(cause);
	}

	/** @see RuntimeException#RuntimeException(String, Throwable)
	 * @param message
	 * @param cause
	 */
	public DragStartWithoutDragEndException(String message, Throwable cause) {
		super(message, cause);
	}

	/** @see RuntimeException#RuntimeException(String, Throwable, boolean, boolean)
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DragStartWithoutDragEndException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
