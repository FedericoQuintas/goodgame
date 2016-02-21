package goodgames.config;

import goodgames.order.service.OrderService;
import goodgames.order.service.OrderServiceImpl;
import goodgames.store.service.StoreService;
import goodgames.store.service.StoreServiceImpl;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@EnableScheduling
public class MVCConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public OrderService orderService() {
		return new OrderServiceImpl();
	}

	@Bean
	public StoreService storeService() {
		return new StoreServiceImpl();
	}

	@Bean
	public GetPropertyValues propertyValues() throws IOException {
		return new GetPropertyValues();
	}
}
