package duke;

import java.io.File;
import java.io.IOException;

public class Storage {
    private File data;

    public Storage(String pathname, String dir) throws IOException {
        File directory = new File(dir);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File data = new File(pathname);
        if (!data.exists()) {
            data.createNewFile();
        }
        this.data = data;
    }

    public File load() {
        return this.data;
    }
}
