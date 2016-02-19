package goodgames.order.exception;

public class EmptyOrderListException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmptyOrderListException(String message) {
		super(message);
	}

}
