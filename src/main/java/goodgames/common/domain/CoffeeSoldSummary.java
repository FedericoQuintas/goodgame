package goodgames.common.domain;

import goodgames.order.domain.Order;

public class CoffeeSoldSummary {

	private Integer totalCoffeeSold = new Integer(0);
	private Integer totalCapuccinoSold = new Integer(0);
	private Integer totalLatteSold = new Integer(0);
	private Integer totalEspressoSold = new Integer(0);

	public Integer getTotalCoffeeSold() {
		return totalCoffeeSold;
	}

	public void addTotalOfCoffeeSold(Order order) {
		addTotalCoffeeSold();
		addByType(order.getProgrammer().getCoffeeType());
	}

	private void addByType(CoffeeType coffeeType) {
		if (coffeeType.equals(CoffeeType.CAPUCCINO)) {
			totalCapuccinoSold++;
		} else if (coffeeType.equals(CoffeeType.LATTE)) {
			totalLatteSold++;
		} else {
			totalEspressoSold++;
		}
	}

	private void addTotalCoffeeSold() {
		totalCoffeeSold++;
	}

	public Integer getTotalCapuccinoSold() {
		return totalCapuccinoSold;
	}

	public Integer getTotalLatteSold() {
		return totalLatteSold;
	}

	public Integer getTotalEspressoSold() {
		return totalEspressoSold;
	}

}
