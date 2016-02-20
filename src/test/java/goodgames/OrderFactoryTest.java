package goodgames;

import static org.junit.Assert.fail;
import goodgames.order.domain.Order;
import goodgames.order.domain.builder.OrderBuilder;
import goodgames.order.exception.OrderBuilderException;
import goodgames.store.domain.CoffeeType;
import goodgames.store.domain.PaymentType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderFactoryTest {

	private OrderBuilder orderBuilder;
	private static final String PAYMENT_TYPE_FIELD_CANNOT_BE_NULL = "Payment type field cannot be null";
	private static final String COFFEE_TYPE_FIELD_CANNOT_BE_NULL = "Coffee type field cannot be null";

	@Before
	public void before() {
		orderBuilder = new OrderBuilder();
	}

	@Test
	public void whenCreatesAnOrderThenOrderHasTotalAmmountOfTimeInZero() {

		Order order = orderBuilder.withCoffeeType(CoffeeType.ESPRESSO)
				.withPaymentType(PaymentType.CARD).build();

		Assert.assertEquals(new Double(0), order.getTotalAmmountOfTime());

	}

	@Test
	public void whenCreatesAnOrderThenOrderHasAProgrammer() {

		Order order = orderBuilder.withCoffeeType(CoffeeType.ESPRESSO)
				.withPaymentType(PaymentType.CARD).build();

		Assert.assertNotNull(order.getCoffeeType());

	}

	@Test
	public void whenCreatesAnOrderWithCashThenOrderHasAPaymentType() {

		Order order = orderBuilder.withCoffeeType(CoffeeType.ESPRESSO)
				.withPaymentType(PaymentType.CASH).build();

		Assert.assertEquals(PaymentType.CASH, order.getPaymentType());

	}

	@Test
	public void whenCreatesAnOrderWithCreditCardThenOrderHasAPaymentType() {

		Order order = orderBuilder.withCoffeeType(CoffeeType.ESPRESSO)
				.withPaymentType(PaymentType.CARD).build();

		Assert.assertEquals(PaymentType.CARD, order.getPaymentType());

	}

	@Test
	public void whenCreatesAProgrammerWithEspressoTypeThenOrderHasAProgrammerWithFavouriteCoffeeType() {

		Order order = orderBuilder.withCoffeeType(CoffeeType.ESPRESSO)
				.withPaymentType(PaymentType.CARD).build();

		Assert.assertEquals(CoffeeType.ESPRESSO, order.getCoffeeType());

	}

	@Test
	public void whenCreatesAProgrammerWithLatteTypeThenOrderHasAProgrammerWithFavouriteCoffeeType() {

		Order order = orderBuilder.withCoffeeType(CoffeeType.LATTE)
				.withPaymentType(PaymentType.CARD).build();

		Assert.assertEquals(CoffeeType.LATTE, order.getCoffeeType());

	}

	@Test
	public void whenCreatesAProgrammerWithCapuccinoTypeThenOrderHasAProgrammerWithFavouriteCoffeeType() {

		Order order = orderBuilder.withCoffeeType(CoffeeType.CAPUCCINO)
				.withPaymentType(PaymentType.CARD).build();

		Assert.assertEquals(CoffeeType.CAPUCCINO, order.getCoffeeType());

	}

	@Test
	public void whenCreatesAnOrderWithNullProgrammerThenExceptionIsThrown() {

		try {
			orderBuilder.withCoffeeType(null).build();
			fail();
		} catch (OrderBuilderException exception) {
			Assert.assertEquals(exception.getMessage(),
					COFFEE_TYPE_FIELD_CANNOT_BE_NULL);
		}

	}

	@Test
	public void whenCreatesAnOrderWithNullPaymentTypeThenExceptionIsThrown() {

		try {
			orderBuilder.withCoffeeType(CoffeeType.CAPUCCINO)
					.withPaymentType(null).build();
			fail();
		} catch (OrderBuilderException exception) {
			Assert.assertEquals(exception.getMessage(),
					PAYMENT_TYPE_FIELD_CANNOT_BE_NULL);
		}

	}

}
