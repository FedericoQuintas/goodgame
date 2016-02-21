package goodgames.store.service;

import goodgames.store.resource.SummaryInformationDTO;

public interface StoreService {

	SummaryInformationDTO getSummaryInformation(Integer numberOfEspresso,
			Integer numberOfLatte, Integer numberOfCapuccino);

}
