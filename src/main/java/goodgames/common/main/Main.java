package goodgames.common.main;

import goodgames.CoffeeShopSimulator;
import goodgames.config.GetPropertyValues;
import goodgames.order.domain.Order;
import goodgames.order.domain.builder.OrderBuilder;
import goodgames.store.domain.CoffeeType;
import goodgames.store.domain.MachineSummary;
import goodgames.store.domain.PaymentType;
import goodgames.store.domain.SummaryInformation;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import com.google.common.collect.Lists;

public class Main {

	private static final String INVALID_PARAMETERS = "Application needs three parameters. Please try again.";
	private static final String SOLD_BY_MACHINE_NUMBER = " sold by machine number ";
	private static final String TOTAL_COFFEE_SOLD_BY_MACHINE_NUMBER = "Total coffee sold by machine number ";
	private static final String SOLD = " sold: ";
	private static final String TOTAL = "Total ";
	private static final String TOTAL_COFFEE_SOLD = "Total coffee sold: ";
	private static final String FASTEST_PROGRAMMER = "Fastest Programmer: ";
	private static final String AVERAGE_AMMOUNT_OF_TIME = "Average ammount of time: ";
	private static final String TOTAL_AMMOUNT_OF_TIME = "Total ammount of time: ";
	private static final String SLOWEST_PROGRAMMER = "Slowest Programmer: ";
	private static List<PaymentType> paymentTypes;

	public static void main(String[] args) throws IOException {

		validateArguments(args);

		loadPaymentTypes();
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
		System.exit(0);
	}

	private static void validateArguments(String[] args) {
		if (args.length < 3) {
			throw new IllegalArgumentException(INVALID_PARAMETERS);
		}
	}

	private static void loadPaymentTypes() {
		paymentTypes = Lists.newArrayList();
		for (PaymentType paymentType : PaymentType.values()) {
			paymentTypes.add(paymentType);
		}
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

			printCoffeeTotalSoldByMachine(machineInformation);
			printMachineInformationByType(machineInformation);
		}
	}

	private static void printMachineInformationByType(
			Entry<Integer, MachineSummary> machineInformation) {
		for (CoffeeType coffeeType : CoffeeType.values()) {
			printTotalCoffeeTypeSoldByMachine(machineInformation, coffeeType);
		}
	}

	private static void printCoffeeSoldInformation(
			SummaryInformation summaryInformation) {

		printTotalCoffeeSold(summaryInformation);

		for (CoffeeType coffeeType : CoffeeType.values()) {
			printTotalCoffeeTypeSold(summaryInformation, coffeeType);
		}
	}

	private static void printTotalCoffeeTypeSold(
			SummaryInformation summaryInformation, CoffeeType coffeeType) {
		String totalCoffeeTypeSold = new StringBuilder()
				.append(TOTAL)
				.append(coffeeType)
				.append(SOLD)
				.append(summaryInformation.getCoffeeSold().getTotalSoldByType(
						coffeeType)).toString();

		System.out.println(totalCoffeeTypeSold);
	}

	private static void printTotalCoffeeSold(
			SummaryInformation summaryInformation) {
		String totalCoffeeSold = new StringBuilder()
				.append(TOTAL_COFFEE_SOLD)
				.append(summaryInformation.getCoffeeSold().getTotalCoffeeSold())
				.toString();
		System.out.println(totalCoffeeSold);
	}

	private static void printAmmountOfTimeInformation(
			SummaryInformation summaryInformation) {

		printAmmountOfTime(summaryInformation);

		printAverageAmmountOfTime(summaryInformation);

		printFastestAmmountOfTime(summaryInformation);

		printSlowestAmmountOfTime(summaryInformation);
	}

	private static void printSlowestAmmountOfTime(
			SummaryInformation summaryInformation) {
		String slowestAmmountOfTime = new StringBuilder()
				.append(SLOWEST_PROGRAMMER)
				.append(summaryInformation.getFastestAmmountOfTime())
				.toString();
		System.out.println(slowestAmmountOfTime);
	}

	private static void printFastestAmmountOfTime(
			SummaryInformation summaryInformation) {
		String fastestAmmountOfTime = new StringBuilder()
				.append(FASTEST_PROGRAMMER)
				.append(summaryInformation.getFastestAmmountOfTime())
				.toString();

		System.out.println(fastestAmmountOfTime);
	}

	private static void printAverageAmmountOfTime(
			SummaryInformation summaryInformation) {
		String averageAmmountOfTime = new StringBuilder()
				.append(AVERAGE_AMMOUNT_OF_TIME)
				.append(summaryInformation.getAverageAmmountOfTime())
				.toString();

		System.out.println(averageAmmountOfTime);
	}

	private static void printAmmountOfTime(SummaryInformation summaryInformation) {
		String ammountOfTime = new StringBuilder()
				.append(TOTAL_AMMOUNT_OF_TIME)
				.append(summaryInformation.getTotalAmmountOfTime()).toString();

		System.out.println(ammountOfTime);
	}

	private static SummaryInformation simulateOrders(List<Order> orders,
			Integer numberOfMachines) {
		SummaryInformation coffeeMachineInformation = new CoffeeShopSimulator(
				numberOfMachines).getCoffeeMachineInformation(orders);

		return coffeeMachineInformation;
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
		Integer paymentTypeIndex = rand.nextInt(PaymentType.values().length);
		return paymentTypes.get(paymentTypeIndex);
	}

	private static void printTotalCoffeeTypeSoldByMachine(
			Entry<Integer, MachineSummary> machineInformation,
			CoffeeType coffeeType) {
		String totalCoffeeTypeSoldByMachine = new StringBuilder()
				.append(TOTAL)
				.append(coffeeType.name())
				.append(SOLD_BY_MACHINE_NUMBER)
				.append(machineInformation.getKey())
				.append(": ")
				.append(machineInformation.getValue().getTotalPerType(
						coffeeType)).toString();

		System.out.println(totalCoffeeTypeSoldByMachine);
	}

	private static void printCoffeeTotalSoldByMachine(
			Entry<Integer, MachineSummary> machineInformation) {
		String totalCoffeeSoldByMachine = new StringBuilder()
				.append(TOTAL_COFFEE_SOLD_BY_MACHINE_NUMBER)
				.append(machineInformation.getKey()).append(": ")
				.append(machineInformation.getValue().getTotalSold())
				.toString();

		System.out.println(totalCoffeeSoldByMachine);
	}

}
