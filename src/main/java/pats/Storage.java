package pats;

import static java.util.Objects.requireNonNull;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides methods to create local file and perform I/O operations with it.
 */
public class Storage {
    // About save file
    private static final String[] SAVE_DIRECTORY = new String[] {"data"};
    private static final String SAVE_FILENAME = "duke.txt";
    private static Path savePath;

    private boolean isInitialized;

    public Storage() {
        this.isInitialized = false;
    }

    /**
     * Initializes save path with default save path.
     *
     * @throws PatsException if failed to create file
     */
    protected void initialize() throws PatsException {
        Path path = Arrays.stream(SAVE_DIRECTORY)
                .map(Paths::get)
                .reduce(Paths.get(""), Path::resolve)
                .resolve(SAVE_FILENAME);

        try {
            if (!Files.exists(path)) {
                createFile(path);
            }
        } catch (IOException e) {
            throw new PatsException(ExceptionType.FAIL_TO_CREATE_FILE, e.getMessage());
        }

        savePath = path;
        assert Files.exists(savePath) : "Failed to create file at " + savePath;
        this.isInitialized = true;
    }

    private void createFile(Path path) throws IOException {
        Path p = Paths.get("");
        for (String s : SAVE_DIRECTORY) {
            p = p.resolve(s);
            if (!Files.exists(p)) {
                Files.createDirectory(p);
            }
        }

        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    /**
     * Appends a line of text to the end of save file.
     *
     * @param textToWrite text to write to save file
     * @throws PatsException if failed to write to save file
     */
    public void writeLine(String textToWrite) throws PatsException {
        requireNonNull(textToWrite, "text to write is null");

        if (!this.isInitialized) {
            return;
        }

        try {
            FileWriter fileWriter = new FileWriter(savePath.toFile(), true);
            fileWriter.write(textToWrite);
            fileWriter.close();
        } catch (IOException e) {
            throw new PatsException(ExceptionType.FAIL_TO_WRITE, e.getMessage());
        }
    }

    /**
     * Removes a line of text in save file
     *
     * @param lineIndex index of line to remove
     * @throws PatsException if failed to write to save file
     * @throws IndexOutOfBoundsException if line index is negative
     */
    public void removeLine(int lineIndex) throws PatsException {
        if (!this.isInitialized) {
            return;
        }

        if (lineIndex < 0) {
            throw new IndexOutOfBoundsException("line index is negative");
        }

        try {
            List<String> originalContent = Files.readAllLines(savePath);
            originalContent.remove(lineIndex);
            Files.write(savePath, originalContent);
        } catch (IOException e) {
            throw new PatsException(ExceptionType.FAIL_TO_WRITE, e.getMessage());
        }
    }

    /**
     * Removes all contents in save file
     *
     * @throws PatsException if failed to write to save file
     */
    public void removeAll() throws PatsException {
        if (!this.isInitialized) {
            return;
        }

        try {
            Files.write(savePath, new ArrayList<String>());
        } catch (IOException e) {
            throw new PatsException(ExceptionType.FAIL_TO_WRITE, e.getMessage());
        }
    }

    /**
     * Changes a line in save file
     *
     * @param lineIndex index of line to change
     * @param textToWrite new text at the line
     * @throws PatsException if failed to write to save file
     * @throws IndexOutOfBoundsException if line index is negative or text to write is null
     */
    public void setLine(int lineIndex, String textToWrite) throws PatsException {
        if (!this.isInitialized) {
            return;
        }

        if (lineIndex < 0) {
            throw new IndexOutOfBoundsException("line index cannot be negative");
        }
        requireNonNull(textToWrite, "text to write is null");

        try {
            List<String> originalContent = Files.readAllLines(savePath);
            originalContent.set(lineIndex, textToWrite);
            Files.write(savePath, originalContent);
        } catch (IOException e) {
            throw new PatsException(ExceptionType.FAIL_TO_WRITE, e.getMessage());
        }
    }

    /**
     * Reads all lines from save file.
     *
     * @return the lines from save file as a list
     * @throws PatsException if failed to read from save file
     */
    protected List<String> getFileContents() throws PatsException {
        if (!this.isInitialized) {
            return new ArrayList<>();
        }

        try {
            return Files.readAllLines(savePath);
        } catch (IOException e) {
            throw new PatsException(ExceptionType.FAIL_TO_READ, e.getMessage());
        }
    }
}
