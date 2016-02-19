package goodgames;

import goodgames.common.domain.CoffeeType;
import goodgames.common.domain.PaymentType;
import goodgames.order.domain.Order;
import goodgames.order.domain.Programmer;
import goodgames.order.domain.builder.OrderBuilder;
import goodgames.order.domain.builder.ProgrammerBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderFactoryTest {

	private OrderBuilder orderBuilder;

	@Before
	public void before() {
		orderBuilder = new OrderBuilder();
	}

	@Test
	public void whenCreatesAnOrderThenOrderHasTotalAmmountOfTimeInZero() {

		Programmer programmer = buildProgrammer(CoffeeType.ESPRESSO);

		Order order = orderBuilder.withProgrammer(programmer).build();

		Assert.assertEquals(new Double(0), order.getTotalAmmountOfTime());

	}

	@Test
	public void whenCreatesAnOrderThenOrderHasAProgrammer() {

		Programmer programmer = buildProgrammer(CoffeeType.ESPRESSO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withProgrammer(programmer).build();

		Assert.assertNotNull(order.getProgrammer());

	}

	@Test
	public void whenCreatesAnOrderWithCashThenOrderHasAPaymentType() {

		Programmer programmer = buildProgrammer(CoffeeType.ESPRESSO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withProgrammer(programmer)
				.withPaymentType(PaymentType.CASH).build();

		Assert.assertEquals(PaymentType.CASH, order.getPaymentType());

	}

	@Test
	public void whenCreatesAnOrderWithCreditCardThenOrderHasAPaymentType() {

		Programmer programmer = buildProgrammer(CoffeeType.ESPRESSO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withProgrammer(programmer)
				.withPaymentType(PaymentType.CARD).build();

		Assert.assertEquals(PaymentType.CARD, order.getPaymentType());

	}

	@Test
	public void whenCreatesAProgrammerWithEspressoTypeThenOrderHasAProgrammerWithFavouriteCoffeeType() {

		Programmer programmer = buildProgrammer(CoffeeType.ESPRESSO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withProgrammer(programmer).build();

		Assert.assertEquals(CoffeeType.ESPRESSO, order.getProgrammer()
				.getCoffeeType());

	}

	@Test
	public void whenCreatesAProgrammerWithLatteTypeThenOrderHasAProgrammerWithFavouriteCoffeeType() {

		Programmer programmer = buildProgrammer(CoffeeType.LATTE);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withProgrammer(programmer).build();

		Assert.assertEquals(CoffeeType.LATTE, order.getProgrammer()
				.getCoffeeType());

	}

	@Test
	public void whenCreatesAProgrammerWithCapuccinoTypeThenOrderHasAProgrammerWithFavouriteCoffeeType() {

		Programmer programmer = buildProgrammer(CoffeeType.CAPUCCINO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withProgrammer(programmer).build();

		Assert.assertEquals(CoffeeType.CAPUCCINO, order.getProgrammer()
				.getCoffeeType());

	}

	private Programmer buildProgrammer(CoffeeType coffeeType) {
		ProgrammerBuilder programmerBuilder = new ProgrammerBuilder();

		Programmer programmer = programmerBuilder.withFavouriteCoffee(
				coffeeType).build();
		return programmer;
	}

}
