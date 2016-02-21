package goodgames.common.adapter;

import goodgames.common.domain.factory.SummaryInformationDTOFactory;
import goodgames.store.domain.SummaryInformation;
import goodgames.store.resource.SummaryInformationDTO;
import goodgames.store.service.StoreService;

import javax.annotation.Resource;

public class SummaryInformationAdapter {

	@Resource
	private StoreService storeService;

	public SummaryInformationDTO getSummaryInformation(
			Integer numberOfEspresso, Integer numberOfLatte,
			Integer numberOfCapuccino) {

		SummaryInformation summaryInformation = storeService
				.getSummaryInformation(numberOfEspresso, numberOfLatte,
						numberOfCapuccino);

		return SummaryInformationDTOFactory.create(summaryInformation);
	}

}
