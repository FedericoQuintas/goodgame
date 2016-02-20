package goodgames;

import static org.junit.Assert.fail;
import goodgames.order.domain.Order;
import goodgames.order.domain.Programmer;
import goodgames.order.domain.builder.OrderBuilder;
import goodgames.order.domain.builder.ProgrammerBuilder;
import goodgames.order.exception.OrderBuilderException;
import goodgames.store.domain.CoffeeType;
import goodgames.store.domain.PaymentType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderFactoryTest {

	private OrderBuilder orderBuilder;
	private static final String PAYMENT_TYPE_FIELD_CANNOT_BE_NULL = "Payment type field cannot be null";
	private static final String PROGRAMMER_FIELD_CANNOT_BE_NULL = "Programmer field cannot be null";

	@Before
	public void before() {
		orderBuilder = new OrderBuilder();
	}

	@Test
	public void whenCreatesAnOrderThenOrderHasTotalAmmountOfTimeInZero() {

		Programmer programmer = buildProgrammer(CoffeeType.ESPRESSO);

		Order order = orderBuilder.withProgrammer(programmer)
				.withPaymentType(PaymentType.CARD).build();

		Assert.assertEquals(new Double(0), order.getTotalAmmountOfTime());

	}

	@Test
	public void whenCreatesAnOrderThenOrderHasAProgrammer() {

		Programmer programmer = buildProgrammer(CoffeeType.ESPRESSO);

		Order order = orderBuilder.withProgrammer(programmer)
				.withPaymentType(PaymentType.CARD).build();

		Assert.assertNotNull(order.getProgrammer());

	}

	@Test
	public void whenCreatesAnOrderWithCashThenOrderHasAPaymentType() {

		Programmer programmer = buildProgrammer(CoffeeType.ESPRESSO);

		Order order = orderBuilder.withProgrammer(programmer)
				.withPaymentType(PaymentType.CASH).build();

		Assert.assertEquals(PaymentType.CASH, order.getPaymentType());

	}

	@Test
	public void whenCreatesAnOrderWithCreditCardThenOrderHasAPaymentType() {

		Programmer programmer = buildProgrammer(CoffeeType.ESPRESSO);

		Order order = orderBuilder.withProgrammer(programmer)
				.withPaymentType(PaymentType.CARD).build();

		Assert.assertEquals(PaymentType.CARD, order.getPaymentType());

	}

	@Test
	public void whenCreatesAProgrammerWithEspressoTypeThenOrderHasAProgrammerWithFavouriteCoffeeType() {

		Programmer programmer = buildProgrammer(CoffeeType.ESPRESSO);

		Order order = orderBuilder.withProgrammer(programmer)
				.withPaymentType(PaymentType.CARD).build();

		Assert.assertEquals(CoffeeType.ESPRESSO, order.getProgrammer()
				.getCoffeeType());

	}

	@Test
	public void whenCreatesAProgrammerWithLatteTypeThenOrderHasAProgrammerWithFavouriteCoffeeType() {

		Programmer programmer = buildProgrammer(CoffeeType.LATTE);

		Order order = orderBuilder.withProgrammer(programmer)
				.withPaymentType(PaymentType.CARD).build();

		Assert.assertEquals(CoffeeType.LATTE, order.getProgrammer()
				.getCoffeeType());

	}

	@Test
	public void whenCreatesAProgrammerWithCapuccinoTypeThenOrderHasAProgrammerWithFavouriteCoffeeType() {

		Programmer programmer = buildProgrammer(CoffeeType.CAPUCCINO);

		Order order = orderBuilder.withProgrammer(programmer)
				.withPaymentType(PaymentType.CARD).build();

		Assert.assertEquals(CoffeeType.CAPUCCINO, order.getProgrammer()
				.getCoffeeType());

	}

	@Test
	public void whenCreatesAnOrderWithNullProgrammerThenExceptionIsThrown() {

		Programmer programmer = null;
		try {
			orderBuilder.withProgrammer(programmer).build();
			fail();
		} catch (OrderBuilderException exception) {
			Assert.assertEquals(exception.getMessage(),
					PROGRAMMER_FIELD_CANNOT_BE_NULL);
		}

	}

	@Test
	public void whenCreatesAnOrderWithNullPaymentTypeThenExceptionIsThrown() {

		Programmer programmer = buildProgrammer(CoffeeType.CAPUCCINO);
		try {
			orderBuilder.withProgrammer(programmer).withPaymentType(null)
					.build();
			fail();
		} catch (OrderBuilderException exception) {
			Assert.assertEquals(exception.getMessage(),
					PAYMENT_TYPE_FIELD_CANNOT_BE_NULL);
		}

	}

	private Programmer buildProgrammer(CoffeeType coffeeType) {
		ProgrammerBuilder programmerBuilder = new ProgrammerBuilder();

		Programmer programmer = programmerBuilder.withFavouriteCoffee(
				coffeeType).build();
		return programmer;
	}

}
