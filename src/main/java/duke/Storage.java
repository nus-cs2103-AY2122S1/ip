package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

class Storage {
    private final String fileName;
    private final FileWriter writer;

    Storage(String fileName) throws IOException {
        this.fileName = fileName;
        writer = new FileWriter(fileName, true);
    }

    List<String> readAllLines() throws IOException {
        return Files.readAllLines(new File(fileName).toPath());
    }

    void write(String s) throws IOException {
        writer.write(s + '\n');
    }

    void close() throws IOException {
        writer.close();
    }
}
