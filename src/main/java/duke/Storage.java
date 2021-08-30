package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Represents a persistent storage that can be written to and read from.
 */
class Storage {
    private final String fileName;
    private final FileWriter writer;

    /**
     * Creates storage.
     *
     * @param fileName File name to use.
     * @throws IOException If file cannot be opened or created.
     */
    Storage(String fileName) throws IOException {
        this.fileName = fileName;
        writer = new FileWriter(fileName, true);
    }

    /**
     * Reads file and returns lines.
     *
     * @return list of lines in file.
     * @throws IOException If file cannot be read.
     */
    List<String> readAllLines() throws IOException {
        return Files.readAllLines(new File(fileName).toPath());
    }

    /**
     * Writes line to file.
     * Newline will be appended after writing string.
     *
     * @param s String to write.
     * @throws IOException If I/O error occurs while writing.
     */
    void write(String s) throws IOException {
        writer.write(s + '\n');
    }

    /**
     * Closes file after flushing output.
     *
     * @throws IOException If I/O error occurs.
     */
    void close() throws IOException {
        writer.close();
    }
}
