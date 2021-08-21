package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

class Storage {
    private final String filePath;
    private final FileWriter writer;

    Storage(String filePath) throws IOException {
        this.filePath = filePath;
        writer = new FileWriter(filePath, true);
    }

    List<String> readAllLines() throws IOException {
        return Files.readAllLines(new File(filePath).toPath());
    }

    void write(String s) throws IOException {
        writer.write(s + '\n');
    }

    void close() throws IOException {
        writer.close();
    }
}
