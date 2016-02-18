package goodgames.domain;

import java.util.SortedSet;
import java.util.TreeSet;

public class SummaryInformation {

	private Double totalAmmountOfTime;
	private SortedSet<Double> orderTimes = new TreeSet<>();
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
		return orderTimes.first();
	}

	public Double getFastestAmmountOfTime() {
		return orderTimes.last();
	}

	public CoffeeSoldSummary getCoffeeSold() {
		return coffeeSoldSummary;
	}

}
