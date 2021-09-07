package catobot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import catobot.exception.EmptyCommandException;
import catobot.exception.LoadingException;
import catobot.item.Deadline;
import catobot.item.Event;
import catobot.item.Task;
import catobot.item.Todo;

/**
 * Represents the local memory of tasks.
 */
public class Storage {
    /** The path to the file. */
    private final String filePath;

    /** The file storing the tasks. */
    private final File storage;

    /**
     * Constructor for Storage.
     *
     * @param filePath The path to the local memory.
     * @throws LoadingException If the file cannot be loaded properly.
     */
    public Storage(String filePath) throws LoadingException {
        this.filePath = filePath;
        this.storage = new File(filePath);
        try {
            if (!storage.exists()) {
                storage.getParentFile().mkdirs();
                storage.createNewFile();
            }
        } catch (IOException e) {
            throw new LoadingException();
        }
    }

    /**
     * Loads the tasks from local memory to Catobot.
     *
     * @return The list of tasks from local memory.
     * @throws LoadingException If the tasks cannot be loaded from storage.
     */
    public List<Task> load() throws LoadingException {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Scanner s = new Scanner(storage);

            while (s.hasNext()) {
                String rawTask = s.nextLine();
                String[] input = rawTask.split(" \\| ", 4);

                loadTask(input, taskList);
            }
        } catch (FileNotFoundException | EmptyCommandException e) {
            throw new LoadingException();
        }
        return taskList;
    }

    private void loadTask(String[] input, ArrayList<Task> taskList) throws EmptyCommandException {
        String type = input[0];

        int isDone = Integer.parseInt(input[1]);
        assert (isDone == 0 || isDone == 1) : "isDone should be either 0 or 1";

        String description = input[2];
        assert !description.equals("") : "description cannot be empty";
        DateTimeFormatter formatter = Task.DATE_FORMAT_DOC;

        switch (type) {
        case "D":
            Deadline deadline = Deadline.of(description, LocalDateTime.parse(input[3], formatter));
            addToList(deadline, taskList, isDone);
            break;
        case "E":
            Event event = Event.of(description, LocalDateTime.parse(input[3], formatter));
            addToList(event, taskList, isDone);
            break;
        case "T":
            Todo todo = Todo.of(description);
            addToList(todo, taskList, isDone);
            break;
        default:
            break;
        }
    }

    private void addToList(Task task, ArrayList<Task> list, int isDone) {
        if (isDone == 1) {
            task.markAsDone();
        }
        list.add(task);
    }

    /**
     * Writes the list of tasks to the local memory.
     *
     * @param tasks The list of tasks to be written.
     * @throws IOException If the file is not found.
     */
    public void write(String tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(tasks);
        fw.close();
    }
}
