package de.himberger.jackson.builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class TestData {

	public static InputStream get(String dataSetName) {
		String absPath = "src/test/data/" + dataSetName + ".json";
		File dataSetFile = new File(absPath);
		if (!dataSetFile.exists()) {
			throw new IllegalArgumentException("A Data set with name "
					+ dataSetName + " does not exist at "
					+ dataSetFile.getAbsolutePath());
		}
		try {
			return new FileInputStream(dataSetFile);
		} catch (Exception e) {
			throw new RuntimeException("Error while loading data set "
					+ dataSetName, e);
		}
	}
}
