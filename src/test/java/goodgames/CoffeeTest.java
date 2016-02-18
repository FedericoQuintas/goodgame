package goodgames;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import goodgames.domain.CoffeeType;
import goodgames.domain.Order;
import goodgames.domain.PaymentType;
import goodgames.domain.Programmer;
import goodgames.domain.SummaryInformation;
import goodgames.domain.builder.OrderBuilder;
import goodgames.domain.builder.ProgrammerBuilder;

public class CoffeeTest {

	@Test
	public void whenAProgrammerLikesEspressoAndPaysCashThenTakesTwoSecondsAndAQuarter() {

		Programmer programmer = buildProgrammer(CoffeeType.ESPRESSO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CASH).withProgrammer(programmer).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2.25), coffeeMachineInformation.getTotalAmmountOfTime());
	}

	@Test
	public void whenAProgrammerLikesEspressoAndPaysWithCreditCardThenTakesTwoSeconds() {

		Programmer programmer = buildProgrammer(CoffeeType.ESPRESSO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CARD).withProgrammer(programmer).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2), coffeeMachineInformation.getTotalAmmountOfTime());
	}

	@Test
	public void whenAProgrammerLikesLatteAndPaysWithCashThenTakesTwoSecondsAndAHalf() {

		Programmer programmer = buildProgrammer(CoffeeType.LATTE);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CASH).withProgrammer(programmer).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2.5), coffeeMachineInformation.getTotalAmmountOfTime());
	}

	private SummaryInformation simulateOrders(Order order) {
		List<Order> orders = Arrays.asList(order);

		SummaryInformation coffeeMachineInformation = new CoffeeShopSimulator().getCoffeeMachineInformation(orders);
		return coffeeMachineInformation;
	}

	@Test
	public void whenAProgrammerLikesLatteAndPaysWithCreditCardThenTakesTwoSecondsAndAQuarter() {

		Programmer programmer = buildProgrammer(CoffeeType.LATTE);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CARD).withProgrammer(programmer).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2.25), coffeeMachineInformation.getTotalAmmountOfTime());
	}

	@Test
	public void whenAProgrammerLikesCapuccinoAndPaysWithCashThenTakesTwoSecondsAndThreeQuarters() {

		Programmer programmer = buildProgrammer(CoffeeType.CAPUCCINO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CASH).withProgrammer(programmer).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2.75), coffeeMachineInformation.getTotalAmmountOfTime());

	}

	@Test
	public void whenAProgrammerLikesCapuccinoAndPaysWithCashThenTakesTwoSecondsAndAHalf() {

		Programmer programmer = buildProgrammer(CoffeeType.CAPUCCINO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CARD).withProgrammer(programmer).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2.5), coffeeMachineInformation.getTotalAmmountOfTime());
	}

	@Test
	public void whenTwoProgrammersMakesOrdersThenFastestIsCalculatedCorrectly() {

		Programmer programmerOne = buildProgrammer(CoffeeType.ESPRESSO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order orderOne = orderBuilder.withPaymentType(PaymentType.CARD).withProgrammer(programmerOne).build();

		Programmer programmerTwo = buildProgrammer(CoffeeType.CAPUCCINO);

		Order orderTwo = orderBuilder.withPaymentType(PaymentType.CASH).withProgrammer(programmerTwo).build();

		List<Order> orders = Arrays.asList(orderOne, orderTwo);
		
		SummaryInformation coffeeMachineInformation = new CoffeeShopSimulator().getCoffeeMachineInformation(orders);

		Double fastestTimeExpected = new Double(2.75);
		Assert.assertEquals(fastestTimeExpected, coffeeMachineInformation.getFastestAmmountOfTime());
	}

	@Test
	public void whenTwoProgrammersMakesOrdersThenSlowestIsCalculatedCorrectly() {

		Programmer programmerOne = buildProgrammer(CoffeeType.ESPRESSO);

		OrderBuilder orderBuilder = new OrderBuilder();

		Order orderOne = orderBuilder.withPaymentType(PaymentType.CARD).withProgrammer(programmerOne).build();

		Programmer programmerTwo = buildProgrammer(CoffeeType.CAPUCCINO);

		Order orderTwo = orderBuilder.withPaymentType(PaymentType.CASH).withProgrammer(programmerTwo).build();

		List<Order> orders = Arrays.asList(orderOne, orderTwo);
		
		SummaryInformation coffeeMachineInformation = new CoffeeShopSimulator().getCoffeeMachineInformation(orders);

		Double slowestTimeExpected = new Double(2);
		Assert.assertEquals(slowestTimeExpected, coffeeMachineInformation.getSlowestAmmountOfTime());
	}
	
	private Programmer buildProgrammer(CoffeeType coffeType) {

		ProgrammerBuilder programmerBuilder = new ProgrammerBuilder();

		Programmer programmer = programmerBuilder.withFavouriteCoffee(coffeType).build();

		return programmer;
	}
}
