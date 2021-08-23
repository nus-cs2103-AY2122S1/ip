import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TaskManager handles the management of and interaction with tasks
 */
public class TaskList {
    /** ArrayList containing all tasks **/
    private static ArrayList<Task> taskArrayList = new ArrayList<>();


    /**
     * Ensures the initial task list is empty
     */
    public static void start() {
        taskArrayList = new ArrayList<>();
    }

    /**
     * Adds a new task to taskArrayList
     * @param task Task object to be added
     */
    public static void add(Task task) {
        taskArrayList.add(task);
    }

    /**
     * Retrieves the task with the corresponding ID
     * @param id ID of task
     * @return Task with ID
     * @throws IndexOutOfBoundsException If task ID does not exist
     */
    public static Task get(int id) throws IndexOutOfBoundsException {
        return taskArrayList.get(id - 1);
    }

    /**
     * Removes the task with corresponding ID
     * @param id ID of task
     * @throws IndexOutOfBoundsException If task ID does not exist
     */
    public static void remove(int id) throws IndexOutOfBoundsException {
        taskArrayList.remove(id - 1);
    }

    /**
     * Lists the current Tasks in taskArrayList with numbering
     */
    public static void list() {
        for (int i = 0; i < taskArrayList.size(); i++) {
            System.out.println(i+1 + "." + taskArrayList.get(i).toString());
        }
        System.out.println();
    }

    /**
     * Retrieves the number of tasks currently
     * @return Number of tasks
     */
    public static int size() {
        return taskArrayList.size();
    }

    /**
     * Retrieves a list of tasks from previous sessions, stored in data/duke.txt
     */
    public static void getTasksFromStorage() throws FileNotFoundException {
        File dataFile = Storage.getDataFile();
        Scanner sc = new Scanner(dataFile);
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
    public static void saveTasksToStorage() {
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
                Storage.writeToDataFile(taskString);
            } else {
                Storage.appendToDataFile("\n" + taskString);
            }
        }
    }
}
