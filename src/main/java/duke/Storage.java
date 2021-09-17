package duke;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Represents a storage which allows for reading and writing of tasks to a file.
 */
public class Storage {
    private static final Pattern STORE_FORMAT = Pattern.compile("\\[(?<type>\\S)] \\[(?<done>[ X])] (?<arguments>.*)");
    private final String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Loads stored tasks from a file into a TaskList.
     *
     * @return A TaskList with the stored tasks.
     * @throws IOException If file is not found.
     * @throws DukeException If the format of the file is incorrect.
     */
    public TaskList load() throws IOException, DukeException {
        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(new FileReader(fileName));
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Matcher matcher = STORE_FORMAT.matcher(input);
            if (!matcher.matches()) {
                throw new DukeException("Task list corrupted, list will be deleted.");
            }
            String type = matcher.group("type");
            String done = matcher.group("done");
            String arguments = matcher.group("arguments");
            String[] parts;
            Task task;

            switch (type) {
            case "T":
                task = new Todo(arguments);
                if (done.equals("X")) {
                    task.markAsDone();
                }
                taskList.addTask(task);
                break;
            case "D":
                parts = arguments.split(" \\(by: |\\)");
                task = new Deadline(parts[0], parts[1]);
                if (done.equals("X")) {
                    task.markAsDone();
                }
                taskList.addTask(task);
                break;
            case "E":
                parts = arguments.split(" \\(at: |\\)");
                task = new Event(parts[0], parts[1]);
                if (done.equals("X")) {
                    task.markAsDone();
                }
                taskList.addTask(task);
                break;
            default:
                throw new DukeException("Task list corrupted, list will be deleted.");
            }
        }
        return taskList;
    }

    /**
     * Writes tasks in tasklist into a file.
     *
     * @param taskList List of tasks to be written into the file.
     */
    public void write(TaskList taskList) {
        try {
            assert Files.exists(Paths.get(fileName)) : "File should exist.";
            FileWriter fw = new FileWriter(fileName);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.getTask(i) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a new file.
     */
    public void createNewFile() {
        File file = new File(fileName);
        File directory = new File(file.getParent());
        try {
            directory.mkdir();
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) {
                System.out.println("Error: File already exists");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
