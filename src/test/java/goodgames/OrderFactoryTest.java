package goodgames;

import goodgames.domain.CoffeType;
import goodgames.domain.Order;
import goodgames.domain.PaymentType;
import goodgames.domain.Programmer;
import goodgames.domain.factory.OrderBuilder;
import goodgames.domain.factory.ProgrammerBuilder;

import org.junit.Assert;
import org.junit.Test;

public class OrderFactoryTest {

	@Test
	public void whenCreatesAnOrderThenOrderHasTotalAmmountOfTimeInZero() {

		Programmer programmer = buildProgrammer();

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withProgrammer(programmer).build();

		Assert.assertEquals(new Double(0), order.getTotalAmmountOfTime());

	}

	@Test
	public void whenCreatesAnOrderThenOrderHasAProgrammer() {
		
		Programmer programmer = buildProgrammer();

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withProgrammer(programmer).build();

		Assert.assertNotNull(order.getProgrammer());

	}

	private Programmer buildProgrammer() {
		ProgrammerBuilder programmerBuilder = new ProgrammerBuilder();

		Programmer programmer = programmerBuilder
				.withFavouriteCoffee(CoffeType.ESPRESSO)
				.withPaymentType(PaymentType.CASH).build();
		return programmer;
	}

}
