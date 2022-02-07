package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.EmptyValueException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private final String filePath;
    private File storage;

    /**
     * Constructor of Storage.
     * Creates necessary folder or file if they do not exist.
     *
     * @param filePath File to be handled.
     */
    public Storage(String filePath) {
        this.filePath = filePath;

        try {
            File storage = new File(filePath);
            // check if directory exists
            File dir = storage.getParentFile();
            if (!dir.exists()) {
                dir.mkdir();
            }
            // check if file exists
            if (!storage.exists()) {
                storage.createNewFile();
            }
            assert storage.exists() : "filepath should be validated";
            this.storage = storage;
        } catch (IOException e) {
            System.out.println(Ui.showError(e.getMessage()));
        }
    }


    /**
     * Load tasks from file to targeted task list.
     *
     * @return List of Task generated from the file.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner readFile = new Scanner(storage);
            while (readFile.hasNextLine()) {
                String task = readFile.nextLine();
                if (!task.isBlank()) {
                    parseTask(task, tasks);
                }
            }
        } catch (FileNotFoundException | DukeException e) {
            System.out.println(Ui.showError(e.getMessage()));
        }
        return tasks;
    }

    /**
     * Used in this class to parse each line in file into a Task.
     *
     * @param str Line of the file.
     * @param tasks Target list of tasks.
     */
    public void parseTask(String str, List<Task> tasks) throws DukeException {
        Scanner sc = new Scanner(str);
        String category = sc.next();
        String[] input = sc.nextLine().substring(5).split("/");
        String des = input[0].strip();
        String time = "";
        if (!category.equals("T")) {
            time = input[1].strip();
            assert !time.isBlank(): "time should not be empty";
        }
        if (des.equals("")) {
            throw new EmptyValueException();
        }

        switch (category) {
        case "T":
            tasks.add(new Todo(des));
            break;
        case "D": {
            LocalDate ld = LocalDate.parse(time);
            tasks.add(new Deadline(des, ld));
            break;
        }
        case "E": {
            LocalDate ld = LocalDate.parse(time);
            tasks.add(new Event(des, ld));
            break;
        }
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Save the current task list to the file.
     *
     * @param tasks The task list in the programme.
     */
    public void save(List<Task> tasks) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            for (Task task : tasks) {
                myWriter.write(task.toStoredString() + "\n");
                System.out.println(task.toStoredString());
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println(Ui.showError(e.getMessage()));
        }
    }

}
