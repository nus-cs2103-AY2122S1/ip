package bubbles.tasks;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import bubbles.exceptions.EmptyTaskException;
import bubbles.exceptions.IndexOutOfBoundsException;
import bubbles.exceptions.InvalidCommandException;
import bubbles.exceptions.InvalidFormatException;
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
     * Accepts any input from the user (except "bye") and handles the
     * input by calling other methods/throwing an exception to indicate
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
            case "remind":
                return remindMe();
            case "help":
                return help();
            default:
                throw new InvalidCommandException(input);
            }
        } catch (InvalidCommandException e) {
            return e.toString();
        }
    }

    /**
     * Lists out the tasks in the TaskList by printing it out, after
     * the user's "list" command.
     */
    public String listTasks() {
        int n = 0;

        StringBuilder message = new StringBuilder();

        message.append("Below are some of the tasks in your list!\n");

        for (Task t : this.tasks) {
            message.append((n + 1) + ". " + t + "\n");

            n++;
        }

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
            String description = arr.length < 2 ? "" : arr[1];

            switch (t) {
            case TODO:
                recentlyAdded = ToDo.addToDo(description, false);
                break;
            case DEADLINE:
                recentlyAdded = Deadline.addDeadline(description, false);
                break;
            case EVENT:
                recentlyAdded = Event.addEvent(description, false);
                break;
            default:
                assert false : "taskType is not one of the expected;";
                break;
            }

            assert recentlyAdded != null : "Task is null and has yet to be created";

            this.tasks.add(recentlyAdded);
            this.count++;

            String taskCount = "Now you have " + this.count + " task(s) in the list!";

            return (Message.ADD + "     " + recentlyAdded + "\n" + taskCount);
        } catch (EmptyTaskException | InvalidFormatException e) {
            return e.toString();
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
            System.out.println(e);
        }

        int previousCount = this.count;
        this.count++;

        assert this.count - previousCount == 1 : "Count of tasks in task list did not increment as expected;";
    }

    /**
     * Adds a Deadline (Task) to the TaskList.
     *
     * @param input The description of the Deadline (including the due date).
     * @param isDone Whether the Deadline is done/completed.
     */
    public void addDeadline(String input, boolean isDone) {
        try {
            this.tasks.add(Deadline.addDeadline(input, isDone));
        } catch (EmptyTaskException | InvalidFormatException e) {
            System.out.println(e);
        }

        int previousCount = this.count;
        this.count++;

        assert this.count - previousCount == 1 : "Count of tasks in task list did not increment as expected;";
    }

    /**
     * Adds an Event (Task) to the TaskList.
     *
     * @param input The description of the Event (including the event date).
     * @param isDone Whether the Event is done/completed.
     */
    public void addEvent(String input, boolean isDone) {
        try {
            this.tasks.add(Event.addEvent(input, isDone));
        } catch (EmptyTaskException | InvalidFormatException e) {
            System.out.println(e);
        }

        int previousCount = this.count;
        this.count++;

        assert this.count - previousCount == 1 : "Count of tasks in task list did not increment as expected;";
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

            if (index >= this.count || index < 0) {
                throw new IndexOutOfBoundsException("");
            } else {
                Task t = this.tasks.get(index);
                t.setAsDone();

                assert t.isDone : "Task should be set to done, but is_done status is still false;";

                return (Message.DONE + "     " + t + "\n" + Message.REWARD);
            }
        } catch (IndexOutOfBoundsException e) {
            return e.toString();
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

            if (index >= this.count || index < 0) {
                throw new IndexOutOfBoundsException("");
            } else {
                int previousCount = this.count;

                Task removed = this.tasks.remove(index);
                this.count--;

                assert previousCount - this.count == 1 : "Count of tasks in task list did not decrement as expected;";

                String taskCount = "Now you have " + count + " task(s) in the list!";

                return (Message.DELETE + "     " + removed + "\n" + taskCount);
            }
        } catch (IndexOutOfBoundsException e) {
            return e.toString();
        }
    }

    /**
     * Finds tasks with the corresponding keywords in the TaskList.
     *
     * @param input The user input that includes the "find" command and keyword(s) that the user
     *              wants to search for within the tasks in TaskList.
     */
    public String findTask(String input) {
        String[] arr = input.split(" ", 2);

        List<Task> filteredTasks = this.tasks
                                        .stream()
                                        .filter(task -> task.toString().contains(arr[1]))
                                        .collect(Collectors.toList());

        StringBuilder message = new StringBuilder();

        if (filteredTasks.size() == 0) {
            message.append("There are no matching tasks in your list. ☹\n");
        } else {
            message.append("Here are the matching tasks in your list!\n");

            int n = 1;
            for (Task t : filteredTasks) {
                message.append((n) + ". " + t + "\n");

                n++;
            }

            assert n == filteredTasks.size() + 1
                    : "Task numbers displayed for tasks "
                    + "(containing matching keywords) did not increment as expected;";
        }

        return message.toString();
    }

    /**
     * Returns a string that collates the deadlines and events that are happening within the next week.
     * (or allow user input for the time frame he/she wants)
     *
     * @return A String with the reminders for the user.
     */
    public String remindMe() {
        List<Task> recentTasks = findRecentTasks();

        StringBuilder message = new StringBuilder();

        if (recentTasks.size() == 0) {
            message.append("Good news! You don't have any deadlines/events coming up in the next 7 days!\n");
        } else {
            message.append(Message.UPCOMING);

            int n = 1;
            for (Task t : recentTasks) {
                message.append((n) + ". " + t + "\n");

                n++;
            }

            assert n == recentTasks.size() + 1
                    : "Task numbers displayed for tasks "
                    + "(containing matching keywords) did not increment as expected;";
        }

        List<Task> overdueTasks = findOverdueTasks();

        if (overdueTasks.size() > 0) {
            message.append(Message.OVERDUE);

            int n = 1;
            for (Task t : overdueTasks) {
                message.append((n) + ". " + t + "\n");

                n++;
            }
        }

        return message.toString();
    }

    /**
     * Finds and returns a list of tasks occurring within the next seven days of the current date.
     *
     * @return A list of tasks that are going to occur within the next seven days.
     */
    public List<Task> findRecentTasks() {
        return new ArrayList<>(this.tasks)
                        .stream()
                        .filter(task -> (task instanceof Deadline || task instanceof Event))
                        .filter(task -> {
                            Clock cl = Clock.systemUTC();

                            LocalDate now = LocalDate.now(cl);
                            LocalDate limit = now.plusDays(8);

                            LocalDate taskDate = task.getDate();

                            return (taskDate.isBefore(limit) && taskDate.isAfter(now));
                        })
                        .sorted(Comparator.comparing(Task::getDate))
                        .collect(Collectors.toList());
    }

    /**
     * Finds and returns a list of tasks that are overdue and undone.
     *
     * @return A list of tasks that are overdue and undone.
     */
    public List<Task> findOverdueTasks() {
        return new ArrayList<>(this.tasks)
                .stream()
                .filter(task -> (task instanceof Deadline || task instanceof Event))
                .filter(task -> {
                    Clock cl = Clock.systemUTC();
                    LocalDate now = LocalDate.now(cl);

                    return (!task.isDone && task.getDate().isBefore(now));
                })
                .sorted(Comparator.comparing(Task::getDate))
                .collect(Collectors.toList());
    }

    /**
     * Returns the help string to guide users on how to use Bubbles.
     *
     * @return A String that provides a short list of commands users can use with Bubbles.
     */
    public String help() {
        return Message.HELP.toString();
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
