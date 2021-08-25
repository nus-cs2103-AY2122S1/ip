package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage {
    private final File saveFile;
    private final FileParser parser;
    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
        saveFile = new File(filePath.toString());
        parser = new FileParser();
    }

    /**
     * Loads data from savefile.
     * Starts the session with an empty tasklist if there was an error found.
     *
     * @return
     * @throws DukeException
     */
    public Tasklist load() throws DukeException {
        Tasklist taskList = new Tasklist();
        try {
            Scanner s = new Scanner(saveFile);
            while (s.hasNext()) {
                taskList.addTask(parser.parse(s.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("There was no savefile found. Starting session with an empty list.");
        }
        return taskList;
    }

    /**
     * Accepts the tasklist and writes to file.
     * Throws an exception if an error occurs.
     *
     * @param taskList
     * @throws IOException
     */
    public void save(Tasklist taskList) throws IOException {
        String writableData = taskList.toWritable();
        FileWriter fileWriter = new FileWriter(this.filePath.toString());
        fileWriter.write(writableData);
        fileWriter.close();
    }
}
