package goodgames.common.validator;

public class FieldValidator {

	public static void validateNotNull(Object object, RuntimeException exception) {
		if (object == null) {
			throw exception;
		}

	}

}
