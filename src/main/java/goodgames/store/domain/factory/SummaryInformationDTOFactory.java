package goodgames.store.domain.factory;

import goodgames.store.domain.CoffeeSoldSummary;
import goodgames.store.domain.MachineSummary;
import goodgames.store.domain.SummaryInformation;
import goodgames.store.resource.CoffeeSoldSummaryDTO;
import goodgames.store.resource.MachineSummaryDTO;
import goodgames.store.resource.SummaryInformationDTO;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

public class SummaryInformationDTOFactory {

	public static SummaryInformationDTO create(
			SummaryInformation summaryInformation) {
		SummaryInformationDTO summaryInformationDTO = new SummaryInformationDTO();
		summaryInformationDTO.setAverageAmmountOfTime(summaryInformation
				.getAverageAmmountOfTime());

		summaryInformationDTO
				.setCoffeeSold(convertToCoffeeSoldDTO(summaryInformation
						.getCoffeeSold()));
		summaryInformationDTO.setFastestAmmountOfTime(summaryInformation
				.getFastestAmmountOfTime());
		summaryInformationDTO.setSlowestAmmountOfTime(summaryInformation
				.getSlowestAmmountOfTime());
		summaryInformationDTO.setTotalAmmountOfTime(summaryInformation
				.getTotalAmmountOfTime());
		summaryInformationDTO
				.setMachinesSummary(convertToMachineSummaryDTO(summaryInformation
						.getMachinesSummary()));
		return summaryInformationDTO;

	}

	private static CoffeeSoldSummaryDTO convertToCoffeeSoldDTO(
			CoffeeSoldSummary coffeeSoldSummary) {

		return CoffeeSoldSummaryDTOFactory.create(coffeeSoldSummary);
	}

	private static Map<Integer, MachineSummaryDTO> convertToMachineSummaryDTO(
			Map<Integer, MachineSummary> machinesSummary) {
		Map<Integer, MachineSummaryDTO> machineSummaryDTOByNumber = Maps
				.newHashMap();
		for (Entry<Integer, MachineSummary> machineSummary : machinesSummary
				.entrySet()) {
			machineSummaryDTOByNumber.put(machineSummary.getKey(),
					MachineSummaryDTOFactory.create(machineSummary.getValue()));
		}

		return machineSummaryDTOByNumber;
	}

}
