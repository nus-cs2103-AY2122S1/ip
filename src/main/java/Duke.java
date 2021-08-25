import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String GREETING_MSG = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String GOODBYE_MSG = "Bye. Hope to see you again soon!";
    private static final String LIST_TASK_MSG = "Here are the tasks in your list: ";
    private static final String ADD_TASK_MSG_TEMPLATE = "Got it. I've added this task: \n"
                                                        + "  %s\n"
                                                        + "Now you have %d %s in the list.\n";
    private static final String DELETE_TASK_MSG_TEMPLATE = "Noted. I've removed this task: \n"
                                                        + "  %s\n"
                                                        + "Now you have %d %s in the list.\n";
    private static final String MARK_DONE_MSG_TEMPLATE = "Nice! I've marked this task as done: \n  %s\n";
    private static final String UNEXPECTED_ERROR_MSG = "Something went wrong";
    private static final Scanner SCANNER = new Scanner(System.in);

    private static Storage taskStorage;
    private static TaskList tasks = new TaskList();

    private static void greet() {
        System.out.println(GREETING_MSG);
    }

    private static void exit() {
        System.out.println(GOODBYE_MSG);
    }


    private static void addTodo(String description) throws IOException {
        Task taskToAdd = new Todo(description);
        System.out.printf(
                ADD_TASK_MSG_TEMPLATE,
                tasks.addTask(taskToAdd), 
                tasks.getTaskCount(), 
                tasks.getTaskCount() <= 1 ? "task" : "tasks"
        );
        taskStorage.backup(tasks);
    }

    private static void addDeadline(String description, String by) throws IOException {
        Task taskToAdd = new Deadline(description, by);
        System.out.printf(
                ADD_TASK_MSG_TEMPLATE,
                tasks.addTask(taskToAdd),
                tasks.getTaskCount(),
                tasks.getTaskCount() <= 1 ? "task" : "tasks"
        );
        taskStorage.backup(tasks);
    }

    private static void addEvent(String description, String at) throws IOException {
        Task taskToAdd = new Event(description, at);
        System.out.printf(
                ADD_TASK_MSG_TEMPLATE,
                tasks.addTask(taskToAdd),
                tasks.getTaskCount(),
                tasks.getTaskCount() <= 1 ? "task" : "tasks"
        );
        taskStorage.backup(tasks);
    }

    private static void listTasks() {
        System.out.println(LIST_TASK_MSG);
        System.out.print(tasks);
    }

    private static void doneTask(int index) throws IOException {
        tasks.getTask(index).markAsDone();
        System.out.printf(MARK_DONE_MSG_TEMPLATE, tasks.getTask(index));
        taskStorage.backup(tasks);
    }

    private static void deleteTask(int index) throws IOException {
        System.out.printf(
                DELETE_TASK_MSG_TEMPLATE,
                tasks.deleteTask(index),
                tasks.getTaskCount(),
                tasks.getTaskCount() <= 1 ? "task" : "tasks"
        );
        taskStorage.backup(tasks);
    }

    private static void runCommand(String cmd) throws DukeException, IOException {
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
        } else if (cmd.matches("^done[ \\t]+[0-9]+$")) {
            int i = Integer.parseInt(cmd.split("^done[ \\t]+")[1]) - 1;
            if (i < 0 || i >= tasks.getTaskCount()) {
                throw new DukeException(String.format("☹ OOPS!!! I'm sorry, but no task numbered %d", i + 1));
            }
            doneTask(i);
        } else if (cmd.matches("^delete[ \\t]+[0-9]+$")) {
            int i = Integer.parseInt(cmd.split("^delete[ \\t]+")[1]) - 1;
            if (i < 0 || i >= tasks.getTaskCount()) {
                throw new DukeException(String.format("☹ OOPS!!! I'm sorry, but no task numbered %d", i + 1));
            }
            deleteTask(i);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


    public static void main(String[] args) {
        try {
            greet();
            taskStorage = new Storage("data/duke.txt");
            tasks = new TaskList(taskStorage.load());
            for (String cmd = SCANNER.nextLine(); !cmd.equals("bye"); cmd = SCANNER.nextLine()) {
                try {
                    runCommand(cmd);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(UNEXPECTED_ERROR_MSG);
        } finally {
            exit();
        }
    }
}
