import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the adding, deleting, and marking completion of tasks.
 *
 * @author Adam Ho
 */
public class TaskManager {
    private final String TASK_FILE = "./data/duke.txt";
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
     * Gets input from user and respond to the commands accordingly.
     */
    public void run() {
        Message.greet();
        try {
            loadTaskList(TASK_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

                saveTaskList(TASK_FILE);
            } catch (IOException e) {
                System.out.println("Oops, something went wrong!");
            } catch (DukeException e) {
                Message.error(e.toString());
            } finally {
                input = sc.nextLine();
            }
        }
        Message.exit();
    }

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

    public void saveTaskList(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String tasks = "";
        for (Task t : listOfTasks) {
            String done = t.isDone ? "1" : "0";
            Command c = getCommand(t.getClass().getName().toLowerCase());
            switch (c) {
            case TODO:
                tasks += "T" + " uwu " + done + " uwu " + t.description + "\n";
                break;
            case DEADLINE:
                Deadline d = (Deadline) t;
                tasks += "D" + " uwu " + done + " uwu " + d.description + " uwu " + d.by + "\n";
                break;
            case EVENT:
                Event e = (Event) t;
                tasks += "E" + " uwu " + done + " uwu " + e.description + " uwu " + e.at + "\n";
            }
        }
        fw.write(tasks);
        fw.close();
    }

    public void loadTaskList(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            Task t;
            String[] taskFormat = s.nextLine().split(" uwu ");
            if (taskFormat[0].equals("T")) {
                t = new Todo(taskFormat[2]);
            } else if (taskFormat[0].equals("D")) {
                t = new Deadline(taskFormat[2], taskFormat[3]);
            } else {
                t = new Event(taskFormat[2], taskFormat[3]);
            }
            t.isDone = taskFormat[1].equals("1");
            listOfTasks.add(t);
        }
    }
}
