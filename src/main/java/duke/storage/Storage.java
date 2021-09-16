package duke.storage;

import java.io.IOException;
import java.util.List;

public abstract class Storage {
    protected String filePath;

    /**
     * Constructor for a new Storage object.
     *
     * @param filePath The filepath to the save file for the application.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public abstract void save(String text);

    public abstract List<String> load() throws IOException;
}
