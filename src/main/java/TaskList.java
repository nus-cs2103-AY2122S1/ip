import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * This class handles commands dealing with the user's task list.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Performs action on tasklist based on the user's inputs.
     *
     * @param command The command to perform.
     * @param input The input from the user.
     * @throws InvalidTaskException If task cannot be found in user's list.
     * @throws MissingTaskException If task is unspecified after command.
     * @throws MissingTimeException If time is unspecified after command.
     * */
    public static void performCommand(String command, String input)
            throws InvalidTaskException, MissingTaskException, MissingTimeException, InvalidCommandException {
        switch (command) {
        case "list":
            printList();
            break;
        case "mark":
            int toMark = Integer.parseInt(input.substring(5));
            markTaskAsDone(toMark);
            break;
        case "delete":
            int toDelete = Integer.parseInt(input.substring(7));
            deleteTask(toDelete);
            break;
        case "todo":
            addToDo(input);
            break;
        case "deadline":
            addDeadline(input);
            break;
        case "event":
            addEvent(input);
            break;
        default:
            throw new InvalidCommandException("Command not found.");
        }
    }

    /**
     * Prints out every task in the list.
     */
    public static void printList() {
        if (taskList.size() == 0) {
            System.out.printf("There are no tasks to be done! Hooray!\n");
        } else {
            System.out.println("Here is your list of tasks:");

            for (int i = 0; i < taskList.size(); i++) {
                String taskName = taskList.get(i).toString();
                System.out.printf("%d.%s\n", i + 1, taskName);
            }
        }
    }

    /**
     * Marks the corresponding task as done and prints confirmation.
     *
     * @param toMark The index of the task to be marked.
     * @throws InvalidTaskException If task cannot be found in user's list.
     * @throws IOException If there are problems with writing into the file.
     *
     */
    public static void markTaskAsDone(int toMark) throws InvalidTaskException {
        if (toMark <= 0 || toMark > taskList.size()) {
            throw new InvalidTaskException("Task is not found");
        }

        taskList.get(toMark - 1).markAsDone();

        System.out.printf("Great job!\n"
                + "The following task is marked as done:\n"
                + "\t%s\n",
                taskList.get(toMark - 1).toString());
    }

    /**
     * Deletes the corresponding task as done and prints confirmation.
     *
     * @param toDelete The index of the task to be deleted.
     * @throws InvalidTaskException If task cannot be found in list.
     */
    public static void deleteTask(int toDelete) throws InvalidTaskException {
        if (toDelete <= 0 || toDelete > taskList.size()) {
            throw new InvalidTaskException("Task is not found");
        }

        System.out.printf("Done!\n"
                + "The following task has been removed:\n"
                + "\t%s\n"
                + "You now have %d "
                + (taskList.size() - 1 == 1 ? "task" : "tasks")
                + " left in your list!\n",
                taskList.get(toDelete - 1).toString(), taskList.size() - 1);

        taskList.remove(toDelete - 1);
    }

    /**
     * Adds the to do entered by the user to the list and prints it.
     *
     * @param input The to do inputted by the user.
     * @throws MissingTaskException If task is unspecified after command.
     */
    public static void addToDo(String input) throws MissingTaskException {
        if (input.length() < 6) {
            throw new MissingTaskException("Task not found.");
        }

        String taskName = input.substring(5);
        taskList.add(new ToDo(taskName));
        printTaskAdded(taskName);
    }


    /**
     * Adds the deadline entered by the user to the list and prints it.
     *
     * @param input The deadline inputted by the user.
     * @throws MissingTaskException If task is unspecified after command.
     * @throws MissingTimeException If time is unspecified after command.
     */
    public static void addDeadline(String input)
            throws MissingTaskException, MissingTimeException, DateTimeParseException {
        int separation = input.indexOf(" /by ");

        if (separation == -1) {
            throw new MissingTimeException("Time not found");
        }

        if (separation < 11) {
            throw new MissingTaskException("Task not found");
        }

        String taskName = input.substring(9, separation);

        if (input.substring(separation + 6).length() < 1) {
            throw new MissingTimeException("Time not found");
        }

        String timeFull = input.substring(separation + 5);
        int timeFullSeparation;

        if ((timeFullSeparation = timeFull.indexOf(" ")) != -1) {
            String time = timeFull.substring(timeFullSeparation + 1);
            LocalDate date = LocalDate.parse(timeFull.substring(0, timeFullSeparation));
            taskList.add(new Deadline(taskName, date, time));
        } else {
            LocalDate date = LocalDate.parse(timeFull);
            taskList.add(new Deadline(taskName, date));
        }

        printTaskAdded(taskName);
    }

    /**
     * Adds the event entered by the user to the list and prints it.
     *
     * @param input The event inputted by the user.
     * @throws MissingTaskException If task is unspecified after command.
     * @throws MissingTimeException If time is unspecified after command.
     */
    public static void addEvent(String input)
            throws MissingTaskException, MissingTimeException, DateTimeParseException {
        int separation = input.indexOf(" /at ");

        if (separation == -1) {
            throw new MissingTimeException("Time not found");
        }

        if (separation < 8) {
            throw new MissingTaskException("Task not found");
        }
        String taskName = input.substring(6, separation);

        if (input.substring(separation + 6).length() < 1) {
            throw new MissingTimeException("Time not found");
        }

        String timeFull = input.substring(separation + 5);
        int timeFullSeparation;

        if ((timeFullSeparation = timeFull.indexOf(" ")) != -1) {
            String time = timeFull.substring(timeFullSeparation + 1);
            LocalDate date = LocalDate.parse(timeFull.substring(0, timeFullSeparation));
            taskList.add(new Event(taskName, date, time));
        } else {
            LocalDate date = LocalDate.parse(timeFull);
            taskList.add(new Event(taskName, date));
        }

        printTaskAdded(taskName);
    }

    /**
     * Prints the confirmation of the addition of the last task.
     *
     * @param taskName The name of the task just added.
     */
    public static void printTaskAdded(String taskName) {
        System.out.printf("Gotcha! The following task has been added:\n"
                + "\t%s\n"
                + "You now have %d "
                + (taskList.size() == 1 ? "task" : "tasks")
                + " in your list!\n",
                taskName, taskList.size());
    }

    /**
     * Returns the last task added to the task list.
     *
     * @return The array containing the tasks.
     */
    public ArrayList<Task> getTasks() {
        return taskList;
    }
}