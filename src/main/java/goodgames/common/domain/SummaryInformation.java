package goodgames.common.domain;

import goodgames.order.domain.Order;
import goodgames.order.exception.EmptyOrderListException;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public class SummaryInformation {

	private static final String NO_ORDERS_HAVE_BEEN_MADE_YET = "No orders have been made yet";
	private Double totalAmmountOfTime;
	private List<Double> orderTimes = Lists.newArrayList();
	private CoffeeSoldSummary coffeeSoldSummary;

	public SummaryInformation() {
		totalAmmountOfTime = new Double(0);
		coffeeSoldSummary = new CoffeeSoldSummary();
	}

	public void addOrder(Order order) {
		addTotalTimeOfTime(order.getTotalAmmountOfTime());
		addOrderTime(order);
		coffeeSoldSummary.addTotalOfCoffeeSold(order);

	}

	private void addOrderTime(Order order) {
		orderTimes.add(order.getTotalAmmountOfTime());
	}

	private void addTotalTimeOfTime(Double totalAmmountOfTime) {
		this.totalAmmountOfTime = getTotalAmmountOfTime() + totalAmmountOfTime;
	}

	public Double getTotalAmmountOfTime() {
		return totalAmmountOfTime;
	}

	public Double getSlowestAmmountOfTime() {
		validateEmptyList();
		Collections.sort(orderTimes);
		return orderTimes.get(0);
	}

	public Double getFastestAmmountOfTime() {
		validateEmptyList();
		Collections.sort(orderTimes);
		return orderTimes.get(orderTimes.size() - 1);
	}

	private void validateEmptyList() {
		if (orderTimes.isEmpty()) {
			throw new EmptyOrderListException(NO_ORDERS_HAVE_BEEN_MADE_YET);
		}
	}

	public CoffeeSoldSummary getCoffeeSold() {
		return coffeeSoldSummary;
	}

	public Double getAverageAmmountOfTime() {
		validateEmptyList();
		Double total = new Double(0);
		for (Double orderTime : orderTimes) {
			total = total + orderTime;
		}

		return total / orderTimes.size();
	}

	public boolean getMachinesSummary() {
		
		return false;
	}
}
