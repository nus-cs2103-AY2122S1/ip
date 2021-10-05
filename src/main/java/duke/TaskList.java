package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * TaskList handles the loading, storing and interactions with tasks.
 */
public class TaskList {
    /** ArrayList containing all tasks. **/
    private final ArrayList<Task> tasks;

    /** Storage instance for interaction with files. **/
    private final Storage storage;

    /**
     * Initializes a new TaskList with the given file path.
     * Filepath is used to initialize a Storage instance.
     *
     * @param filePath Path to storage file.
     */
    public TaskList(String filePath) {
        this.tasks = new ArrayList<>();
        this.storage = new Storage(filePath);
        this.getTasksFromStorage();
    }

    /**
     * Adds a new task to taskArrayList.
     *
     * @param task Task object to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves the task with the corresponding ID.
     *
     * @param id ID of task.
     * @return Task with corresponding ID.
     */
    public Task get(int id) {
        return tasks.get(id - 1);
    }

    /**
     * Removes the task with corresponding ID.
     *
     * @param id ID of task.
     */
    public void remove(int id) {
        tasks.remove(id - 1);
    }

    /**
     * Retrieves the number of tasks currently.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves a list of tasks from previous sessions, stored in data/duke.txt.
     * If file does not exist, it will be created.
     * Parses and adds tasks line by line to taskArrayList.
     */
    public void getTasksFromStorage() {
        // Get or create data file if it doesn't exist
        File dataFile = storage.getFile();
        Scanner sc;
        try {
            sc = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Parses file line by line and add into tasks
        while (sc.hasNext()) {
            String[] commandArr = sc.nextLine().split("\\|");
            boolean isDone = commandArr[1].equals("1");
            switch (commandArr[0]) {
            case "T":
                add(new ToDo(commandArr[2], isDone));
                break;
            case "D":
                LocalDate deadlineDate = LocalDate.parse(commandArr[3]);
                add(new Deadline(commandArr[2], deadlineDate, isDone));
                break;
            case "E":
                LocalDate eventDate = LocalDate.parse(commandArr[3]);
                add(new Event(commandArr[2], eventDate, isDone));
                break;
            default:
                // Go on to the next line
                continue;
            }
        }
        sc.close();
    }

    /**
     * Saves the current list of tasks to data/duke.txt to be used in future sessions.
     */
    public void saveTasksToStorage() {
        // Empty file if there are no tasks
        if (tasks.isEmpty()) {
            storage.writeToDataFile("");
        }

        // Stores each tasks to the data file
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskString;
            String isDone = task.isDone() ? "1|" : "0|";
            if (task instanceof ToDo) {
                taskString = "T|" + isDone + task.getName();
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                taskString = "D|" + isDone + task.getName() + "|" + deadline.getEndDate();
            } else if (task instanceof Event) {
                Event event = (Event) task;
                taskString = "E|" + isDone + task.getName() + "|" + event.getEventDate();
            } else {
                // skip task
                continue;
            }
            if (i == 0) {
                storage.writeToDataFile(taskString);
            } else {
                storage.appendToDataFile("\n" + taskString);
            }
        }
    }

    /**
     * Finds and prints a list of tasks with matching task name.
     *
     * @param taskName Name of task entered by user.
     */
    public String find(String taskName) {
        String response = String.format("Here are your tasks that match %s:\n", taskName);
        // Use Java streams to filter out the matching tasks
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getName().contains(taskName))
                .collect(Collectors.toList());
        for (int i = 0; i < matchingTasks.size(); i++) {
            response += i + 1 + "." + matchingTasks.get(i).toString() + "\n";
        }
        return response;
    }
}
