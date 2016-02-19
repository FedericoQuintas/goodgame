package goodgames;

import goodgames.common.domain.CoffeeType;
import goodgames.common.domain.PaymentType;
import goodgames.common.domain.SummaryInformation;
import goodgames.order.domain.Order;

import java.util.List;

public class CoffeeShopSimulator {

	private SummaryInformation summaryInformation;

	public SummaryInformation getCoffeeMachineInformation(List<Order> orders) {

		summaryInformation = new SummaryInformation();
		
		for (Order order : orders) {
			selectCoffeeType(order);
			payCoffee(order);
			findCoffeeMachine(order);
			addOrderStatsToSummaryInformation(order);
		}

		return summaryInformation;

	}

	private void addOrderStatsToSummaryInformation(Order order) {
		summaryInformation.addOrder(order);
	}

	private void findCoffeeMachine(Order order) {
		findACup(order);
		putCupUnderTheOutlet(order);
		pickPaidCoffeeType(order);
		fillCup(order);
		takeCupAndLeave(order);
	}

	private void takeCupAndLeave(Order order) {
		order.addAccumulatedTime(new Double(0.25));
	}

	private void fillCup(Order order) {
		CoffeeType programmerCoffeeType = order.getProgrammer().getCoffeeType();
		if(programmerCoffeeType.equals(CoffeeType.ESPRESSO)){
			order.addAccumulatedTime(new Double(0.25));
		}else if(programmerCoffeeType.equals(CoffeeType.LATTE)){
			order.addAccumulatedTime(new Double(0.5));
		}else{
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
