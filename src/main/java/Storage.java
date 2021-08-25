import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * This class encapsulates the logic for saving and writing to the hard disk.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the input string to the instance file path.
     * @param text The text to be saved.
     */
    public void save(String text) {
        File file = new File(this.filePath);

        try {
            // Create parent directories if they do not exist
            file.getParentFile().mkdirs();

            FileWriter fw = new FileWriter(this.filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            Ui.printMessage(String.format("An error occurred while saving to '%s'.", this.filePath));
        } catch (SecurityException e) {
            Ui.printMessage(String.format("Unable to write to '%s'. Please check your directory and file permissions.",
                    this.filePath));
        }

    }

    /**
     * Loads the file specified by the instance file path.
     * @return The file contents as a BufferedReader
     * @throws FileNotFoundException When file is not found at the specified path
     */
    public List<String> load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(this.filePath), Charset.defaultCharset());
        return lines;
    }
}
