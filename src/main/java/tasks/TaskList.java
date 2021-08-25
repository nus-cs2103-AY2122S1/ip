package tasks;

import bubbles.exceptions.EmptyTaskException;
import bubbles.exceptions.IndexOutOfBoundsException;
import bubbles.exceptions.InvalidCommandException;
import java.util.ArrayList;

public class TaskList {
    private final String SEPARATOR = "-----------------------------------------------------------------";
    private enum taskType {
        TODO,
        DEADLINE,
        EVENT
    }
    private final ArrayList<Task> tasks;
    private int count;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.count = 0;
    }

    public int taskListener(String input) {
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

        return 0;
    }

    public void listTasks() {
        int n = 0;

        System.out.println(SEPARATOR);
        System.out.println("Below are some of the tasks in your list!");

        for (Task t : this.tasks) {
            System.out.println((n + 1) + ". " + t);

            n++;
        }

        System.out.println(SEPARATOR);
    }

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

    public void addTask(String taskType, String input, boolean isDone) {
        if (taskType.equals("T")) {
            addToDo(input, isDone);
        } else if (taskType.equals("D")) {
            addDeadline(input, isDone);
        } else {
            addEvent(input, isDone);
        }
    }

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

    public void addDeadline(String input, boolean isDone) {
        this.tasks.add(Deadline.addDeadline(input, isDone));
        this.count++;
    }

    public void addEvent(String input, boolean isDone) {
        this.tasks.add(Event.addEvent(input, isDone));
        this.count++;
    }

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

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}