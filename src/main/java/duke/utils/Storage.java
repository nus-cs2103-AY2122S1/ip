package duke.utils;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/** Class to deal with saving/loading tasks from disk */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads previously saved tasks from the disk.
     * Will create a new file to save tasks in if none currently exists.
     *
     * @return List of tasks loaded from disk.
     * @throws DukeException if any of the saved tasks are invalid.
     */
    public List<Task> load() throws DukeException {
        String dirName = filePath.split("/duke.txt")[0];
        File dir = new File(dirName);
        dir.mkdir();

        File data = new File(filePath);
        List<Task> tasks = new ArrayList<>();

        try {
            data.createNewFile();
            Scanner reader = new Scanner(data);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                addTask(line, tasks);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }

    private void addTask(String line, List<Task> tasks) throws DukeException {
        String[] info = line.split(" \\| ");
        if (info.length == 1) {
            return;
        }

        String command = info[0];
        Task task = null;
        boolean done = info[1].equals("1");
        if (command.equals("T")) {
            task = new Todo(info[2]);
        } else if (command.equals("D")) {
            task = new Deadline(info[2], info[3]);
        } else if (command.equals("E")) {
            task = new Event(info[2], info[3]);
        }

        if (task != null) {
            if (done) {
                task.markDone();
            }
            tasks.add(task);
        }
    }

    /**
     * Saves the task list to disc.
     *
     * @param tasks
     */
    public void save(TaskList tasks) {
        String data = tasks.getData();
        boolean isDataValid = data.equals("");
        isDataValid = isDataValid || data.matches("[T, D, E] \\| [0, 1] \\|.*");
        assert (!isDataValid)
                : "data should be formatted as \"EVENT | VALUE | ...\","
                    + "where EVENT is T, D or E, and VALUE is \" \" or X";
        try (PrintWriter out = new PrintWriter(filePath)) {
            out.println(data);
        } catch (IOException e) {
            // will never occur since the file will always be created first
        }
    }
}
