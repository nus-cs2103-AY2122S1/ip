package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

/**
 * Represents saved data for Duke.
 */
public class Storage {
    private File storage;
    private String fileName;

    private static final int DESCRIPTION = 0;
    private static final int DATE_TIME = 1;

    /**
     * Class constructor that constructs a Storage object.
     *
     * @param fileName File name to access file in directory.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
        this.storage = new File(fileName);
    }

    private void obtainFile() throws DukeException {
        try {
            this.storage.getParentFile().mkdirs();
            this.storage.createNewFile();
        } catch (IOException error) {
            throw new DukeException("there was a error creating a file!");
        }
    }
    /**
     * Loads a TaskList object based on the data saved within Storage. If data does not exist, create a new file
     * within Storage to store future data and return an empty TaskList.
     *
     * @return The list of tasks the user saved in Storage.
     * @throws DukeException If the user did not create a folder named "data" within the main project directory.
     */
    public TaskList load() throws DukeException {
        obtainFile();
        try {
            // Initialisation of required objects
            Scanner fileScanner = new Scanner(this.storage);
            TaskList taskList = new TaskList();

            while (fileScanner.hasNext()) {
                String fileData = fileScanner.nextLine();
                Parser parser = new Parser(fileData);

                //Compute and generate TaskList based on scanned data
                if (parser.isDone()) {
                    taskList.done(parser.getSecondPartInInt());
                } else if (parser.isToDo()) {
                    ToDo task = new ToDo(parser.getSecondPart());
                    taskList.add(task);
                } else if (parser.isDeadline()) {
                    Deadline task = new Deadline(parser.splitSecondPartForDeadline()[DESCRIPTION],
                            parser.splitSecondPartForDeadline()[DATE_TIME]);
                    taskList.add(task);
                } else if (parser.isEvent()) {
                    Event task = new Event(parser.splitSecondPartForEvent()[DESCRIPTION],
                            parser.splitSecondPartForEvent()[DATE_TIME]);
                    taskList.add(task);
                } else if (parser.isDelete()) {
                    taskList.delete(parser.getSecondPartInInt());
                }
            }

            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("we could not find your file.");
        }
    }

    private void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.fileName, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Saves user command into Storage.
     *
     * @param history The text command given by the user.
     * @throws DukeException If the IO operation fails.
     */
    public void save(String history) throws DukeException{
        try {
            appendToFile(history + System.lineSeparator());
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
