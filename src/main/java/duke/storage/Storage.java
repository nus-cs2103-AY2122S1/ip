package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stores a file and its lines.
 *
 * @author botr99
 */
public class Storage {
    private Path filePath;
    private ArrayList<String> lines;

    /**
     * Constructs a storage that reads in lines from the specified
     * file location.
     *
     * @param fileLocation The file location to read the lines from.
     * @throws IOException When an error occurs when reading to the file.
     */
    public Storage(String fileLocation) throws IOException {
        Path filePath = Path.of(fileLocation);
        Files.createDirectories(filePath.getParent());
        if (checkFileDoesNotExist(filePath)) {
            Files.createFile(filePath);
        }
        this.filePath = filePath;
        this.lines = readLines();

        boolean doesFileExist = checkFileExist(filePath);
        assert doesFileExist;
    }

    private boolean checkFileExist(Path filePath) {
        return Files.exists(filePath);
    }

    private boolean checkFileDoesNotExist(Path filePath) {
        return !Files.exists(filePath);
    }

    private boolean checkLinesIsUpdated() throws IOException {
        return lines.equals(readLines());
    }

    private ArrayList<String> readLines() throws IOException {
        Stream<String> linesStream = Files.lines(filePath);
        return linesStream.collect(Collectors.toCollection(ArrayList::new));
    }

    private void saveLinesToStorage() throws IOException {
        // store updated lines to the file path
        Files.write(filePath, lines);

        boolean isLinesUpdated = checkLinesIsUpdated();
        assert isLinesUpdated;
    }

    /**
     * Returns the lines stored in the file.
     *
     * @return The lines as an ArrayList of strings.
     */
    public ArrayList<String> getLines() {
        return lines;
    }

    /**
     * Saves the added line and updates the storage.
     *
     * @param line The string to be added at the last line.
     * @throws IOException When an error occurs when writing to the file.
     */
    public void addLine(String line) throws IOException {
        lines.add(line);
        saveLinesToStorage();
    }

    /**
     * Removes a line specified by the line number and updates the storage.
     *
     * @param lineNumber The line number to remove the line.
     * @throws IOException When an error occurs when writing to the file.
     */
    public void removeLine(int lineNumber) throws IOException {
        lines.remove(lineNumber - 1);
        saveLinesToStorage();
    }

    /**
     * Modifies a line specified by the line number and updates the storage.
     *
     * @param lineNumber The line number to be modified in the file.
     * @param line The string to be replaced with at that line.
     * @throws IOException When an error occurs when writing to the file.
     */
    public void editLine(int lineNumber, String line) throws IOException {
        lines.set(lineNumber - 1, line);
        saveLinesToStorage();
    }

}
