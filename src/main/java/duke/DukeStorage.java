package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Class that defines the storage for the data needed for Duke
 *
 */
public class DukeStorage {

    /** The destination of the stored data */
    private String path;

    /**
     * Constructor that initializes the storage for Duke
     *
     * @param path The destination of the stored data
     */
    public DukeStorage(String path) {
        this.path = path;
    }

    /**
     * Reads tasks from storage
     *
     * @return List of tasks read from storage
     * @throws DukeException Error thrown if file cannot be read
     */
    public TaskList readTasks() throws DukeException {
        File file = new File(this.path);
        if (file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                TaskList taskList = new TaskList();

                String data = "";
                while (sc.hasNext()) {
                    data = sc.nextLine();
                    taskList.add(parseInput(data));
                }

                sc.close();
                return taskList;
            } catch (FileNotFoundException e) {
                throw new DukeException("File error! Please try again.");
            }
        } else {
            try {
                file.createNewFile();
                return new TaskList();
            } catch (IOException e) {
                throw new DukeException("File error! Please try again.");
            }
        }
    }

    /**
     * Parses the text read from the file to return the task it represents
     *
     * @param input String read from text file
     * @return Task that the text represents
     * @throws DukeException Exception thrown if the task read is an invalid task
     */
    public Task parseInput(String input) throws DukeException {
        String[] parseTask = input.split(" \\| ");

        String taskType = parseTask[0];
        boolean isDone = parseTask[1].equals("1") ? true : false;
        String taskDescr = parseTask[2];
        Task task;

        assert taskType == "T" || taskType == "D" || taskType == "E" : "Invalid task type";

        switch (taskType) {

        case "T":
            task = new Todo(taskDescr);
            if (isDone) {
                task.setDone();
            }
            break;
        case "D":
            task = new Deadline(taskDescr, parseTask[3]);
            if (isDone) {
                task.setDone();
            }
            break;
        case "E":
            task = new Event(taskDescr, parseTask[3]);
            if (isDone) {
                task.setDone();
            }
            break;
        default:
            throw new DukeException("Invalid task type! :(");
        }
        return task;
    }

    /**
     * Writes task data into storage
     *
     * @param taskList The list of tasks to be written
     * @throws DukeException Error thrown if file cannot be written
     */
    public void writeTasks(TaskList taskList) throws DukeException {
        File file = new File(this.path);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            String data = "";
            for (int i = 0; i < taskList.size(); i++) {
                data += taskList.get(i).getFileString() + "\n";
            }

            FileWriter w = new FileWriter(file);
            w.write(data);
            w.close();
        } catch (IOException e) {
            throw new DukeException("File error! Please try again.");
        }
    }
}
