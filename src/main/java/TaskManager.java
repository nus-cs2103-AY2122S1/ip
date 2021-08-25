import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the adding, deleting, and marking completion of tasks.
 *
 * @author Adam Ho
 */
public class TaskManager {
    final String DATA_FILE = "./data/duke.txt";
    private enum Command {

        LIST("list"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        DONE("done"),
        INVALID("");

        public final String type;

        Command(String type) {
            this.type = type;
        }
    }

    protected static ArrayList<Task> listOfTasks = new ArrayList<>();

    /**
     * Gets the description of the task.
     * @param input The user input.
     * @return A string representing the description of the task.
     * @throws DukeException.MissingDescriptionException The exception is thrown when the user does not input the task description.
     */
    public String taskDescription(String input) throws DukeException.MissingDescriptionException {
        String[] description = input.split(" ", 2);
        if (description.length == 1) {
            throw new DukeException.MissingDescriptionException();
        }
        return description[1];
    }

    /**
     * Adds a task to the list of tasks.
     * @param task The task to add.
     * @param cmd The command input by the user.
     */
    public void addTask(String task, String cmd) {
        Task t;
        if (cmd.equals("todo")) {
            t = new Todo(task);
            listOfTasks.add(t);
        } else if (cmd.equals("deadline")) {
            String date = task.split(" /by ")[1];
            task = task.split(" /by ")[0];
            t = new Deadline(task, date);
            listOfTasks.add(t);
        } else {
            String date = task.split(" /at ")[1];
            task = task.split(" /at ")[0];
            t = new Event(task, date);
            listOfTasks.add(t);
        }
        Message.add(t);
    }

    /**
     * Deletes a task from the list of tasks.
     * @param taskId The task identifier.
     * @throws DukeException.MissingTaskException The exception is thrown when the user tries to delete a non-existing task.
     */
    public void deleteTask(int taskId) throws DukeException.MissingTaskException {
        try {
            Task t = listOfTasks.get(taskId-1);
            listOfTasks.remove(t);
            --Task.totalTasks;
            Message.delete(t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException.MissingTaskException();
        }
    }

    /**
     * Sets the task to be completed.
     * @param taskId The task identifier.
     * @throws DukeException.MissingTaskException The exception is thrown when the user tries to delete a non-existing task.
     */
    public void markDone(int taskId) throws DukeException.MissingTaskException {
        try {
            Task t = listOfTasks.get(taskId-1);
            t.isDone = true;
            Message.done(t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException.MissingTaskException();
        }
    }

    /**
     * Gets the command from the user input.
     * @param input The user's input.
     * @return A command enum representing the first word of the user's input.
     */
    public Command getCommand(String input) {
        String[] words = input.split(" ", 2);
        String command = words[0];
        for (Command c : Command.values()) {
            if (command.equals(c.type)) {
                return c;
            }
        }
        return Command.INVALID;
    }

    /**
     * Gets input from user and respond to the commands accordingly.
     */
    public void run() {
        Message.greet();
        listOfTasks = TaskListReader.read(DATA_FILE);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            Command c = getCommand(input);
            try {
                switch (c) {
                case LIST:
                    Message.list(listOfTasks);
                    break;
                case DONE:
                    markDone(Integer.parseInt(taskDescription(input)));
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    addTask(taskDescription(input), c.type);
                    break;
                case DELETE:
                    deleteTask(Integer.parseInt(taskDescription(input)));
                    break;
                case INVALID:
                    throw new DukeException.InvalidInputException();
                }

                TaskListWriter.write(DATA_FILE, listOfTasks);
            } catch (DukeException e) {
                Message.error(e.toString());
            } finally {
                input = sc.nextLine();
            }
        }
        Message.exit();
    }
}
