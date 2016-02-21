package goodgames.store.resource;

import goodgames.store.service.StoreService;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("storeResource")
@RequestMapping("/store")
public class StoreResource {

	@Resource
	private StoreService storeService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public SummaryInformationDTO store(
			@RequestParam("espresso") Integer numberOfEspresso,
			@RequestParam("latte") Integer numberOfLatte,
			@RequestParam("capuccino") Integer numberOfCapuccino) {
		return storeService.getSummaryInformation(numberOfEspresso,
				numberOfLatte, numberOfCapuccino);
	}
}
