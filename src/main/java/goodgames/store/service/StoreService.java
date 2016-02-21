package goodgames.store.service;

import goodgames.store.domain.SummaryInformation;

public interface StoreService {

	SummaryInformation getSummaryInformation(Integer numberOfEspresso,
			Integer numberOfLatte, Integer numberOfCapuccino);

}
