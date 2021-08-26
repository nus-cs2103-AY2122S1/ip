package bubbles.tasks;

import bubbles.exceptions.EmptyTaskException;
import bubbles.exceptions.IndexOutOfBoundsException;
import bubbles.exceptions.InvalidCommandException;
import java.util.ArrayList;

/**
 * A list of Task objects, that contains and tracks the Tasks the Bubbles bot
 * user has entered (including previous entries read from the hard dusk file).
 */
public class TaskList {
    private final String SEPARATOR = "-----------------------------------------------------------------";
    private enum taskType {
        TODO,
        DEADLINE,
        EVENT
    }
    private final ArrayList<Task> tasks;
    private int count;

    /**
     * A public constructor for a TaskList Object,
     * that initializes an empty ArrayList for storing the Tasks and
     * keeps a counter for the number of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.count = 0;
    }

    /**
     * TaskListener that accepts any input from the user (except "bye") and
     * handles the input by calling other methods/throwing an exception to indicate
     * that such command does not exist.
     *
     * @param input A String input by the user
     */
    public void taskListener(String input) {
        try {
            String[] arr = input.split(" ", 2);
            String command = arr[0];

            switch (command) {
                case "todo":
                    addTask(taskType.TODO, input);
                    break;
                case "deadline":
                    addTask(taskType.DEADLINE, input);
                    break;
                case "event":
                    addTask(taskType.EVENT, input);
                    break;
                case "list":
                    listTasks();
                    break;
                case "done":
                    markAsDone(input);
                    break;
                case "delete":
                    deleteTask(input);
                    break;
                default:
                    throw new InvalidCommandException(input);
            }
        } catch (InvalidCommandException e) {
            System.out.println(SEPARATOR + "\n"
                    + e
                    + SEPARATOR);
        }
    }

    /**
     * List out the tasks in the TaskList by printing it out, after
     * the user's "list" command.
     */
    public void listTasks() {
        int n = 0;

        System.out.println(SEPARATOR);
        System.out.println("Below are some of the bubbles.tasks in your list!");

        for (Task t : this.tasks) {
            System.out.println((n + 1) + ". " + t);

            n++;
        }

        System.out.println(SEPARATOR);
    }

    /**
     * Add a Task of the specific task type (ToDo, Deadline or Event)
     * into the TaskList. Prints the total number of tasks in the list
     * after.
     *
     * @param t The type of Task
     * @param input The description of the Task (including date for Deadline/Event)
     */
    public void addTask(taskType t, String input) {
        String[] arr = input.split(" ", 2);

        try {
            Task recentlyAdded = null;

            switch (t) {
                case TODO:
                    if (arr.length == 1) {
                        recentlyAdded = ToDo.addToDo("", false);
                    }

                    recentlyAdded = ToDo.addToDo(arr[1], false);
                    break;
                case DEADLINE:
                    recentlyAdded = Deadline.addDeadline(arr[1], false);
                    break;
                case EVENT:
                    recentlyAdded = Event.addEvent(arr[1], false);
                    break;
                default:
                    break;
            }

            this.tasks.add(recentlyAdded);
            this.count++;

            String taskCount = "Now you have " + count + " task(s) in the list!";

            System.out.println(SEPARATOR + "\n"
                    + "Received order! I've added this task:\n"
                    + "     " + recentlyAdded + "\n"
                    + taskCount + "\n"
                    + SEPARATOR);

        } catch (EmptyTaskException e) {
            System.out.println(SEPARATOR + "\n"
                    + e
                    + SEPARATOR);
        }
    }

    /**
     * Add a Task of the specific task type (ToDo, Deadline or Event)
     * into the TaskList. Does not print the total number of tasks in the list after.
     *
     * @param taskType The type of Task
     * @param input The description of the Task (including date for Deadline/Event)
     * @param isDone Whether the Task is done/completed
     */
    public void addTask(String taskType, String input, boolean isDone) {
        if (taskType.equals("T")) {
            addToDo(input, isDone);
        } else if (taskType.equals("D")) {
            addDeadline(input, isDone);
        } else {
            addEvent(input, isDone);
        }
    }

    /**
     * Add a ToDo (Task) to the TaskList.
     *
     * @param input The description of the ToDo
     * @param isDone Whether the ToDo is done/completed
     */
    public void addToDo(String input, boolean isDone) {
        try {
            this.tasks.add(ToDo.addToDo(input, isDone));
        } catch (EmptyTaskException e) {
            System.out.println(SEPARATOR + "\n"
                    + e
                    + SEPARATOR);
        }
        this.count++;
    }

    /**
     * Add a Deadline (Task) to the TaskList.
     *
     * @param input The description of the Deadline (including the due date)
     * @param isDone Whether the Deadline is done/completed
     */
    public void addDeadline(String input, boolean isDone) {
        this.tasks.add(Deadline.addDeadline(input, isDone));
        this.count++;
    }

    /**
     * Add an Event (Task) to the TaskList.
     *
     * @param input The description of the Event (including the event date)
     * @param isDone Whether the Event is done/completed
     */
    public void addEvent(String input, boolean isDone) {
        this.tasks.add(Event.addEvent(input, isDone));
        this.count++;
    }

    /**
     * Mark the indicated task (of the corresponding task number in the
     * input) as done.
     *
     * @param input The user input that includes the "done" command and number
     *              of task that user wants to mark as done/completed
     */
    public void markAsDone(String input) {
        try {
            String[] arr = input.split(" ");

            int index = Integer.parseInt(arr[1]) - 1;

            if (index >= count) {
                throw new IndexOutOfBoundsException("");
            } else {
                Task t = this.tasks.get(index);

                t.markAsDone();

                String encouragement = "Good job! I've marked this task as done:";
                String reward = "bubbles.Bubbles will reward you with a piece of candy.";

                System.out.println(SEPARATOR + "\n"
                        + encouragement + "\n"
                        + "     " + t + "\n"
                        + reward + "\n"
                        + SEPARATOR);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(SEPARATOR + "\n"
                    + e
                    + SEPARATOR);
        }
    }

    /**
     * Delete a Task from the TaskList.
     *
     * @param input The user input that includes the "delete" command and number
     *              of task that user wants to delete permanently
     */
    public void deleteTask(String input) {
        try {
            String[] arr = input.split(" ");

            int index = Integer.parseInt(arr[1]) - 1;

            if (index >= count) {
                throw new IndexOutOfBoundsException("");
            } else {
                Task removed = this.tasks.remove(index);
                count--;

                String taskCount = "Now you have " + count + " task(s) in the list!";

                System.out.println(SEPARATOR + "\n"
                        + "Noted! I've removed this task:\n"
                        + "     " + removed + "\n"
                        + taskCount + "\n"
                        + SEPARATOR);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(SEPARATOR + "\n"
                    + e
                    + SEPARATOR);
        }
    }

    /**
     * Returns the stored array of Tasks.
     *
     * @return ArrayList of Tasks that are stored in this TaskList Object.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}