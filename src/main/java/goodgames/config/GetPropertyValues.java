package goodgames.config;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertyValues {

	private InputStream inputStream;
	private Integer numberOfMachines;

	private static GetPropertyValues INSTANCE = null;

	private GetPropertyValues() {
		try {
			Properties prop = new Properties();
			String propFileName = "/config/config.properties";
			inputStream = getClass().getResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '"
						+ propFileName + "' not found in the classpath");
			}

			numberOfMachines = Integer.valueOf(prop
					.getProperty("number_of_machines"));

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}

	public static GetPropertyValues getInstance() {
		if (INSTANCE == null || INSTANCE.getNumberOfMachines() == null) {
			INSTANCE = new GetPropertyValues();
		}

		return INSTANCE;
	}

	public Integer getNumberOfMachines() {
		return numberOfMachines;
	}

}