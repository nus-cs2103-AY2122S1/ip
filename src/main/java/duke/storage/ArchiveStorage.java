package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import duke.util.Ui;

public class ArchiveStorage extends Storage {
    /**
     * Constructor for a new ArchiveStorage object.
     *
     * @param filePath The filepath to the save file for the application.
     */
    public ArchiveStorage(String filePath) {
        super(filePath);
    }

    /**
     * Appends and saves the input string to the instance file path.
     *
     * @param text The text to be saved.
     */
    @Override
    public void save(String text) {
        File file = new File(this.filePath);

        try {
            // Create parent directories if they do not exist
            file.getParentFile().mkdirs();

            // Append to file
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            Ui.printMessage(String.format("An error occurred while saving to archive file '%s'.", this.filePath));
        } catch (SecurityException e) {
            Ui.printMessage(String.format("Unable to write to archive file '%s'. "
                            + "Please check your directory and file permissions.",
                    this.filePath));
        }
    }

    /**
     * Loads the file specified by the instance file path.
     * Deletes the file contents after loading.
     *
     * @return The file contents as a list of strings.
     * @throws FileNotFoundException When file is not found at the specified path.
     */
    @Override
    public List<String> load() throws IOException {
        List<String> fileContents = Files.readAllLines(Paths.get(this.filePath), Charset.defaultCharset());
        new PrintWriter(this.filePath).close(); // Delete file contents after loading archive file
        return fileContents;
    }
}
