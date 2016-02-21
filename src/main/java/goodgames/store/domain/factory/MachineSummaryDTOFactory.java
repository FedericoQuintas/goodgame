package goodgames.store.domain.factory;

import goodgames.store.domain.CoffeeType;
import goodgames.store.domain.MachineSummary;
import goodgames.store.resource.MachineSummaryDTO;

import java.util.Map;

import com.google.common.collect.Maps;

public class MachineSummaryDTOFactory {

	public static MachineSummaryDTO create(MachineSummary machineSummary) {
		MachineSummaryDTO machineSummaryDTO = new MachineSummaryDTO();

		machineSummaryDTO.setTotalSold(machineSummary.getTotalSold());

		Map<CoffeeType, Integer> totalSoldBycoffeeType = Maps.newHashMap();
		for (CoffeeType coffeeType : CoffeeType.values()) {
			totalSoldBycoffeeType.put(coffeeType,
					machineSummary.getTotalPerType(coffeeType));
		}

		machineSummaryDTO.setTotalSoldByCoffeeType(totalSoldBycoffeeType);
		return machineSummaryDTO;
	}

}
