package com.epam.mentoring.utils;

import java.io.File;
import org.testng.Reporter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	private static final String sFileName = "configurations.properties";
	private static String sDirSeparator = System.getProperty("file.separator");
	private static Properties props = new Properties();

	static {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		File currentDir = new File(".");
		try {
			String sFilePath = currentDir.getCanonicalPath() + sDirSeparator + sFileName;

			FileInputStream ins = new FileInputStream(sFilePath);
			props.load(ins);

		} catch (FileNotFoundException e) {

		Reporter.log("File not found!");
			
			e.printStackTrace();

		} catch (IOException ex) {

			Reporter.log("IO Error!");

			ex.printStackTrace();

		}
	}

	public static String getBaseUrl() {
		return props.getProperty("baseUrl");
	}

}
