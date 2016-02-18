package goodgames;

import goodgames.domain.CoffeeType;
import goodgames.domain.Order;
import goodgames.domain.PaymentType;
import goodgames.domain.Programmer;
import goodgames.domain.SummaryInformation;
import goodgames.domain.builder.OrderBuilder;
import goodgames.domain.builder.ProgrammerBuilder;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.google.common.collect.Lists;

public class CoffeeSoldTest {

	@Test
	public void whenProgrammerBuysOneCoffeeThenTotalCoffeeSoldIsOne() {

		Order order = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(order);

		SummaryInformation coffeeMachineInformation = simulateOrders(orders);

		Assert.assertEquals(new Integer(1), coffeeMachineInformation
				.getCoffeeSold().getTotalCoffeeSold());
	}

	@Test
	public void whenTwoProgrammersBuyCoffeeThenTotalCoffeeSoldIsTwo() {

		Order orderOne = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		Order orderTwo = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(orderOne, orderTwo);

		SummaryInformation coffeeMachineInformation = simulateOrders(orders);

		Assert.assertEquals(new Integer(2), coffeeMachineInformation
				.getCoffeeSold().getTotalCoffeeSold());
	}

	@Test
	public void whenTwoProgrammersBuyCoffeeCapuccinoThenTotalCapuccinoSoldIsTwo() {

		Order orderOne = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		Order orderTwo = generateOrder(CoffeeType.CAPUCCINO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(orderOne, orderTwo);

		SummaryInformation coffeeMachineInformation = simulateOrders(orders);

		Assert.assertEquals(new Integer(2), coffeeMachineInformation
				.getCoffeeSold().getTotalCapuccinoSold());
	}

	@Test
	public void whenTwoProgrammersBuyCoffeeLatteThenTotalLatteSoldIsTwo() {

		Order orderOne = generateOrder(CoffeeType.LATTE, PaymentType.CASH);

		Order orderTwo = generateOrder(CoffeeType.LATTE, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(orderOne, orderTwo);

		SummaryInformation coffeeMachineInformation = simulateOrders(orders);

		Assert.assertEquals(new Integer(2), coffeeMachineInformation
				.getCoffeeSold().getTotalLatteSold());
	}

	@Test
	public void whenTwoProgrammersBuyCoffeeEspressoThenTotalEspressoSoldIsTwo() {

		Order orderOne = generateOrder(CoffeeType.ESPRESSO, PaymentType.CASH);

		Order orderTwo = generateOrder(CoffeeType.ESPRESSO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(orderOne, orderTwo);

		SummaryInformation coffeeMachineInformation = simulateOrders(orders);

		Assert.assertEquals(new Integer(2), coffeeMachineInformation
				.getCoffeeSold().getTotalEspressoSold());
	}

	@Test
	public void whenTwoProgrammersBuyLatteAndEspressoThenTotalEspressoSoldIsOneAndTotalLatteIsOne() {

		Order orderOne = generateOrder(CoffeeType.LATTE, PaymentType.CASH);

		Order orderTwo = generateOrder(CoffeeType.ESPRESSO, PaymentType.CASH);

		List<Order> orders = Lists.newArrayList(orderOne, orderTwo);

		SummaryInformation coffeeMachineInformation = simulateOrders(orders);

		Assert.assertEquals(new Integer(1), coffeeMachineInformation
				.getCoffeeSold().getTotalEspressoSold());
		Assert.assertEquals(new Integer(1), coffeeMachineInformation
				.getCoffeeSold().getTotalLatteSold());
	}

	private Programmer buildProgrammer(CoffeeType coffeeType) {
		ProgrammerBuilder programmerBuilder = new ProgrammerBuilder();

		Programmer programmer = programmerBuilder.withFavouriteCoffee(
				coffeeType).build();
		return programmer;
	}

	private SummaryInformation simulateOrders(List<Order> orders) {

		SummaryInformation coffeeMachineInformation = new CoffeeShopSimulator()
				.getCoffeeMachineInformation(orders);

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
