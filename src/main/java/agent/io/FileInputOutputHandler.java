package agent.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Encapsulates functionality for reading and writing strings to and from a specified target file.
 *
 * @author kevin9foong
 */
public class FileInputOutputHandler {
    private final BufferedReader br;
    private final String filePath;

    /**
     * Instantiates a <code>FileIO</code> object.
     * Creates directories and files at the filepath provided if it does not exist.
     *
     * @param filePath filepath of file to read from and write to.
     * @throws IOException thrown when error reading or writing to target file occurs.
     */
    public FileInputOutputHandler(String filePath) throws IOException {
        this.filePath = filePath;

        File tasksFile = new java.io.File(filePath);

        // handle the case when the file does not initially exist
        if (!tasksFile.exists()) {
            if (!tasksFile.getParentFile().exists()) {
                tasksFile.getParentFile().mkdirs();
            }
            tasksFile.createNewFile();
        }

        this.br = new BufferedReader(new FileReader(tasksFile));
    }

    /**
     * Reads target file line by line.
     *
     * @return List of strings containing each line of target file.
     * @throws IOException thrown when error reading from file occurs.
     */
    public List<String> readLinesFromFile() throws IOException {
        List<String> fileLines = new LinkedList<>();
        String fileLine = br.readLine();
        while (fileLine != null && !fileLine.isBlank()) {
            fileLines.add(fileLine);
            fileLine = br.readLine();
        }
        return fileLines;
    }

    /**
     * Writes each String of given list on a new line to the target file.
     *
     * @param lines List of strings to write to target file.
     * @throws IOException thrown when error writing to file occurs.
     */
    public void saveLinesToFile(List<String> lines) throws IOException {
        try (FileWriter fw = new FileWriter(this.filePath)) {
            for (String line : lines) {
                fw.write(line);
                fw.write("\n");
            }
        }
    }
}
