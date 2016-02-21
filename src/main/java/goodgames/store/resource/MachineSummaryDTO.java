package goodgames.store.resource;

import goodgames.store.domain.CoffeeType;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

public class MachineSummaryDTO {

	@JsonProperty("total_sold")
	private Integer totalSold;
	@JsonProperty("total_sold_by_coffee_type")
	private Map<CoffeeType, Integer> totalSoldByCoffeeType;

	public synchronized Integer getTotalSold() {
		return totalSold;
	}

	public synchronized Map<CoffeeType, Integer> getTotalSoldByCoffeeType() {
		return totalSoldByCoffeeType;
	}

	public void setTotalSold(Integer totalSold) {
		this.totalSold = totalSold;
	}

	public void setTotalSoldByCoffeeType(
			Map<CoffeeType, Integer> totalSoldBycoffeeType) {
		this.totalSoldByCoffeeType = totalSoldBycoffeeType;
	}

}
