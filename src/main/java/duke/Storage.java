package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.ToDoCommand;
import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final String FILE_PATH;
    private Scanner scanner;
    private TaskList storedTaskList;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The path of saved file.
     */
    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Loads data from the txt and converts it to TaskList.
     *
     * @return TaskList New TaskList with inputs from filePath.
     * @throws DukeException If file is corrupted.
     */
    public TaskList load() throws DukeException {
        try {
            setFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.storedTaskList = new TaskList();

        // Insert tasks from hard disk into TaskList.
        int index = 1;
        while (scanner.hasNext()) {
            String unformattedTask = scanner.nextLine();
            parseUnformattedTask(unformattedTask, index);
            index += 1;
        }
        return storedTaskList;
    }

    /**
     * Creates the data file if it does not exist.
     *
     * Initialises Scanner.
     */
    private void setFile() throws IOException {
        // Create directory is not exist.
        String DIR_PATH = "data";
        File dir = new File(DIR_PATH);
        if (!dir.exists()) {
            Files.createDirectory(Paths.get(DIR_PATH));
        }

        // Create file if not exist.
        File f = new File(FILE_PATH);
        if (!f.exists()) {
            Files.createFile(Paths.get(FILE_PATH));
        }

        this.scanner = new Scanner(f);
    }

    /** Parses the unformatted task from file, inserts task into TaskList */
    private void parseUnformattedTask(String unformattedTask, int index) throws DukeException {
        String[] splitTask = unformattedTask.split(" \\| ", 3);
        String command = splitTask[0];
        String isDone = splitTask[1];
        String body = splitTask[2];

        if (command.equals("T")) {
            ToDoCommand t = new ToDoCommand("todo " + body);
            storedTaskList.add(t.getTaskToAdd());
        } else if (command.equals("D")) {
            String[] descAndTime = body.split(" \\| ", 3);
            DeadlineCommand d = new DeadlineCommand("deadline "
                    + descAndTime[0]
                    + " /by "
                    + descAndTime[1]);
            storedTaskList.add(d.getTaskToAdd());
        } else if (command.equals("E")) {
            String[] descAndTime = body.split(" \\| ", 3);
            EventCommand e = new EventCommand("event "
                    + descAndTime[0]
                    + " /at "
                    + descAndTime[1]);
            storedTaskList.add(e.getTaskToAdd());
        }

        // Java assertion
        assert isDone.equals("1") || isDone.equals("0") : "Stored file is false";

        if (isDone.equals("1")) {
            storedTaskList.markAsDone(index);
        }
    }

    /**
     * Appends to file. Append when adding new tasks.
     *
     * @param task Task to add.
     * @throws IOException
     */
    public void appendToFile(Task task) throws IOException {
        String req = "";
        if (task instanceof ToDo) {
            req += "T | ";
            req += task.isDone() ? "1 | " : "0 | ";
            req += task.getDescription();
        } else if (task instanceof Deadline) {
            req += "D | ";
            req += task.isDone() ? "1 | " : "0 | ";
            req += task.getDescription() + " | ";
            req += ((Deadline) task).getDate();
        } else if (task instanceof Event) {
            req += "E | ";
            req += task.isDone() ? "1 | " : "0 | ";
            req += task.getDescription() + " | ";
            req += ((Event) task).getDate();
        }
        req += System.lineSeparator();

        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(req);
        fw.close();
    }

    /**
     * Rewrites the entire file with the given string.
     *
     * @param tasks TaskList to write into file.
     * @throws IOException
     */
    public void rewriteFile(TaskList tasks) throws IOException {
        String req = "";
        for (Task task : tasks.getAll()) {
            if (task instanceof ToDo) {
                req += "T | ";
                req += task.isDone() ? "1 | " : "0 | ";
                req += task.getDescription();
                req += System.lineSeparator();
            } else if (task instanceof Deadline) {
                req += "D | ";
                req += task.isDone() ? "1 | " : "0 | ";
                req += task.getDescription() + " | ";
                req += ((Deadline) task).getDate();
                req += System.lineSeparator();
            } else if (task instanceof Event) {
                req += "E | ";
                req += task.isDone() ? "1 | " : "0 | ";
                req += task.getDescription() + " | ";
                req += ((Event) task).getDate();
                req += System.lineSeparator();
            }
        }

        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(req);
        fw.close();
    }
}
