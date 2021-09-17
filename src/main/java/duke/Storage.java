package duke;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Encapsulates the storage and access of the local file containing the user's tasks.
 */
public class Storage {
    /** The path to the task storage file. */
    private final Path DATA_PATH;

    /**
     * Constructs an instance of the Storage class.
     * Manages interactions with the storage file on the user's computer.
     *
     * @param filePath The file path to the storage file on the user's computer.
     */
    public Storage(String filePath) {
        final String HOME_PATH_STRING = System.getProperty("user.home");
        DATA_PATH = Paths.get(HOME_PATH_STRING, filePath);
    }

    /**
     * Loads in the data from the storage file on the user's computer.
     * Creates a new file if no existing storage file is found. An empty stream is returned if no existing
     * storage file is found (or there are no tasks in the existing storage file).
     *
     * @return A stream of strings representing the various tasks stored.
     * @throws DukeException If there is an issue creating or reading the storage file.
     */
    public Stream<String> load() throws DukeException {
        Stream<String> lines;
        if (!Files.exists(DATA_PATH)) {
            try {
                Files.createFile(DATA_PATH);
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }

        try {
            lines = Files.lines(DATA_PATH);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

        return lines;
    }

    /**
     * Writes to the storage file on the user's computer.
     * Rewrites the storage file with the string passed on the method.
     *
     * @param dataStr The data string to write to the file.
     * @throws DukeException If there is an issue writing to the storage file.
     */
    public void rewriteData(String dataStr) throws DukeException {
        try {
            Files.writeString(DATA_PATH, dataStr, StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
