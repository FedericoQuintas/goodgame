package goodgames;

import goodgames.common.config.GetPropertyValues;
import goodgames.common.domain.CoffeeType;
import goodgames.common.domain.PaymentType;
import goodgames.common.domain.SummaryInformation;
import goodgames.order.domain.Order;
import goodgames.order.domain.Programmer;
import goodgames.order.domain.builder.OrderBuilder;
import goodgames.order.domain.builder.ProgrammerBuilder;

import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Lists;

public class CoffeeSoldTest {

	private static Integer numberOfMachines;

	@BeforeClass
	public static void before() {
		GetPropertyValues properties = new GetPropertyValues();
		numberOfMachines = properties.getNumberOfMachines();

	}

	@Test
	public void whenProgrammerBuysOneCoffeeThenTotalCoffeeSoldIsOne() {

		Order order = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(order);

		SummaryInformation summaryInformation = simulateOrders(orders);

		Assert.assertEquals(new Integer(1), summaryInformation.getCoffeeSold()
				.getTotalCoffeeSold());
	}

	@Test
	public void whenTwoProgrammersBuyCoffeeThenTotalCoffeeSoldIsTwo() {

		Order orderOne = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		Order orderTwo = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(orderOne, orderTwo);

		SummaryInformation summaryInformation = simulateOrders(orders);

		Assert.assertEquals(new Integer(2), summaryInformation.getCoffeeSold()
				.getTotalCoffeeSold());
	}

	@Test
	public void whenTwoProgrammersBuyCoffeeCapuccinoThenTotalCapuccinoSoldIsTwo() {

		Order orderOne = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		Order orderTwo = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(orderOne, orderTwo);

		SummaryInformation summaryInformation = simulateOrders(orders);

		Assert.assertEquals(new Integer(2), summaryInformation.getCoffeeSold()
				.getTotalCapuccinoSold());
	}

	@Test
	public void whenTwoProgrammersBuyCoffeeLatteThenTotalLatteSoldIsTwo() {

		Order orderOne = generateOrder(CoffeeType.LATTE, PaymentType.CASH);

		Order orderTwo = generateOrder(CoffeeType.LATTE, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(orderOne, orderTwo);

		SummaryInformation summaryInformation = simulateOrders(orders);

		Assert.assertEquals(new Integer(2), summaryInformation.getCoffeeSold()
				.getTotalLatteSold());
	}

	@Test
	public void whenTwoProgrammersBuyCoffeeEspressoThenTotalEspressoSoldIsTwo() {

		Order orderOne = generateOrder(CoffeeType.ESPRESSO, PaymentType.CASH);

		Order orderTwo = generateOrder(CoffeeType.ESPRESSO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(orderOne, orderTwo);

		SummaryInformation summaryInformation = simulateOrders(orders);

		Assert.assertEquals(new Integer(2), summaryInformation.getCoffeeSold()
				.getTotalEspressoSold());
	}

	@Test
	public void whenTwoProgrammersBuyLatteAndEspressoThenTotalEspressoSoldIsOneAndTotalLatteIsOne() {

		Order orderOne = generateOrder(CoffeeType.LATTE, PaymentType.CASH);

		Order orderTwo = generateOrder(CoffeeType.ESPRESSO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(orderOne, orderTwo);

		SummaryInformation summaryInformation = simulateOrders(orders);

		Assert.assertEquals(new Integer(1), summaryInformation.getCoffeeSold()
				.getTotalEspressoSold());
		Assert.assertEquals(new Integer(1), summaryInformation.getCoffeeSold()
				.getTotalLatteSold());
	}

	@Test
	public void whenAProgrammerBuysCoffeeThenOneMachineSellsOneCoffee() {

		Order order = generateOrder(CoffeeType.LATTE, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(order);

		SummaryInformation summaryInformation = simulateOrders(orders);

		Assert.assertTrue(summaryInformation.getMachinesSummary());

	}

	private Programmer buildProgrammer(CoffeeType coffeeType) {
		ProgrammerBuilder programmerBuilder = new ProgrammerBuilder();

		Programmer programmer = programmerBuilder.withFavouriteCoffee(
				coffeeType).build();
		return programmer;
	}

	private SummaryInformation simulateOrders(List<Order> orders) {

		SummaryInformation coffeeMachineInformation = new CoffeeShopSimulator(
				numberOfMachines).getCoffeeMachineInformation(orders);

		return coffeeMachineInformation;
	}

	private Order generateOrder(CoffeeType coffeeType, PaymentType paymentType) {
		Programmer programmer = buildProgrammer(coffeeType);
		OrderBuilder orderBuilder = new OrderBuilder();

		Order order = orderBuilder.withPaymentType(paymentType)
				.withProgrammer(programmer).build();
		return order;
	}
}
