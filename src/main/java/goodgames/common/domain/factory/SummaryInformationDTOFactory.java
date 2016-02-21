package goodgames.common.domain.factory;

import goodgames.store.domain.SummaryInformation;
import goodgames.store.resource.SummaryInformationDTO;

public class SummaryInformationDTOFactory {

	public static SummaryInformationDTO create(
			SummaryInformation summaryInformation) {
		SummaryInformationDTO summaryInformationDTO = new SummaryInformationDTO();
		summaryInformationDTO.setAverageAmmountOfTime(summaryInformation
				.getAverageAmmountOfTime());
		summaryInformationDTO.setCoffeeSold(summaryInformation.getCoffeeSold());
		summaryInformationDTO.setFastestAmmountOfTime(summaryInformation
				.getFastestAmmountOfTime());
		summaryInformationDTO.setSlowestAmmountOfTime(summaryInformation
				.getSlowestAmmountOfTime());
		summaryInformationDTO.setTotalAmmountOfTime(summaryInformation
				.getTotalAmmountOfTime());
		summaryInformationDTO.setMachinesSummary(summaryInformation
				.getMachinesSummary());
		return summaryInformationDTO;

	}

}
