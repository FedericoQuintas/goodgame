package goodgames;

import goodgames.order.domain.Order;
import goodgames.store.domain.CoffeeType;
import goodgames.store.domain.PaymentType;
import goodgames.store.domain.SummaryInformation;

import java.util.List;
import java.util.Random;

public class CoffeeShopSimulator {

	private SummaryInformation summaryInformation;
	private Integer numberOfMachines;

	public CoffeeShopSimulator(Integer numberOfMachines) {
		this.numberOfMachines = numberOfMachines;
	}

	public SummaryInformation getCoffeeMachineInformation(List<Order> orders) {

		summaryInformation = new SummaryInformation(numberOfMachines);

		for (Order order : orders) {
			selectCoffeeType(order);
			payCoffee(order);
			pickCoffeeFromMachine(order);
			addOrderStatsToSummaryInformation(order);
		}

		return summaryInformation;

	}

	private void addOrderStatsToSummaryInformation(Order order) {
		summaryInformation.addOrder(order);
	}

	private void pickCoffeeFromMachine(Order order) {
		selectMachine(order);
		findACup(order);
		putCupUnderTheOutlet(order);
		pickPaidCoffeeType(order);
		fillCup(order);
		takeCupAndLeave(order);
	}

	private void selectMachine(Order order) {
		Integer machineNumber = obtainRandomMachineNumber();
		order.setMachineNumber(machineNumber);
	}

	private Integer obtainRandomMachineNumber() {
		Random rand = new Random();
		Integer machineNumber = rand.nextInt(numberOfMachines) + 1;
		return machineNumber;
	}

	private void takeCupAndLeave(Order order) {
		order.addAccumulatedTime(new Double(0.25));
	}

	private void fillCup(Order order) {
		CoffeeType programmerCoffeeType = order.getProgrammer().getCoffeeType();
		if (programmerCoffeeType.equals(CoffeeType.ESPRESSO)) {
			order.addAccumulatedTime(new Double(0.25));
		} else if (programmerCoffeeType.equals(CoffeeType.LATTE)) {
			order.addAccumulatedTime(new Double(0.5));
		} else {
			order.addAccumulatedTime(new Double(0.75));
		}
	}

	private void pickPaidCoffeeType(Order order) {
		order.addAccumulatedTime(new Double(0.25));
	}

	private void putCupUnderTheOutlet(Order order) {
		order.addAccumulatedTime(new Double(0.25));
	}

	private void findACup(Order order) {
		order.addAccumulatedTime(new Double(0.25));
	}

	private void payCoffee(Order order) {
		if (order.getPaymentType().equals(PaymentType.CARD)) {
			order.addAccumulatedTime(new Double(0.25));
		} else {
			order.addAccumulatedTime(new Double(0.5));
		}

	}

	private void selectCoffeeType(Order order) {
		order.addAccumulatedTime(new Double(0.5));
	}

}
