import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TaskList handles the management of and interaction with tasks
 */
public class TaskList {
    /** ArrayList containing all tasks **/
    private ArrayList<Task> taskArrayList;
    private Storage storage;

    public TaskList(String filePath) {
        this.taskArrayList = new ArrayList<>();
        this.storage = new Storage(filePath);
        this.getTasksFromStorage();
    }

    /**
     * Adds a new task to taskArrayList
     * @param task Task object to be added
     */
    public void add(Task task) {
        taskArrayList.add(task);
    }

    /**
     * Retrieves the task with the corresponding ID
     * @param id ID of task
     * @return Task with ID
     * @throws IndexOutOfBoundsException If task ID does not exist
     */
    public Task get(int id) {
        return taskArrayList.get(id - 1);
    }

    /**
     * Removes the task with corresponding ID
     * @param id ID of task
     * @throws IndexOutOfBoundsException If task ID does not exist
     */
    public void remove(int id) {
        taskArrayList.remove(id - 1);
    }

    /**
     * Lists the current Tasks in taskArrayList with numbering
     */
    public void list() {
        for (int i = 0; i < taskArrayList.size(); i++) {
            System.out.println(i+1 + "." + taskArrayList.get(i).toString());
        }
        System.out.println();
    }

    /**
     * Retrieves the number of tasks currently
     * @return Number of tasks
     */
    public int size() {
        return taskArrayList.size();
    }

    /**
     * Retrieves a list of tasks from previous sessions, stored in data/duke.txt
     */
    public void getTasksFromStorage() {
        File dataFile = storage.getFile();
        Scanner sc;
        try {
            sc = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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
            }
        }
        sc.close();
    }

    /**
     * Saves the current list of tasks to data/duke.txt to be used in future sessions
     */
    public void saveTasksToStorage() {
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task task = taskArrayList.get(i);
            String taskString;
            String isDone = task.isDone() ? "1|" : "0|";
            if (task instanceof ToDo) {
                taskString = "T|" + isDone + task.getName();
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                taskString = "D|" + isDone + task.getName() + "|" + deadline.getEndDate();
            } else {
                Event event = (Event) task;
                taskString = "E|" + isDone + task.getName() + "|" + event.getEventDate();
            }
            if (i == 0) {
                storage.writeToDataFile(taskString);
            } else {
                storage.appendToDataFile("\n" + taskString);
            }
        }
    }
}
