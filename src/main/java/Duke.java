import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static String GREETING_MSG = "Hello! I'm Duke\nWhat can I do for you?";
    private final static String GOODBYE_MSG = "Bye. Hope to see you again soon!";
    private final static String LIST_TASK_MSG = "Here are the tasks in your list:";
    private final static String ADD_TASK_MSG_TEMPLATE = "Got it. I've added this task:\n"
                                                        + "  %s\n"
                                                        + "Now you have %d %s in the list.\n";
    private final static String DELETE_TASK_MSG_TEMPLATE = "Noted. I've removed this task:\n"
                                                        + "  %s\n"
                                                        + "Now you have %d %s in the list.\n";
    private final static String MARK_DONE_MSG_TEMPLATE = "Nice! I've marked this task as done:\n  %s\n";
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static ArrayList<Task> tasks = new ArrayList<>();

    private static void greet() {
        System.out.println(GREETING_MSG);
    }

    private static void exit() {
        System.out.println(GOODBYE_MSG);
    }


    private static void addTodo(String description) {
        Task taskToAdd = new Todo(description);
        tasks.add(taskToAdd);
        System.out.printf(ADD_TASK_MSG_TEMPLATE, taskToAdd, tasks.size(), tasks.size() <= 1 ? "task" : "tasks");
    }

    private static void addDeadline(String description, String by) {
        Task taskToAdd = new Deadline(description, by);
        tasks.add(taskToAdd);
        System.out.printf(ADD_TASK_MSG_TEMPLATE, taskToAdd, tasks.size(), tasks.size() <= 1 ? "task" : "tasks");
    }

    private static void addEvent(String description, String at) {
        Task taskToAdd = new Event(description, at);
        tasks.add(taskToAdd);
        System.out.printf(ADD_TASK_MSG_TEMPLATE, taskToAdd, tasks.size(), tasks.size() <= 1 ? "task" : "tasks");
    }

    private static void listTasks() {
        System.out.println(LIST_TASK_MSG);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i));
        }
    }

    private static void doneTask(int index) {
        tasks.get(index).markAsDone();
        System.out.printf(MARK_DONE_MSG_TEMPLATE, tasks.get(index));
    }

    private static void deleteTask(int index) {
        Task tasksToRemove = tasks.remove(index);
        System.out.printf(DELETE_TASK_MSG_TEMPLATE, tasksToRemove, tasks.size(), tasks.size() <= 1 ? "task" : "tasks");
    }

    private static void runCommand(String cmd) throws DukeException {
        if (cmd.matches("^todo[ \\t]*$")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (cmd.matches("^deadline[ \\t]*$")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (cmd.matches("^event[ \\t]*$")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else if (cmd.matches("^todo[ \\t]+.+$")) {
            addTodo(cmd.split("[ \\t]+", 2)[1]);
        } else if (cmd.matches("^deadline[ \\t]+.+[ \\t]+/by[ \\t]+.+$")) {
            String[] bySplit = cmd.split("[ \\t]+/by[ \\t]+", 2);
            addDeadline(bySplit[0].split("^deadline[ \\t]+")[1], bySplit[1]);
        } else if (cmd.matches("^event[ \\t]+.+[ \\t]+/at[ \\t]+.+$")) {
            String[] atSplit = cmd.split("[ \\t]+/at[ \\t]+", 2);
            addEvent(atSplit[0].split("^event[ \\t]+")[1], atSplit[1]);
        } else if (cmd.matches("^list[ \\t]*$")) {
            listTasks();
        } else if (cmd.matches("^done[ \\t]+[0-9]+")) {
            int i = Integer.parseInt(cmd.split("^done[ \\t]+")[1]) - 1;
            if (i < 0 || i >= tasks.size()) {
                throw new DukeException(String.format("☹ OOPS!!! I'm sorry, but no task numbered %d", i + 1));
            }
            doneTask(i);
        } else if (cmd.matches("^delete[ \\t]+[0-9]+")) {
            int i = Integer.parseInt(cmd.split("^delete[ \\t]+")[1]) - 1;
            if (i < 0 || i >= tasks.size()) {
                throw new DukeException(String.format("☹ OOPS!!! I'm sorry, but no task numbered %d", i + 1));
            }
            deleteTask(i);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


    public static void main(String[] args) {
        greet();
        for (String cmd = SCANNER.nextLine(); !cmd.equals("bye"); cmd = SCANNER.nextLine()) {
            try {
                runCommand(cmd);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        exit();
    }
}
