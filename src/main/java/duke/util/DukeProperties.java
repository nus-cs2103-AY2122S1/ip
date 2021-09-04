package duke.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import duke.exception.DukeException;

/**
 * Represent a helper class to deal with properties files.
 */
public class DukeProperties {
    private static final String PROPERTIES_FILE_PATH = "duke.properties";

    /**
     * Returns the value of the key passed in as defined in duke.properties.
     *
     * @param key The key to be queried.
     * @return The value if present. If the value is not present, a null object is returned.
     * @throws DukeException If the file cannot be found or if an <code>IOException</code> occurred when processing
     * the file.
     */
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

    /**
     * Returns the value of the key passed in as defined in duke.properties. Defaults to <code>defaultValue</code> if
     * the key was not found or if an exception was thrown.
     *
     * @param key The key to be queried.
     * @param defaultValue The default value to fall back to.
     * @return The value if found. Else, <code>defaultValue</code> is returned.
     */
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
