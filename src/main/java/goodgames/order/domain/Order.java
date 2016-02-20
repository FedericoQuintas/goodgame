package goodgames.order.domain;

import goodgames.store.domain.CoffeeType;
import goodgames.store.domain.PaymentType;

public class Order {

	private Double totalAmmountOfTime;
	private CoffeeType coffeeType;
	private PaymentType paymentType;
	private Integer machineNumber;

	public Order(CoffeeType coffeeType, PaymentType paymentType) {
		this.totalAmmountOfTime = new Double(0);
		this.coffeeType = coffeeType;
		this.paymentType = paymentType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public Double getTotalAmmountOfTime() {
		return totalAmmountOfTime;
	}

	public CoffeeType getCoffeeType() {
		return this.coffeeType;
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
