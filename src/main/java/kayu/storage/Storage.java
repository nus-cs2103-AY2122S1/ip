package kayu.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import kayu.exception.StorageException;

/**
 * Handles the reading and writing of entities into files.
 */
public abstract class Storage<T> {

    // Error message templates.
    protected static final String ERROR_UNABLE_TO_CREATE_DIRECTORY = "Load/save directory ./%s cannot be created.";
    protected static final String ERROR_UNABLE_TO_CREATE_FILE = "Load/save file ./%s cannot be created.";
    protected static final String ERROR_UNABLE_TO_LOAD_PATH = "Path ./%s cannot be accessed/loaded.";
    protected static final String ERROR_UNABLE_TO_SAVE = "Error updating to file.";

    // Assert error templates.
    protected static final String ASSERT_FAIL_IMPROPER_FILEPATH = "Filepath specified is not legitimate.";

    // Default task file directory.
    private final String directoryPath;
    private final String filePath;

    /**
     * Initializes the {@link kayu.storage.Storage}.
     *
     * @param directoryPath Directory/folder path string.
     * @param filePath File path string.
     */
    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    protected static String extractDirectoryPath(String fullPath) {
        assert (fullPath.contains("/")) : ASSERT_FAIL_IMPROPER_FILEPATH;
        int splitIdx = fullPath.lastIndexOf('/');

        return fullPath.substring(0, splitIdx + 1); // exclusive end
    }

    /**
     * Loads the file based on specified directory, extracts and decodes the
     * {@link kayu.task.Task} instances for return.
     *
     * @return List of {@link kayu.task.Task}.
     * @throws StorageException If unable to read/write to file.
     */
    public List<T> load() throws StorageException {
        initializeDirectoryAndFile();
        List<String> lines = readFile();
        return decodeAll(lines);
    }

    private void initializeDirectoryAndFile() throws StorageException {
        try {
            initializeDirectory();
            initializeFile();

        } catch (IOException exception) {
            throw new StorageException(String.format(ERROR_UNABLE_TO_LOAD_PATH, filePath));
        }
    }

    private void initializeDirectory() throws StorageException {
        File directory = new File(directoryPath);
        if (!directory.exists() && !directory.mkdir()) {
            throw new StorageException(String.format(ERROR_UNABLE_TO_CREATE_DIRECTORY, directoryPath));
        }
    }

    private void initializeFile() throws StorageException, IOException {
        File file = new File(filePath);
        if (!file.exists() && !file.createNewFile()) {
            throw new StorageException(String.format(ERROR_UNABLE_TO_CREATE_FILE, filePath));
        }
    }

    private List<String> readFile() throws StorageException {
        try {
            Path filePath = Paths.get(this.filePath);
            return Files.readAllLines(filePath);

        } catch (IOException exception) {
            throw new StorageException(String.format(ERROR_UNABLE_TO_LOAD_PATH, filePath));
        }
    }

    private List<T> decodeAll(List<String> lines) throws StorageException {
        return lines.stream()
                .map(this::decode)
                .collect(Collectors.toList());
    }

    protected abstract T decode(String encoded) throws StorageException;

    private List<String> encodeAll(List<T> list) {
        return list.stream()
                .map(this::encode)
                .collect(Collectors.toList());
    }

    protected abstract String encode(T decoded);

    /**
     * Saves the current list of {@link kayu.task.Task} into file memory.
     *
     * @param list List of {@link kayu.task.Task} to write to file.
     * @throws StorageException If unable to read/write to file.
     */
    public void save(List<T> list) throws StorageException {
        try {
            Path filePath = Paths.get(this.filePath);
            List<String> lines = this.encodeAll(list);
            Files.write(filePath, lines);

        } catch (IOException exception) {
            throw new StorageException(ERROR_UNABLE_TO_SAVE);
        }
    }
}
