package bubbles.tasks;

import java.util.ArrayList;

import bubbles.exceptions.EmptyTaskException;
import bubbles.exceptions.IndexOutOfBoundsException;
import bubbles.exceptions.InvalidCommandException;
import bubbles.util.Message;

/**
 * A list of Task objects, that contains and tracks the Tasks the Bubbles bot
 * user has entered (including previous entries read from the hard dusk file).
 */
public class TaskList {
    private int count;
    private final ArrayList<Task> tasks;
    public enum TaskType {
        DEADLINE,
        EVENT,
        TODO
    }

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
     * @param input A String input by the user.
     */
    public String taskListener(String input) {
        try {
            String[] arr = input.split(" ", 2);
            String command = arr[0];

            switch (command) {
            case "todo":
                return addTask(TaskType.TODO, input);
            case "deadline":
                return addTask(TaskType.DEADLINE, input);
            case "event":
                return addTask(TaskType.EVENT, input);
            case "list":
                return listTasks();
            case "done":
                return markAsDone(input);
            case "delete":
                return deleteTask(input);
            case "find":
                return findTask(input);
            default:
                throw new InvalidCommandException(input);
            }
        } catch (InvalidCommandException e) {
            return (Message.FORMAT_LINE + "\n"
                    + e
                    + Message.FORMAT_LINE);
        }
    }

    /**
     * Lists out the tasks in the TaskList by printing it out, after
     * the user's "list" command.
     */
    public String listTasks() {
        int n = 0;

        StringBuilder message = new StringBuilder(Message.FORMAT_LINE + "\n");

        message.append("Below are some of the tasks in your list!\n");

        for (Task t : this.tasks) {
            message.append((n + 1) + ". " + t + "\n");

            n++;
        }

        message.append(Message.FORMAT_LINE);

        return message.toString();
    }

    /**
     * Adds a Task of the specific task type (ToDo, Deadline or Event)
     * into the TaskList. Prints the total number of tasks in the list
     * after.
     *
     * @param t The type of Task.
     * @param input The description of the Task (including date for Deadline/Event).
     */
    public String addTask(TaskType t, String input) {
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
            return (Message.FORMAT_LINE + "\n"
                    + "Received order! I've added this task:\n"
                    + "     " + recentlyAdded + "\n"
                    + taskCount + "\n"
                    + Message.FORMAT_LINE);

        } catch (EmptyTaskException e) {
            return (Message.FORMAT_LINE + "\n"
                    + e
                    + Message.FORMAT_LINE);
        }
    }

    /**
     * Adds a Task of the specific task type (ToDo, Deadline or Event)
     * into the TaskList. Does not print the total number of tasks in the list after.
     *
     * @param taskType The type of Task.
     * @param input The description of the Task (including date for Deadline/Event).
     * @param isDone Whether the Task is done/completed.
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
     * Adds a ToDo (Task) to the TaskList.
     *
     * @param input The description of the ToDo.
     * @param isDone Whether the ToDo is done/completed.
     */
    public void addToDo(String input, boolean isDone) {
        try {
            this.tasks.add(ToDo.addToDo(input, isDone));
        } catch (EmptyTaskException e) {
            System.out.println(Message.FORMAT_LINE + "\n"
                    + e
                    + Message.FORMAT_LINE);
        }
        this.count++;
    }

    /**
     * Adds a Deadline (Task) to the TaskList.
     *
     * @param input The description of the Deadline (including the due date).
     * @param isDone Whether the Deadline is done/completed.
     */
    public void addDeadline(String input, boolean isDone) {
        this.tasks.add(Deadline.addDeadline(input, isDone));
        this.count++;
    }

    /**
     * Adds an Event (Task) to the TaskList.
     *
     * @param input The description of the Event (including the event date).
     * @param isDone Whether the Event is done/completed.
     */
    public void addEvent(String input, boolean isDone) {
        this.tasks.add(Event.addEvent(input, isDone));
        this.count++;
    }

    /**
     * Marks the indicated task (of the corresponding task number in the
     * input) as done.
     *
     * @param input The user input that includes the "done" command and number
     *              of task that user wants to mark as done/completed.
     */
    public String markAsDone(String input) {
        try {
            String[] arr = input.split(" ");
            int index = Integer.parseInt(arr[1]) - 1;

            if (index >= count) {
                throw new IndexOutOfBoundsException("");
            } else {
                Task t = this.tasks.get(index);
                t.setAsDone();

                String encouragement = "Good job! I've marked this task as done:";
                String reward = "Bubbles will reward you with a piece of candy!";
                return (Message.FORMAT_LINE + "\n"
                        + encouragement + "\n"
                        + "     " + t + "\n"
                        + reward + "\n"
                        + Message.FORMAT_LINE);
            }
        } catch (IndexOutOfBoundsException e) {
            return (Message.FORMAT_LINE + "\n"
                    + e
                    + Message.FORMAT_LINE);
        }
    }

    /**
     * Deletes a Task from the TaskList.
     *
     * @param input The user input that includes the "delete" command and number
     *              of task that user wants to delete permanently.
     */
    public String deleteTask(String input) {
        try {
            String[] arr = input.split(" ");
            int index = Integer.parseInt(arr[1]) - 1;

            if (index >= count) {
                throw new IndexOutOfBoundsException("");
            } else {
                Task removed = this.tasks.remove(index);
                count--;

                String taskCount = "Now you have " + count + " task(s) in the list!";
                return (Message.FORMAT_LINE + "\n"
                        + "Noted! I've removed this task:\n"
                        + "     " + removed + "\n"
                        + taskCount + "\n"
                        + Message.FORMAT_LINE);
            }
        } catch (IndexOutOfBoundsException e) {
            return (Message.FORMAT_LINE + "\n"
                    + e
                    + Message.FORMAT_LINE);
        }
    }

    /**
     * Finds tasks with the corresponding keywords in the TaskList.
     *
     * @param input The user input that includes the "find" command and keyword(s) that the user
     *              wants to search for within the tasks in TaskList.
     */
    public String findTask(String input) {
        ArrayList<Task> filtered = new ArrayList<>();

        String[] arr = input.split(" ", 2);

        for (Task t : tasks) {
            if (t.toString().contains(arr[1])) {
                filtered.add(t);
            }
        }

        StringBuilder message = new StringBuilder(Message.FORMAT_LINE.toString() + "\n");

        if (filtered.size() == 0) {
            message.append("There are no matching tasks in your list. ☹\n");
        } else {
            message.append("Here are the matching tasks in your list!\n");

            int n = 1;
            for (Task t : filtered) {
                message.append((n) + ". " + t + "\n");

                n++;
            }
        }

        message.append(Message.FORMAT_LINE);

        return message.toString();
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
