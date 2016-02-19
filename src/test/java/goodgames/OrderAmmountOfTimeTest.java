package goodgames;

import goodgames.common.domain.CoffeeType;
import goodgames.common.domain.PaymentType;
import goodgames.common.domain.SummaryInformation;
import goodgames.order.domain.Order;
import goodgames.order.domain.Programmer;
import goodgames.order.domain.builder.OrderBuilder;
import goodgames.order.domain.builder.ProgrammerBuilder;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class OrderAmmountOfTimeTest {

	@Test
	public void whenAProgrammerLikesEspressoAndPaysCashThenTakesTwoSecondsAndAQuarter() {

		Programmer programmer = buildProgrammer(CoffeeType.ESPRESSO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CASH)
				.withProgrammer(programmer).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2.25),
				coffeeMachineInformation.getTotalAmmountOfTime());
	}

	@Test
	public void whenAProgrammerLikesEspressoAndPaysWithCreditCardThenTakesTwoSeconds() {

		Programmer programmer = buildProgrammer(CoffeeType.ESPRESSO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CARD)
				.withProgrammer(programmer).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2),
				coffeeMachineInformation.getTotalAmmountOfTime());
	}

	@Test
	public void whenAProgrammerLikesLatteAndPaysWithCashThenTakesTwoSecondsAndAHalf() {

		Programmer programmer = buildProgrammer(CoffeeType.LATTE);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CASH)
				.withProgrammer(programmer).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2.5),
				coffeeMachineInformation.getTotalAmmountOfTime());
	}

	@Test
	public void whenAProgrammerLikesLatteAndPaysWithCreditCardThenTakesTwoSecondsAndAQuarter() {

		Programmer programmer = buildProgrammer(CoffeeType.LATTE);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CARD)
				.withProgrammer(programmer).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2.25),
				coffeeMachineInformation.getTotalAmmountOfTime());
	}

	@Test
	public void whenAProgrammerLikesCapuccinoAndPaysWithCashThenTakesTwoSecondsAndThreeQuarters() {

		Programmer programmer = buildProgrammer(CoffeeType.CAPUCCINO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CASH)
				.withProgrammer(programmer).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2.75),
				coffeeMachineInformation.getTotalAmmountOfTime());

	}

	@Test
	public void whenAProgrammerLikesCapuccinoAndPaysWithCashThenTakesTwoSecondsAndAHalf() {

		Programmer programmer = buildProgrammer(CoffeeType.CAPUCCINO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CARD)
				.withProgrammer(programmer).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2.5),
				coffeeMachineInformation.getTotalAmmountOfTime());
	}

	@Test
	public void whenTwoProgrammersMakesOrdersThenFastestIsCalculatedCorrectly() {

		Programmer programmerOne = buildProgrammer(CoffeeType.ESPRESSO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order orderOne = orderBuilder.withPaymentType(PaymentType.CARD)
				.withProgrammer(programmerOne).build();

		Programmer programmerTwo = buildProgrammer(CoffeeType.CAPUCCINO);

		Order orderTwo = orderBuilder.withPaymentType(PaymentType.CASH)
				.withProgrammer(programmerTwo).build();

		List<Order> orders = Arrays.asList(orderOne, orderTwo);

		SummaryInformation coffeeMachineInformation = new CoffeeShopSimulator()
				.getCoffeeMachineInformation(orders);

		Double fastestTimeExpected = new Double(2.75);

		Assert.assertEquals(fastestTimeExpected,
				coffeeMachineInformation.getFastestAmmountOfTime());
	}

	@Test
	public void whenTwoProgrammersMakesOrdersThenSlowestIsCalculatedCorrectly() {

		Programmer programmerOne = buildProgrammer(CoffeeType.ESPRESSO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order orderOne = orderBuilder.withPaymentType(PaymentType.CARD)
				.withProgrammer(programmerOne).build();

		Programmer programmerTwo = buildProgrammer(CoffeeType.CAPUCCINO);

		Order orderTwo = orderBuilder.withPaymentType(PaymentType.CASH)
				.withProgrammer(programmerTwo).build();

		List<Order> orders = Arrays.asList(orderOne, orderTwo);

		SummaryInformation coffeeMachineInformation = new CoffeeShopSimulator()
				.getCoffeeMachineInformation(orders);

		Double slowestTimeExpected = new Double(2);
		Assert.assertEquals(slowestTimeExpected,
				coffeeMachineInformation.getSlowestAmmountOfTime());
	}

	@Test
	public void whenTwoProgrammersMakesOrdersThenAverageTimeCalculatedCorrectly() {

		Programmer programmerOne = buildProgrammer(CoffeeType.ESPRESSO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order orderOne = orderBuilder.withPaymentType(PaymentType.CARD)
				.withProgrammer(programmerOne).build();

		Programmer programmerTwo = buildProgrammer(CoffeeType.CAPUCCINO);

		Order orderTwo = orderBuilder.withPaymentType(PaymentType.CASH)
				.withProgrammer(programmerTwo).build();

		List<Order> orders = Arrays.asList(orderOne, orderTwo);

		SummaryInformation coffeeMachineInformation = new CoffeeShopSimulator()
				.getCoffeeMachineInformation(orders);

		Double averageTimeExpected = new Double(2.375);
		Assert.assertEquals(averageTimeExpected,
				coffeeMachineInformation.getAverageAmmountOfTime());
	}

	private Programmer buildProgrammer(CoffeeType coffeType) {

		ProgrammerBuilder programmerBuilder = new ProgrammerBuilder();

		Programmer programmer = programmerBuilder
				.withFavouriteCoffee(coffeType).build();

		return programmer;
	}

	private SummaryInformation simulateOrders(Order order) {
		List<Order> orders = Arrays.asList(order);

		SummaryInformation coffeeMachineInformation = new CoffeeShopSimulator()
				.getCoffeeMachineInformation(orders);
		return coffeeMachineInformation;
	}
}
