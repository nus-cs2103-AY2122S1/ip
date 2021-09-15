package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Storage read and store task in the list in the file.
 */
public class Storage {
    private File file;
    private String fileName;

    /**
     * Constructs a storage object.
     *
     * @param fileName The name of the file that stored the Task List.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
        file = new File(fileName);
    }

    /**
     * Returns an array of task that are stored in the file.
     *
     * @return Array of task that are store in the file.
     * @throws DukeException If file is corrupted.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner scan = new Scanner(this.file);

            while (scan.hasNext()) {
                String taskLine = scan.nextLine();
                Task newTask = this.createTaskFromString(taskLine);
                tasks.add(newTask);
            }
            return tasks;

        } catch (FileNotFoundException e) {
            return tasks;
        }
    }

    private Task createTaskFromString(String taskString) throws DukeException {
        Task newTask;
        String[] task = taskString.split(" \\| ");
        String taskType = task[0];
        boolean isDone = task[1].equals("X");
        String[] tags = task[2].split("#");

        switch (taskType) {
        case ("T"):
            newTask = new Todo(task[3]);
            break;
        case ("D"):
            newTask = new Deadline(task[3], task[4]);
            break;
        case ("E"):
            newTask = new Event(task[3], task[4]);
            break;
        default:
            throw new DukeException("Oops! Duke can't load a file");
        }

        if (isDone) {
            newTask.markDone();
        }

        for (int i = 0; i < tags.length; i++) {
            newTask.addTag(tags[i]);
        }

        return newTask;
    }


    /**
     * Stores all current tasks in the hard-drive.
     *
     * @param tasks Task list with all current tasks.
     * @throws DukeException If overriding of file can't be done.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            String fileTask = tasks.toFileString();
            FileWriter fileWriter = new FileWriter(this.fileName, false);
            fileWriter.write(fileTask);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Oops there is an issue with overriding the file");
        }
    }

}
