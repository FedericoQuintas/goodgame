package goodgames;

import goodgames.domain.CoffeeType;
import goodgames.domain.Order;
import goodgames.domain.PaymentType;
import goodgames.domain.Programmer;
import goodgames.domain.SummaryInformation;
import goodgames.domain.builder.OrderBuilder;
import goodgames.domain.builder.ProgrammerBuilder;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CoffeeTest {

	@Test
	public void whenAProgrammerLikesEspressoAndPaysCashThenTakesTwoSecondsAndAQuarter() {

		Programmer programmer = buildProgrammer(CoffeeType.ESPRESSO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CASH)
				.withProgrammer(programmer).build();

		List<Order> orders = Arrays.asList(order);

		SummaryInformation coffeeMachineInformation = new CoffeeShopSimulator()
				.getCoffeeMachineInformation(orders);

		Assert.assertEquals(new Double(2.25),
				coffeeMachineInformation.getTotalCoffeeSold());
	}

	private Programmer buildProgrammer(CoffeeType coffeType) {

		ProgrammerBuilder programmerBuilder = new ProgrammerBuilder();

		Programmer programmer = programmerBuilder
				.withFavouriteCoffee(coffeType).build();

		return programmer;
	}
}
