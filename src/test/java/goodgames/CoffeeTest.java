package goodgames;

import goodgames.domain.CoffeType;
import goodgames.domain.Order;
import goodgames.domain.PaymentType;
import goodgames.domain.Programmer;
import goodgames.domain.SummaryInformation;
import goodgames.domain.factory.OrderBuilder;
import goodgames.domain.factory.ProgrammerBuilder;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class CoffeeTest {

	@Test
	public void whenAProgrammerLikesEspressoAndPaysCashThenTakesTwoSecondsAndAQuarter() {

		Programmer programmer = buildProgrammer(CoffeType.ESPRESSO,
				PaymentType.CASH);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withProgrammer(programmer).build();

		List<Order> orders = Arrays.asList(order);

		SummaryInformation coffeeMachineInformation = new CoffeeShopSimulator()
				.getCoffeeMachineInformation(orders);

		Assert.assertEquals(new Double(2.25),
				coffeeMachineInformation.getTotalCoffeeSold());
	}

	private Programmer buildProgrammer(CoffeType coffeType,
			PaymentType paymentType) {

		ProgrammerBuilder programmerBuilder = new ProgrammerBuilder();

		Programmer programmer = programmerBuilder
				.withFavouriteCoffee(coffeType).withPaymentType(paymentType)
				.build();

		return programmer;
	}
}
