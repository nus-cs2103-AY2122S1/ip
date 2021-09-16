package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

/**
 * Read and Write Duke chatbot's list of tasks to/from a file.
 */
public class Storage {

    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    private Task parseString(String taskString) throws DukeException {
        String[] descriptions = taskString.split("\\|");

        String type = descriptions[0];
        boolean isDone = Boolean.parseBoolean(descriptions[1]);
        String description = descriptions[2];
        String time = descriptions[3];

        Task task;
        switch (type) {
        case "T":
            task = new TodoTask(description);
            break;
        case "D":
            task = new DeadlineTask(description, time);
            break;
        case "E":
            task = new EventTask(description, time);
            break;
        default:
            throw new DukeException("Incorrect Task Format!");
        }
        if (isDone) {
            task.finishTask();
        }
        return task;
    }

    /**
     * Load list of tasks from file or if file is absent, create new file.
     *
     * @return List of tasks
     * @throws DukeException If new file cannot be made.
     */
    public TaskList<Task> load() throws DukeException {
        try {
            TaskList<Task> taskList = new TaskList<>();
            if (!file.exists()) {
                file.createNewFile();
                return taskList;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                Task task = parseString(taskString);
                taskList.add(task);
            }
            scanner.close();
            return taskList;
        } catch (IOException e) {
            throw new DukeException("Cannot Make New File!");
        }
    }

    /**
     * Save list of tasks to file.
     *
     * @param tasks List of tasks.
     * @throws DukeException If destination file is absent.
     */
    public void save(TaskList tasks) throws DukeException {
        String listString = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = (Task) tasks.get(i);
            listString += String.format("%s\n", task.toSaveString());
        }
        try {
            PrintWriter writer = new PrintWriter(file);
            if (!listString.isEmpty()) {
                writer.println(listString.trim());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("File Not Found!");
        }
    }
}
