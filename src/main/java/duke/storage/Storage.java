package duke.storage;

import static duke.parser.Parser.dateFormatter;
import static duke.parser.Parser.timeFormatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Encapsulates the loading of tasks from the file and the saving of tasks to the file
 */
public class Storage {

    private final String filePath;

    /**
     * Public constructor to initialize a Storage object with the path of the file to be saved to and loaded from.
     *
     * @param filePath Path of the file to be saved to and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the list of tasks from the file given in filePath.
     *
     * @return A list of tasks from the file.
     * @throws DukeException Throws exception if the file could not be found.
     */
    public ArrayList<Task> load() throws DukeException {
        File f = new File(filePath);
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(f); // create a Scanner using the File as the source
            while (sc.hasNext()) {
                String input = sc.nextLine();
                assert !input.equals("") : "User's input should not be empty.";
                String[] splitStr = input.split("\\|");
                Task t;
                if (splitStr[0].trim().equals("T")) {
                    t = new Todo(splitStr[2].trim());
                } else if (splitStr[0].trim().equals("E")) {
                    String[] dateTime = splitStr[3].trim().split("\\s+");
                    t = new Event(splitStr[2].trim(), timeFormatter(dateTime[1]), dateFormatter(dateTime[0]));
                } else {
                    String[] dateTime = splitStr[3].trim().split("\\s+");
                    t = new Deadline(splitStr[2].trim(), timeFormatter(dateTime[1]), dateFormatter(dateTime[0]));
                }
                list.add(t);
                if (splitStr[1].trim().equals("1")) {
                    t.markTaskDone();
                }
            }

        } catch (FileNotFoundException e) {
            throw new DukeException("Could not find file to load from.");
        }
        return list;
    }

    /**
     * Saves the current list of tasks to the file given in filePath.
     *
     * @param tasks A list of tasks to be saved to the file.
     * @throws IOException Throws exception if the file could not be opened.
     */
    public void saveToDisk(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.numberOfTasks(); i++) {
            fw.write(tasks.taskNumber(i).outputFormat() + '\n');
        }
        fw.close();
    }
}
