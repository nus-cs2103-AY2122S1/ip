package duke.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import duke.exception.DukeException;

public class DukeProperties {
    private static final String PROPERTIES_FILE_PATH = "duke.properties";

    public static String getProperty(String key) throws DukeException {
        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(PROPERTIES_FILE_PATH));
        } catch (FileNotFoundException e) {
            throw new DukeException(
                    String.format("File '%s' not found. Please make sure that the file exists.", PROPERTIES_FILE_PATH));
        } catch (IOException e) {
            throw new DukeException(String.format("An error occurred when trying to load %s:\n\t%s\n",
                    PROPERTIES_FILE_PATH, e.getMessage()));
        }
        return appProps.getProperty(key);
    }

    public static String getPropertyOrDefault(String key, String defaultValue) {
        String valueIfPresent;
        try {
            valueIfPresent = getProperty(key);
        } catch (DukeException e) {
            return defaultValue;
        }

        if (valueIfPresent == null) {
            // Key-value pair was not defined in properties file
            return defaultValue;
        }
        return valueIfPresent;
    }
}
