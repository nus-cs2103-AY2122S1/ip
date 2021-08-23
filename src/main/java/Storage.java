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
        this.directoryPath = index == -1 ? null : Paths.get(this.filePath.substring(0, index));
    }

    private List<String> load() throws IOException {

        if (directoryPath != null && !Files.exists(directoryPath))
            Files.createDirectory(directoryPath);

        Path path = Paths.get(filePath);

        if (!Files.exists(path))
            Files.createFile(path);

        return Files.readAllLines(path);
    }
}
