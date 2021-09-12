package duke.storage;

import duke.exceptions.AuguryException;

/**
 * The {@code Settings} class contains user preferences.
 */
public class Settings {
    private Storage storage;
    private String theme;

    /**
     * Initializes a {@code Settings} instance with a {@code Storage} to read/write user preferences.
     *
     * @param storage {@code Storage} instance that handles read/write of user preferences.
     */
    public Settings(Storage storage) {
        this.theme = "light";
        this.storage = storage;
    }

    /**
     * Returns the current theme of the application
     * @return {@code String} of current theme
     */
    public String getTheme() {
        return this.theme;
    }

    /**
     * Sets the current theme of the application
     * @param t {#code String} of theme to write
     * @throws AuguryException if file error occurs
     */
    public void setTheme(String t) throws AuguryException {
        this.theme = t;
        storage.saveSettingsToStorage(this);
    }

    /**
     * Returns the current theme of the application
     */
    @Override
    public String toString() {
        return this.theme;
    }
}
