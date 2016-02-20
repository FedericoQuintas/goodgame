package goodgames;

import goodgames.config.GetPropertyValues;
import goodgames.order.domain.Order;
import goodgames.order.domain.Programmer;
import goodgames.order.domain.builder.OrderBuilder;
import goodgames.order.domain.builder.ProgrammerBuilder;
import goodgames.store.domain.CoffeeType;
import goodgames.store.domain.MachineSummary;
import goodgames.store.domain.PaymentType;
import goodgames.store.domain.SummaryInformation;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	@Test
	public void when10CoffeeThenThreeMachinesSell10Coffee() {
		Integer expectedCoffeeSold = 10;

		List<Order> orders = generateOrders(expectedCoffeeSold);

		Integer numberOfMachines = 10;

		SummaryInformation summaryInformation = simulateOrders(orders,
				numberOfMachines);

		Map<Integer, MachineSummary> machinesSummary = summaryInformation
				.getMachinesSummary();

		Integer totalCoffeeSold = sumCoffeeSoldInMachines(machinesSummary);

		Assert.assertEquals(expectedCoffeeSold, totalCoffeeSold);
	}

	@Test
	public void whenAProgrammerBuysLatteThenOneMachineSellsOneLatte() {

		Order order = generateOrder(CoffeeType.LATTE, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(order);

		Integer numberOfMachines = 1;

		SummaryInformation summaryInformation = simulateOrders(orders,
				numberOfMachines);

		Integer expectedCoffeeSold = 1;

		Integer machineNumber = 1;

		Assert.assertEquals(expectedCoffeeSold, summaryInformation
				.getMachinesSummary().get(machineNumber).getTotalPerType(CoffeeType.LATTE));

	}
	
	@Test
	public void whenAProgrammerBuysEspressoThenOneMachineSellsOneEspresso() {

		Order order = generateOrder(CoffeeType.ESPRESSO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(order);

		Integer numberOfMachines = 1;

		SummaryInformation summaryInformation = simulateOrders(orders,
				numberOfMachines);

		Integer expectedCoffeeSold = 1;

		Integer machineNumber = 1;

		Assert.assertEquals(expectedCoffeeSold, summaryInformation
				.getMachinesSummary().get(machineNumber).getTotalPerType(CoffeeType.ESPRESSO));

	}
	
	
	@Test
	public void whenAProgrammerBuysCapuccinoThenOneMachineSellsOneCapuccino() {

		Order order = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(order);

		Integer numberOfMachines = 1;

		SummaryInformation summaryInformation = simulateOrders(orders,
				numberOfMachines);

		Integer expectedCoffeeSold = 1;

		Integer machineNumber = 1;

		Assert.assertEquals(expectedCoffeeSold, summaryInformation
				.getMachinesSummary().get(machineNumber).getTotalPerType(CoffeeType.CAPUCCINO));

	}
	private Integer sumCoffeeSoldInMachines(
			Map<Integer, MachineSummary> machinesSummary) {
		Integer totalCoffeeSold = 0;
		for (Entry<Integer, MachineSummary> machineByNumber : machinesSummary
				.entrySet()) {
			totalCoffeeSold = totalCoffeeSold
					+ machinesSummary.get(machineByNumber.getKey())
							.getTotalSold();
		}
		return totalCoffeeSold;
	}

	private List<Order> generateOrders(Integer expectedCoffeeSold) {
		List<Order> orders = Lists.newArrayList();
		for (int i = 0; i < expectedCoffeeSold; i++) {
			orders.add(generateOrder(CoffeeType.LATTE, PaymentType.CASH));
		}
		return orders;
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
