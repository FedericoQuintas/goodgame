package goodgames.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OrderCreationException extends RuntimeException {

	private static final long serialVersionUID = 7069612824573237570L;

	public OrderCreationException(String message) {
		super(message);

	}

}
