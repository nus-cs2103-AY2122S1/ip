package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/** A storage class serves as a database system. */
public class Storage {
    private Path filePath;

    /**
     * Constructs a storage that uses the specified file to load and store data.
     *
     * @param filePath The path of the file.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);

        try {
            if (Files.notExists(this.filePath)) {
                if (this.filePath.getParent() != null) {
                    Files.createDirectories(this.filePath.getParent());
                }
                Files.createFile(this.filePath);
            }
        } catch (IOException e) {
            // Do nothing
        }
    }

    /**
     * Loads data from the file.
     *
     * @return A list of strings, where each line is stored as one element.
     * @throws IOException If an I/O error occurs.
     */
    public List<String> load() throws IOException {
        FileReader fin = new FileReader(filePath.toString());
        BufferedReader bin = new BufferedReader(fin);

        String line;
        List<String> data = new ArrayList<>();

        while ((line = bin.readLine()) != null) {
            if (line.isEmpty()) {
                break; // end of line
            }

            data.add(line);
        }

        bin.close();

        return data;
    }

    /**
     * Stores data to the file.
     *
     * @param data The data to be stored.
     * @throws IOException If an I/O error occurs.
     */
    public void store(String data) throws IOException {
        FileWriter fout = new FileWriter(filePath.toString());
        BufferedWriter bout = new BufferedWriter(fout);

        bout.write(data);
        bout.close();
    }
}
