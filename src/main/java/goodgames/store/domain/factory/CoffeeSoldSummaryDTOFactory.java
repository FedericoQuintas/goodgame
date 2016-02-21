package goodgames.store.domain.factory;

import java.util.Map;

import com.google.common.collect.Maps;

import goodgames.store.domain.CoffeeSoldSummary;
import goodgames.store.domain.CoffeeType;
import goodgames.store.resource.CoffeeSoldSummaryDTO;

public class CoffeeSoldSummaryDTOFactory {

	public static CoffeeSoldSummaryDTO create(
			CoffeeSoldSummary coffeeSoldSummary) {
		
		CoffeeSoldSummaryDTO coffeeSoldSummaryDTO = new CoffeeSoldSummaryDTO();
		
		coffeeSoldSummaryDTO.setTotalCoffeeSold(coffeeSoldSummary
				.getTotalCoffeeSold());
		
		Map<CoffeeType, Integer> totalSoldBycoffeeType = Maps.newHashMap();
		for (CoffeeType coffeeType : CoffeeType.values()) {
			totalSoldBycoffeeType.put(coffeeType,
					coffeeSoldSummary.getTotalSoldByType(coffeeType));
		}

		coffeeSoldSummaryDTO.setTotalSoldByCoffeeType(totalSoldBycoffeeType);
		

		return coffeeSoldSummaryDTO;
	}

}
