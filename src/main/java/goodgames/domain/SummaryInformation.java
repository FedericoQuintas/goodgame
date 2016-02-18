package goodgames.domain;

public class SummaryInformation {

	private Double totalAmmountOfTime;
	private Double slowestAmmountOfTime;
	private Double fastestAmmountOfTime;

	public SummaryInformation() {
		totalAmmountOfTime = new Double(0);
	}

	public void addOrder(Order order) {
		addTotalTimeOfTime(order.getTotalAmmountOfTime());
		updateSlowest(order);
		updateFastest(order);
	}

	private void updateFastest(Order order) {
		// TODO Auto-generated method stub
		
	}

	private void updateSlowest(Order order) {

	}

	private void addTotalTimeOfTime(Double totalAmmountOfTime) {
		this.totalAmmountOfTime = getTotalAmmountOfTime() + totalAmmountOfTime;
	}

	public Double getTotalAmmountOfTime() {
		return totalAmmountOfTime;
	}

	public Double getSlowestAmmountOfTime() {
		return slowestAmmountOfTime;
	}

	public Double getFastestAmmountOfTime() {
		return fastestAmmountOfTime;
	}

}
