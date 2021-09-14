package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a Storage object that handles file IO for tasks.
 */
public class Storage {
    private final Path path;

    public Storage() {
        this.path = Paths.get(".", "db.txt");
    }

    public Storage(Path path) {
        this.path = path;
    }

    /**
     * Saves given string data to file specified at instantiation.
     * If file does not exist, it will be created.
     *
     * @param data The string data to be saved.
     * @throws IOException If something goes wrong with File operations.
     */
    public void save(String data) throws IOException {
        boolean fileExists = Files.exists(path);

        if (!fileExists) {
            Files.createFile(path);
        }
        BufferedWriter file = Files.newBufferedWriter(path);
        file.write(data);
        file.close();
    }

    /**
     * Returns a List of Task by parsing file specified at instantiation.
     * If file does not exist, empty List will be returned.
     *
     * @return List of Task saved in file.
     * @throws IOException       If something goes wrong with File operations.
     * @throws DateTimeException If file's date data is corrupted.
     */
    public List<String> load() throws IOException, DateTimeException {
        boolean fileExists = Files.exists(path);

        if (!fileExists) {
            return new ArrayList<>();
        }

        BufferedReader file = Files.newBufferedReader(path);
        List<String> lines = file.lines().collect(Collectors.toList());
        file.close();
        return lines;
    }
}
