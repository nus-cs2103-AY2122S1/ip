package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.RecurringTask;
import duke.task.Task;
import duke.task.ToDo;


/**
 * Storage helper for storage related tasks.
 */
public class Storage {

    private final String FILEPATH;
    private final ArrayList<Task> LIST = new ArrayList<>();


    /**
     * Constructor for Storage class.
     *
     * @param filepath path for saving and loading
     */
    public Storage(String filepath) {
        this.FILEPATH = filepath;
    }

    /**
     * Return list of task loaded from save file.
     *
     * @return List of Tasks
     * @throws DukeException Exceptions for file not found
     */
    public ArrayList<Task> load() throws DukeException {
        File file = new File(FILEPATH);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Task t = processData(data);
                if (t != null) {
                    LIST.add(t);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("File is not found");
        }
        return LIST;
    }

    private Task processData(String str) throws DukeException {
        Scanner stringProcessor = new Scanner(str);
        stringProcessor.useDelimiter("\\|");
        try {
            String type = stringProcessor.next();
            switch (type) {
            case "T":
                return new ToDo(stringProcessor.next(), stringProcessor.next());
            case "D":
                return new Deadline(stringProcessor.next(), stringProcessor.next(), stringProcessor.next());
            case "E":
                return new Event(stringProcessor.next(), stringProcessor.next(), stringProcessor.next());
            case "R":
                return new RecurringTask(stringProcessor.next(), stringProcessor.next(),
                        stringProcessor.next(), stringProcessor.next());
            default:
                return null;
            }
        } catch (NoSuchElementException e) {
            throw new DukeException("Save file has invalid format.");
        }
    }

    protected void saveFile(ArrayList<Task> list) throws DukeException {
        try {
            File file = new File(FILEPATH);
            file.getParentFile().mkdir();
            file.createNewFile();
            assert file.exists();
            FileWriter writer = new FileWriter(file);
            writer.write(list.stream().map(Task::saveString).reduce(
                "", (string1, string2) -> string1 + string2 + "\n"));
            writer.close();
        } catch (IOException e) {
            throw new DukeException("IO Exception File Cannot Be Found");
        }
    }

}

