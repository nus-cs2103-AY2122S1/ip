package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * This class encapsulates reading and writing of the savefile for the Duke bot.
 */
public class Storage {
    private final File saveFile;
    private final FileParser parser;
    private final Path filePath;

    /**
     * Creates a Storage object.
     *
     * @param filePath File path to read saved data from.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
        saveFile = new File(filePath.toString());
        parser = new FileParser();
    }

    /**
     * Loads data from savefile.
     * Starts the session with an empty tasklist if there was an error found.
     *
     * @return A Tasklist object containing all the information from the save file as Task objects.
     * @throws DukeException Throws an exception if the file could not be read or no file was found.
     */
    public Tasklist load() throws DukeException {
        Tasklist taskList = new Tasklist();
        assert(saveFile != null);
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
     *
     * @param taskList The Tasklist object in the current user's session.
     * @throws DukeException Throws an exception if an error occurs while writing to file.
     */
    public void save(Tasklist taskList) throws DukeException {
        assert(filePath != null);
        try {
            String writableData = taskList.toWritable();
            FileWriter fileWriter = new FileWriter(this.filePath.toString());
            fileWriter.write(writableData);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("your data could not be saved.");
        }

    }
}
