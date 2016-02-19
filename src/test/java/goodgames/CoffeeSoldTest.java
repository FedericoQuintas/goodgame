package goodgames;

import goodgames.config.GetPropertyValues;
import goodgames.order.domain.Order;
import goodgames.order.domain.Programmer;
import goodgames.order.domain.builder.OrderBuilder;
import goodgames.order.domain.builder.ProgrammerBuilder;
import goodgames.store.domain.CoffeeType;
import goodgames.store.domain.PaymentType;
import goodgames.store.domain.SummaryInformation;

import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Lists;

public class CoffeeSoldTest {

	private static Integer numberOfMachines;

	@BeforeClass
	public static void before() throws IOException {
		GetPropertyValues properties = new GetPropertyValues();
		numberOfMachines = properties.getPropertyValues();
	}

	@Test
	public void whenProgrammerBuysOneCoffeeThenTotalCoffeeSoldIsOne() {

		Order order = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(order);

		SummaryInformation summaryInformation = simulateOrders(orders,
				numberOfMachines);

		Assert.assertEquals(new Integer(1), summaryInformation.getCoffeeSold()
				.getTotalCoffeeSold());
	}

	@Test
	public void whenTwoProgrammersBuyCoffeeThenTotalCoffeeSoldIsTwo() {

		Order orderOne = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		Order orderTwo = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(orderOne, orderTwo);

		SummaryInformation summaryInformation = simulateOrders(orders,
				numberOfMachines);

		Assert.assertEquals(new Integer(2), summaryInformation.getCoffeeSold()
				.getTotalCoffeeSold());
	}

	@Test
	public void whenTwoProgrammersBuyCoffeeCapuccinoThenTotalCapuccinoSoldIsTwo() {

		Order orderOne = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		Order orderTwo = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(orderOne, orderTwo);

		SummaryInformation summaryInformation = simulateOrders(orders,
				numberOfMachines);

		Assert.assertEquals(new Integer(2), summaryInformation.getCoffeeSold()
				.getTotalSoldByType(CoffeeType.CAPUCCINO));
	}

	@Test
	public void whenTwoProgrammersBuyCoffeeLatteThenTotalLatteSoldIsTwo() {

		Order orderOne = generateOrder(CoffeeType.LATTE, PaymentType.CASH);

		Order orderTwo = generateOrder(CoffeeType.LATTE, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(orderOne, orderTwo);

		SummaryInformation summaryInformation = simulateOrders(orders,
				numberOfMachines);

		Assert.assertEquals(new Integer(2), summaryInformation.getCoffeeSold()
				.getTotalSoldByType(CoffeeType.LATTE));
	}

	@Test
	public void whenTwoProgrammersBuyCoffeeEspressoThenTotalEspressoSoldIsTwo() {

		Order orderOne = generateOrder(CoffeeType.ESPRESSO, PaymentType.CASH);

		Order orderTwo = generateOrder(CoffeeType.ESPRESSO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(orderOne, orderTwo);

		SummaryInformation summaryInformation = simulateOrders(orders,
				numberOfMachines);

		Assert.assertEquals(new Integer(2), summaryInformation.getCoffeeSold()
				.getTotalSoldByType(CoffeeType.ESPRESSO));
	}

	@Test
	public void whenTwoProgrammersBuyLatteAndEspressoThenTotalEspressoSoldIsOneAndTotalLatteIsOne() {

		Order orderOne = generateOrder(CoffeeType.LATTE, PaymentType.CASH);

		Order orderTwo = generateOrder(CoffeeType.ESPRESSO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(orderOne, orderTwo);

		SummaryInformation summaryInformation = simulateOrders(orders,
				numberOfMachines);

		Assert.assertEquals(new Integer(1), summaryInformation.getCoffeeSold()
				.getTotalSoldByType(CoffeeType.ESPRESSO));
		Assert.assertEquals(new Integer(1), summaryInformation.getCoffeeSold()
				.getTotalSoldByType(CoffeeType.LATTE));
	}

	@Test
	public void whenAProgrammerBuysCoffeeThenOneMachineSellsOneCoffee() {

		Order order = generateOrder(CoffeeType.LATTE, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(order);

		Integer numberOfMachines = 1;

		SummaryInformation summaryInformation = simulateOrders(orders,
				numberOfMachines);

		Integer expectedCoffeeSold = 1;

		Integer machineNumber = 1;

		Assert.assertEquals(expectedCoffeeSold, summaryInformation
				.getMachinesSummary().get(machineNumber).getTotalSold());

	}

	private Programmer buildProgrammer(CoffeeType coffeeType) {
		ProgrammerBuilder programmerBuilder = new ProgrammerBuilder();

		Programmer programmer = programmerBuilder.withFavouriteCoffee(
				coffeeType).build();
		return programmer;
	}

	private SummaryInformation simulateOrders(List<Order> orders,
			Integer numberOfMachines) {

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
