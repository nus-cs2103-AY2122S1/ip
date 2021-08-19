package tasks;

import exceptions.EmptyTaskException;
import exceptions.InvalidCommandException;
import java.util.ArrayList;

public class ToDoList {
    private final String SEPARATOR = "-----------------------------------------------------------------";
    private final ArrayList<Task> tasks;
    private int count;

    public ToDoList() {
        this.tasks = new ArrayList<>();
        this.count = 0;
    }

    public int taskListener(String input) {
        return (input.equals("list"))
                ? listTasks()
                : toMarkAsDone(input)
                ? markAsDone(input)
                : toDeleteTask(input)
                ? deleteTask(input)
                : addTask(input);
    }

    public int listTasks() {
        int n = 0;

        System.out.println(SEPARATOR);
        System.out.println("Below are some of the tasks in your list!");

        for (Task t : this.tasks) {
            System.out.println((n + 1) + ". " + t);

            n++;
        }

        System.out.println(SEPARATOR);

        return 0;
    }

    public int addTask(String input) {
        String[] arr = input.split(" ", 2);

        Task recentlyAdded;

        if (arr[0].equals("todo")) {
            try {
                recentlyAdded = arr.length == 1
                                ? ToDo.addToDo("")
                                : ToDo.addToDo(arr[1]);
            } catch (EmptyTaskException e) {
                System.out.println(SEPARATOR + "\n"
                        + e
                        + SEPARATOR);

                return 0;
            }
        } else if (arr[0].equals("deadline")) {
            recentlyAdded = Deadline.addDeadline(arr[1]);
        } else if (arr[0].equals("event")) {
            recentlyAdded = Event.addEvent(arr[1]);
        } else {
            try {
                throw new InvalidCommandException(input);
            } catch (InvalidCommandException e) {
                System.out.println(SEPARATOR + "\n"
                        + e
                        + SEPARATOR);

                return 0;
            }
        }

        this.tasks.add(recentlyAdded);
        count++;

        String taskCount = "Now you have " + count + " task(s) in the list!";

        System.out.println(SEPARATOR + "\n"
                + "Received order! I've added this task:\n"
                + "     " + recentlyAdded + "\n"
                + taskCount + "\n"
                + SEPARATOR);

        return 0;
    }

    // to check if user input text is a valid command for marking a task as done
    public boolean toMarkAsDone(String input) {
        String[] arr = input.split(" ");

        boolean toDo = false;

        if ((arr[0].equals("done")) && (arr.length == 2)) {
            try {
                Integer.valueOf(arr[1]);
                toDo = true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        // a possible error arrives from this feature:
        // when # input is invalid

        return toDo;
    }

    public int markAsDone(String input) {
        String[] arr = input.split(" ");

        int index = Integer.parseInt(arr[1]) - 1;

        Task t = this.tasks.get(index);

        t.markAsDone();

        String encouragement = "Good job! I've marked this task as done:";
        String reward = "Bubbles will reward you with a piece of candy.";

        System.out.println(SEPARATOR + "\n"
                + encouragement + "\n"
                + "     " + t + "\n"
                + reward + "\n"
                + SEPARATOR);

        return 0;
    }

    // to check if user input text is a valid command for deleting a task
    public boolean toDeleteTask(String input) {
        String[] arr = input.split(" ");

        boolean toDelete = false;

        if (arr[0].equals("delete") && (arr.length == 2)) {
            try {
                Integer.valueOf(arr[1]);
                toDelete = true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        // a possible error arrives from this feature:
        // when # input is invalid

        return toDelete;
    }

    public int deleteTask(String input) {
        String[] arr = input.split(" ");

        int index = Integer.parseInt(arr[1]) - 1;

        Task removed = this.tasks.remove(index);
        count--;

        String taskCount = "Now you have " + count + " task(s) in the list!";

        System.out.println(SEPARATOR + "\n"
                + "Noted! I've removed this task:\n"
                + "     " + removed + "\n"
                + taskCount + "\n"
                + SEPARATOR);
        return 0;
    }
}