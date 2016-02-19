package goodgames.common.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertyValues {

	private InputStream inputStream;
	private Integer numberOfMachines;

	public Integer getPropValues() throws IOException {

		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(
					propFileName);

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
		} finally {
			inputStream.close();
		}
		return numberOfMachines;
	}

	public Integer getNumberOfMachines() {
		return numberOfMachines;
	}

}