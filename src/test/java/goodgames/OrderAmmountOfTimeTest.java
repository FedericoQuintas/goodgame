package goodgames;

import goodgames.config.GetPropertyValues;
import goodgames.order.domain.Order;
import goodgames.order.domain.builder.OrderBuilder;
import goodgames.store.domain.CoffeeType;
import goodgames.store.domain.PaymentType;
import goodgames.store.domain.SummaryInformation;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Lists;

public class OrderAmmountOfTimeTest {

	private static Integer numberOfMachines;

	@BeforeClass
	public static void before() throws IOException {
		numberOfMachines = GetPropertyValues.getInstance()
				.getNumberOfMachines();
	}

	@Test
	public void whenAProgrammerLikesEspressoAndPaysCashThenTakesTwoSecondsAndAQuarter() {

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CASH)
				.withCoffeeType(CoffeeType.ESPRESSO).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2.25),
				coffeeMachineInformation.getTotalAmmountOfTime());
	}

	@Test
	public void whenAProgrammerLikesEspressoAndPaysWithCreditCardThenTakesTwoSeconds() {

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CARD)
				.withCoffeeType(CoffeeType.ESPRESSO).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2),
				coffeeMachineInformation.getTotalAmmountOfTime());
	}

	@Test
	public void whenAProgrammerLikesLatteAndPaysWithCashThenTakesTwoSecondsAndAHalf() {

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CASH)
				.withCoffeeType(CoffeeType.LATTE).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2.5),
				coffeeMachineInformation.getTotalAmmountOfTime());
	}

	@Test
	public void whenAProgrammerLikesLatteAndPaysWithCreditCardThenTakesTwoSecondsAndAQuarter() {

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CARD)
				.withCoffeeType(CoffeeType.LATTE).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2.25),
				coffeeMachineInformation.getTotalAmmountOfTime());
	}

	@Test
	public void whenAProgrammerLikesCapuccinoAndPaysWithCashThenTakesTwoSecondsAndThreeQuarters() {

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CASH)
				.withCoffeeType(CoffeeType.CAPUCCINO).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2.75),
				coffeeMachineInformation.getTotalAmmountOfTime());

	}

	@Test
	public void whenAProgrammerLikesCapuccinoAndPaysWithCashThenTakesTwoSecondsAndAHalf() {

		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(PaymentType.CARD)
				.withCoffeeType(CoffeeType.CAPUCCINO).build();

		SummaryInformation coffeeMachineInformation = simulateOrders(order);

		Assert.assertEquals(new Double(2.5),
				coffeeMachineInformation.getTotalAmmountOfTime());
	}

	@Test
	public void whenTwoProgrammersMakesOrdersThenFastestIsCalculatedCorrectly() {

		OrderBuilder orderBuilder = new OrderBuilder();

		Order orderOne = orderBuilder.withPaymentType(PaymentType.CARD)
				.withCoffeeType(CoffeeType.ESPRESSO).build();

		Order orderTwo = orderBuilder.withPaymentType(PaymentType.CASH)
				.withCoffeeType(CoffeeType.CAPUCCINO).build();

		List<Order> orders = Arrays.asList(orderOne, orderTwo);

		SummaryInformation coffeeMachineInformation = obtainSummaryInformation(orders);

		Double fastestTimeExpected = new Double(2.75);

		Assert.assertEquals(fastestTimeExpected,
				coffeeMachineInformation.getFastestAmmountOfTime());
	}

	private SummaryInformation obtainSummaryInformation(List<Order> orders) {
		SummaryInformation coffeeMachineInformation = new CoffeeStoreSimulator(
				numberOfMachines).getCoffeeMachineInformation(orders);
		return coffeeMachineInformation;
	}

	@Test
	public void whenTwoProgrammersMakesOrdersThenSlowestIsCalculatedCorrectly() {

		OrderBuilder orderBuilder = new OrderBuilder();

		Order orderOne = orderBuilder.withPaymentType(PaymentType.CARD)
				.withCoffeeType(CoffeeType.ESPRESSO).build();

		Order orderTwo = orderBuilder.withPaymentType(PaymentType.CASH)
				.withCoffeeType(CoffeeType.CAPUCCINO).build();

		List<Order> orders = Arrays.asList(orderOne, orderTwo);

		SummaryInformation coffeeMachineInformation = obtainSummaryInformation(orders);

		Double slowestTimeExpected = new Double(2);
		Assert.assertEquals(slowestTimeExpected,
				coffeeMachineInformation.getSlowestAmmountOfTime());
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenOrderListIsEmptyAndAsksForSlowestOrderThenExceptionIsThrown() {

		List<Order> orders = Lists.newArrayList();

		SummaryInformation coffeeMachineInformation = obtainSummaryInformation(orders);

		coffeeMachineInformation.getSlowestAmmountOfTime();
	}

	@Test
	public void whenTwoProgrammersMakesOrdersThenAverageTimeCalculatedCorrectly() {

		OrderBuilder orderBuilder = new OrderBuilder();

		Order orderOne = orderBuilder.withPaymentType(PaymentType.CARD)
				.withCoffeeType(CoffeeType.ESPRESSO).build();

		Order orderTwo = orderBuilder.withPaymentType(PaymentType.CASH)
				.withCoffeeType(CoffeeType.CAPUCCINO).build();

		List<Order> orders = Arrays.asList(orderOne, orderTwo);

		SummaryInformation coffeeMachineInformation = obtainSummaryInformation(orders);

		Double averageTimeExpected = new Double(2.375);
		Assert.assertEquals(averageTimeExpected,
				coffeeMachineInformation.getAverageAmmountOfTime());
	}

	private SummaryInformation simulateOrders(Order order) {
		List<Order> orders = Arrays.asList(order);

		SummaryInformation coffeeMachineInformation = obtainSummaryInformation(orders);
		return coffeeMachineInformation;
	}
}
