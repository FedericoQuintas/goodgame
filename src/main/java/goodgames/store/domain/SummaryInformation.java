package goodgames.store.domain;

import goodgames.order.domain.Order;
import goodgames.order.exception.EmptyOrderListException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SummaryInformation {

	private static final String NO_ORDERS_HAVE_BEEN_MADE_YET = "No orders have been made yet";
	private Double totalAmmountOfTime;
	private List<Double> orderTimes = Lists.newArrayList();
	private CoffeeSoldSummary coffeeSoldSummary;
	private Map<Integer, MachineSummary> summaryByMachineNumber;

	public SummaryInformation(Integer numberOfMachines) {
		totalAmmountOfTime = new Double(0);
		coffeeSoldSummary = new CoffeeSoldSummary();
		generateMachineSummaries(numberOfMachines);
	}

	public void addOrder(Order order) {
		addTotalTimeOfTime(order.getTotalAmmountOfTime());
		addOrderTime(order);
		coffeeSoldSummary.addTotalOfCoffeeSold(order);
		addToMachineSummary(order);

	}

	private void addToMachineSummary(Order order) {
		MachineSummary machineSummary = summaryByMachineNumber.get(order
				.getMachineNumber());
		machineSummary.addOrder(order);
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

	public Map<Integer, MachineSummary> getMachinesSummary() {
		return summaryByMachineNumber;
	}

	private void generateMachineSummaries(Integer numberOfMachines) {
		summaryByMachineNumber = Maps.newHashMap();

		for (Integer machineNumber = 1; machineNumber <= numberOfMachines; machineNumber++) {
			summaryByMachineNumber.put(machineNumber, new MachineSummary());
		}
	}
}
