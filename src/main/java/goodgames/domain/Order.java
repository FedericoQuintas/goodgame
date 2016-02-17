package goodgames.domain;

public class Order {

	private Double totalAmmountOfTime;
	private Programmer programmer;

	public Order(Programmer programmer) {
		this.totalAmmountOfTime = new Double(0);
		this.programmer = programmer;
	}

	public Double getTotalAmmountOfTime() {
		return totalAmmountOfTime;
	}

	public Programmer getProgrammer() {
		return this.programmer;
	}

}
