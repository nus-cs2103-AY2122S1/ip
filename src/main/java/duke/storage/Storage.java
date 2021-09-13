package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.NoPreviousFileException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;


/**
 * Storage allows the creation and update of txt files to maintain past tasks.
 */
public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Returns a list of Tasks that were stored in the tasks.txt file.
     *
     * @return a list of Tasks
     * @throws DukeException If error writing to the files or no file detected.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (!currentLine.equals("Tasks:")) {
                    Task task = convertToTask(currentLine);
                    list.add(task);
                }
            }
            br.close();
        } catch (IOException e1) {
            File file = new File(path);
            try {
                file.createNewFile();
            } catch (IOException e2) {
                System.out.println("Error creating txt file");
            }
            throw new NoPreviousFileException("No previous file "
                     + "detected, creating new one.");
        }

        return list;
    }

    private static Task convertToTask(String output) {
        char type = output.charAt(1);
        char done = output.charAt(4);
        String taskAndDate = output.substring(7);

        Task task;
        if (type == 'T') {
            task = new Todo(taskAndDate);
        } else if (type == 'D') {
            // Deadlines
            if (taskAndDate.contains("(by: ")) {
                String name = taskAndDate.split(" \\(by: ")[0];
                String date = taskAndDate.split(" \\(by: ")[1];

                // Chop of last ")"
                date = date.substring(0, date.length() - 1);
                task = new Deadline(name, " " + date);
            } else {
                task = new Deadline(taskAndDate, "");
            }
        } else {
            // Events
            if (taskAndDate.contains("(at: ")) {
                String name = taskAndDate.split(" \\(at: ")[0];
                String date = taskAndDate.split(" \\(at: ")[1];

                // Chop of last ")"
                date = date.substring(0, date.length() - 1);
                task = new Event(name, " " + date);
            } else {
                task = new Event(taskAndDate, "");
            }
        }
        task.setType(type);
        if (done == 'X') {
            task.setDone();
        }
        return task;
    }

    /**
     * Updates the txt file to store and remember the tasks entered.
     *
     * @param tasklist This is taken in so that the txt file can be updated.
     */
    public void updateTxtFile(TaskList tasklist) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            for (Task t : tasklist.getTaskList()) {
                if (t.getType() == 'D') {
                    Deadline dl = (Deadline) t;
                    bw.write(dl + "\n");
                } else if (t.getType() == 'E') {
                    Event e = (Event) t;
                    bw.write(e + "\n");
                } else {
                    Todo todo = (Todo) t;
                    bw.write(todo + "\n");
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error reading and writing file.");
        }
    }
}
