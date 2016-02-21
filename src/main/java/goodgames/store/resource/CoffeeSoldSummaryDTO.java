package goodgames.store.resource;

import goodgames.store.domain.CoffeeType;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

public class CoffeeSoldSummaryDTO {

	@JsonProperty("total_coffee_sold")
	private Integer totalCoffeeSold;
	@JsonProperty("total_sold_by_coffee_type")
	private Map<CoffeeType, Integer> totalSoldByCoffeeType;

	public Integer getTotalCoffeeSold() {
		return totalCoffeeSold;
	}

	public Map<CoffeeType, Integer> getTotalSoldByCoffeeType() {
		return totalSoldByCoffeeType;
	}

	public void setTotalCoffeeSold(Integer totalCoffeeSold) {
		this.totalCoffeeSold = totalCoffeeSold;
	}

	public void setTotalSoldByCoffeeType(
			Map<CoffeeType, Integer> totalSoldBycoffeeType) {
		this.totalSoldByCoffeeType = totalSoldBycoffeeType;

	}

}
