package duke;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class Storage {
    /** The user's home path string on their computer. */
    private final String HOME_PATH_STRING = System.getProperty("user.home");
    /** The file path string to find store task info to. */
    private String filePathString;
    /** The path to the task storage file. */
    private Path dataPath;

    /**
     * Constructs an instance of the Storage class.
     * Manages interactions with the storage file on the user's computer.
     *
     * @param filePath The file path to the storage file on the user's computer.
     */
    public Storage(String filePath) {
        this.filePathString = filePath;
        dataPath = Paths.get(HOME_PATH_STRING, filePathString);
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
        Stream<String> lines = Stream.empty();
        if (!Files.exists(dataPath)) {
            try {
                Files.createFile(dataPath);
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        } else {
            try {
                lines = Files.lines(dataPath);
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
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
            Files.writeString(dataPath, dataStr, StandardCharsets.ISO_8859_1);
        }
        catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
