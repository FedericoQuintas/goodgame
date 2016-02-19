package goodgames;

import static org.junit.Assert.fail;
import goodgames.common.config.GetPropertyValues;
import goodgames.common.domain.CoffeeType;
import goodgames.common.domain.PaymentType;
import goodgames.common.domain.SummaryInformation;
import goodgames.order.domain.Order;
import goodgames.order.domain.Programmer;
import goodgames.order.domain.builder.OrderBuilder;
import goodgames.order.domain.builder.ProgrammerBuilder;
import goodgames.order.exception.EmptyOrderListException;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Lists;

public class OrderAmmountOfTimeTest {

	private static final String NO_ORDERS_HAVE_BEEN_MADE_YET = "No orders have been made yet";

	private static Integer numberOfMachines;

	@BeforeClass
	public static void before() {
		GetPropertyValues properties = new GetPropertyValues();
		numberOfMachines = properties.getNumberOfMachines();
	}

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

		SummaryInformation coffeeMachineInformation = obtainSummaryInformation(orders);

		Double fastestTimeExpected = new Double(2.75);

		Assert.assertEquals(fastestTimeExpected,
				coffeeMachineInformation.getFastestAmmountOfTime());
	}

	private SummaryInformation obtainSummaryInformation(List<Order> orders) {
		SummaryInformation coffeeMachineInformation = new CoffeeShopSimulator(
				numberOfMachines).getCoffeeMachineInformation(orders);
		return coffeeMachineInformation;
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

		SummaryInformation coffeeMachineInformation = obtainSummaryInformation(orders);

		Double slowestTimeExpected = new Double(2);
		Assert.assertEquals(slowestTimeExpected,
				coffeeMachineInformation.getSlowestAmmountOfTime());
	}

	@Test
	public void whenOrderListIsEmptyAndAsksForSlowestOrderThenExceptionIsThrown() {

		List<Order> orders = Lists.newArrayList();

		SummaryInformation coffeeMachineInformation = obtainSummaryInformation(orders);

		try {
			coffeeMachineInformation.getSlowestAmmountOfTime();
			fail();
		} catch (EmptyOrderListException exception) {
			Assert.assertTrue(exception.getMessage().equals(
					NO_ORDERS_HAVE_BEEN_MADE_YET));
		}
	}

	@Test
	public void whenOrderListIsEmptyAndAsksForAverageOrderTimeThenExceptionIsThrown() {

		List<Order> orders = Lists.newArrayList();

		SummaryInformation coffeeMachineInformation = obtainSummaryInformation(orders);

		try {
			coffeeMachineInformation.getAverageAmmountOfTime();
			fail();
		} catch (EmptyOrderListException exception) {
			Assert.assertTrue(exception.getMessage().equals(
					NO_ORDERS_HAVE_BEEN_MADE_YET));
		}
	}

	@Test
	public void whenOrderListIsEmptyAndAsksForFastestOrderThenExceptionIsThrown() {

		List<Order> orders = Lists.newArrayList();

		SummaryInformation coffeeMachineInformation = obtainSummaryInformation(orders);

		try {
			coffeeMachineInformation.getFastestAmmountOfTime();
			fail();
		} catch (EmptyOrderListException exception) {
			Assert.assertTrue(exception.getMessage().equals(
					NO_ORDERS_HAVE_BEEN_MADE_YET));
		}
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

		SummaryInformation coffeeMachineInformation = obtainSummaryInformation(orders);

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

		SummaryInformation coffeeMachineInformation = obtainSummaryInformation(orders);
		return coffeeMachineInformation;
	}
}
