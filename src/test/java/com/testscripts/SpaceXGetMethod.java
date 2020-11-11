package com.testscripts;

import org.testng.annotations.Test;

import com.actions.SpaceXActions;
import com.automation.frameworkUtils.FrameworkException;
import com.automation.frameworkUtils.FrameworkUtils;

public class SpaceXGetMethod extends FrameworkUtils {

	@Test
	public static void spaceXGetMethod() {
		try {
			loadProperties();
			SpaceXActions.restConnection(p.getProperty("url"));
			SpaceXActions.getLatestSpaceDetails();
		} catch (Exception exception) {
			new FrameworkException(exception);
		}
	}

}
