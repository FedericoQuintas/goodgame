package goodgames;

import goodgames.order.exception.OrderCreationException;
import goodgames.order.service.OrderServiceImpl;

import org.junit.Assert;
import org.junit.Test;

public class OrderServiceTest {
	private static final String NUMBER_OF_LATTE_ORDERS_CANNOT_BE_NULL = "Number of Latte Orders cannot be null";
	private static final String NUMBER_OF_CAPUCCINO_ORDERS_CANNOT_BE_NULL = "Number of Capuccino Orders cannot be null";
	private static final String NUMBER_OF_ESPRESSO_ORDERS_CANNOT_BE_NULL = "Number of Espresso Orders cannot be null";

	@Test
	public void whenNumberOfEspressoIsNullThenExceptionIsThrown() {
		try {
			new OrderServiceImpl().generateOrders(null, 1, 1);
		} catch (OrderCreationException exception) {
			Assert.assertEquals(NUMBER_OF_ESPRESSO_ORDERS_CANNOT_BE_NULL,
					exception.getMessage());
		}
	}

	@Test
	public void whenNumberOfLatteIsNullThenExceptionIsThrown() {
		try {
			new OrderServiceImpl().generateOrders(1, null, 1);
		} catch (OrderCreationException exception) {
			Assert.assertEquals(NUMBER_OF_LATTE_ORDERS_CANNOT_BE_NULL,
					exception.getMessage());
		}
	}

	@Test
	public void whenNumberOfCapuccinoIsNullThenExceptionIsThrown() {
		try {
			new OrderServiceImpl().generateOrders(1, 1, null);
		} catch (OrderCreationException exception) {
			Assert.assertEquals(NUMBER_OF_CAPUCCINO_ORDERS_CANNOT_BE_NULL,
					exception.getMessage());
		}
	}
}
