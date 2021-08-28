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

public class Storage {
    private Path filePath;

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

    public void store(String data) throws IOException {
        FileWriter fout = new FileWriter(filePath.toString());
        BufferedWriter bout = new BufferedWriter(fout);

        bout.write(data);
        bout.close();
    }
}
