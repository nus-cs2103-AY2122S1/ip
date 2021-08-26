package catobot;

import catobot.exception.EmptyCommandException;
import catobot.exception.LoadingException;
import catobot.item.Deadline;
import catobot.item.Event;
import catobot.item.Task;
import catobot.item.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.storage = new File(filePath);

        try {
            if (!storage.exists()) {
                storage.getParentFile().mkdirs();
                storage.createNewFile();
            }
        } catch (IOException e) {
            Ui.showLoadingError();
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
                String type = input[0];
                int isDone = Integer.parseInt(input[1]);
                String description = input[2];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

                switch (type) {
                case "D":
                    Deadline deadline = Deadline.of(description, LocalDateTime.parse(input[3], formatter));
                    if (isDone == 1) {
                        deadline.markAsDone();
                    }
                    taskList.add(deadline);
                    break;
                case "E":
                    Event event = Event.of(description, LocalDateTime.parse(input[3], formatter));
                    if (isDone == 1) {
                        event.markAsDone();
                    }
                    taskList.add(event);
                    break;
                case "T":
                    Todo todo = Todo.of(description);
                    if (isDone == 1) {
                        todo.markAsDone();
                    }
                    taskList.add(todo);
                    break;
                }
            }
        } catch (FileNotFoundException | EmptyCommandException e) {
            throw new LoadingException();
        }
        return taskList;
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
