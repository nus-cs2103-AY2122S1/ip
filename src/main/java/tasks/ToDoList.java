package tasks;

import exceptions.EmptyTaskException;
import exceptions.IndexOutOfBoundsException;
import exceptions.InvalidCommandException;
import java.util.ArrayList;

public class ToDoList {
    private final String SEPARATOR = "-----------------------------------------------------------------";
    private enum taskType {
        TODO,
        DEADLINE,
        EVENT
    }
    private final ArrayList<Task> tasks;
    private int count;

    public ToDoList() {
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
            Task recentlyAdded;

            if (t == taskType.TODO) {
                if (arr.length == 1) {
                    recentlyAdded = ToDo.addToDo("");
                }

                recentlyAdded = ToDo.addToDo(arr[1]);
            } else if (t == taskType.DEADLINE) {
                recentlyAdded = Deadline.addDeadline(arr[1]);
            } else {
                recentlyAdded = Event.addEvent(arr[1]);
            }

            this.tasks.add(recentlyAdded);
            count++;

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
                String reward = "Bubbles will reward you with a piece of candy.";

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
}