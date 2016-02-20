package goodgames.common.main;

import goodgames.config.GetPropertyValues;
import goodgames.order.domain.Order;
import goodgames.order.domain.builder.OrderBuilder;
import goodgames.store.domain.CoffeeType;
import goodgames.store.domain.MachineSummary;
import goodgames.store.domain.PaymentType;
import goodgames.store.domain.SummaryInformation;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import com.google.common.collect.Lists;

public class Main {

	private static List<PaymentType> paymentsTypes;

	public static void main(String[] args) throws IOException {

		paymentsTypes = Arrays.asList(PaymentType.values());
		Integer numberOfEspressoOrders = Integer.parseInt(args[0]);
		Integer numberOfLatteOrders = Integer.parseInt(args[1]);
		Integer numberOfCapuccinoOrders = Integer.parseInt(args[2]);
		Integer numberOfMachines = getNumberOfMachines();

		List<Order> orders = generateOrders(numberOfEspressoOrders,
				numberOfLatteOrders, numberOfCapuccinoOrders);

		SummaryInformation summaryInformation = simulateOrders(orders,
				numberOfMachines);

		printAmmountOfTimeInformation(summaryInformation);
		printCoffeeSoldInformation(summaryInformation);
		printCoffeeByMachineInformation(summaryInformation);

	}

	private static List<Order> generateOrders(Integer numberOfEspressoOrders,
			Integer numberOfLatteOrders, Integer numberOfCapuccinoOrders) {
		List<Order> orders = Lists.newArrayList();
		orders.addAll(generateOrders(numberOfEspressoOrders,
				CoffeeType.ESPRESSO));
		orders.addAll(generateOrders(numberOfLatteOrders, CoffeeType.LATTE));
		orders.addAll(generateOrders(numberOfCapuccinoOrders,
				CoffeeType.CAPUCCINO));
		return orders;
	}

	private static void printCoffeeByMachineInformation(
			SummaryInformation summaryInformation) {
		for (Entry<Integer, MachineSummary> machineInformation : summaryInformation
				.getMachinesSummary().entrySet()) {
			machineInformation.getKey();
			machineInformation.getValue().getTotalSold();
			printMachineInformationByType(machineInformation);
		}
	}

	private static void printMachineInformationByType(
			Entry<Integer, MachineSummary> machineInformation) {
		for (CoffeeType coffeeType : CoffeeType.values()) {
			machineInformation.getValue().getTotalPerType(coffeeType);
		}
	}

	private static void printCoffeeSoldInformation(
			SummaryInformation summaryInformation) {
		summaryInformation.getCoffeeSold().getTotalCoffeeSold();
		for (CoffeeType coffeeType : CoffeeType.values()) {
			summaryInformation.getCoffeeSold().getTotalSoldByType(coffeeType);
		}
	}

	private static void printAmmountOfTimeInformation(
			SummaryInformation summaryInformation) {
		summaryInformation.getAverageAmmountOfTime();
		summaryInformation.getFastestAmmountOfTime();
		summaryInformation.getSlowestAmmountOfTime();
		summaryInformation.getTotalAmmountOfTime();
	}

	private static SummaryInformation simulateOrders(List<Order> orders,
			Integer numberOfMachines) {
		// TODO Auto-generated method stub
		return null;
	}

	private static List<Order> generateOrders(Integer expectedOrders,
			CoffeeType coffeeType) {
		List<Order> orders = Lists.newArrayList();
		for (int i = 0; i < expectedOrders; i++) {
			orders.add(generateOrder(coffeeType));
		}
		return orders;
	}

	private static Order generateOrder(CoffeeType coffeeType) {
		OrderBuilder orderBuilder = new OrderBuilder();

		PaymentType paymentType = obtainRandomPaymentType();
		Order order = orderBuilder.withPaymentType(paymentType)
				.withCoffeeType(coffeeType).build();
		return order;
	}

	private static Integer getNumberOfMachines() throws IOException {
		GetPropertyValues properties = new GetPropertyValues();
		return properties.getPropertyValues();
	}

	private static PaymentType obtainRandomPaymentType() {
		Random rand = new Random();
		Integer paymentTypeIndex = rand.nextInt(PaymentType.values().length) + 1;
		return paymentsTypes.get(paymentTypeIndex);
	}
}
