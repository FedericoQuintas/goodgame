package goodgames.domain;

public class SummaryInformation {

	private Double totalAmmountOfTime;

	public SummaryInformation(){
		totalAmmountOfTime = new Double(0);
	}
	
	public void addOrder(Order order) {
		addTotalTimeOfTime(order.getTotalAmmountOfTime());
	}

	private void addTotalTimeOfTime(Double totalAmmountOfTime) {
		this.totalAmmountOfTime = getTotalAmmountOfTime() + totalAmmountOfTime;
	}

	public Double getTotalAmmountOfTime() {
		return totalAmmountOfTime;
	}

}
