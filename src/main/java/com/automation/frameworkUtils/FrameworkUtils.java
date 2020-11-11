package com.automation.frameworkUtils;

import java.io.FileReader;
import java.util.Properties;

public class FrameworkUtils {

	public static Properties p = null;

	public static void loadProperties() {
		try {
			FileReader reader = new FileReader("testdata.properties");
			p = new Properties();
			p.load(reader);

		} catch (Exception exception) {
			new FrameworkException(exception);
		}
	}

}
