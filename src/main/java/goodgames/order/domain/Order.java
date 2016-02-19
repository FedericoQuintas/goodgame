package goodgames.order.domain;

import goodgames.store.domain.PaymentType;

public class Order {

	private Double totalAmmountOfTime;
	private Programmer programmer;
	private PaymentType paymentType;
	private Integer machineNumber;

	public Order(Programmer programmer, PaymentType paymentType) {
		this.totalAmmountOfTime = new Double(0);
		this.programmer = programmer;
		this.paymentType = paymentType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public Double getTotalAmmountOfTime() {
		return totalAmmountOfTime;
	}

	public Programmer getProgrammer() {
		return this.programmer;
	}

	public void addAccumulatedTime(Double timeToAdd) {
		totalAmmountOfTime = totalAmmountOfTime + timeToAdd;
	}

	public void setMachineNumber(Integer machineNumber) {
		this.machineNumber = machineNumber;

	}

	public Integer getMachineNumber() {
		return machineNumber;
	}

}
