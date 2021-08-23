import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

public class Storage {

    private final String filePath;
    private final Path directoryPath;

    public Storage(String filePath) {
        this.filePath = filePath.replaceAll("\\|/", File.separator);
        int index = this.filePath.lastIndexOf(File.separator);
        this.directoryPath = Paths.get(this.filePath.substring(0, index));
    }
}
