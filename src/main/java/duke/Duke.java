package duke;

import duke.dukeException.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.tools.Parser;
import duke.tools.Storage;
import duke.tools.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

/**
 * A to-do bot.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class Duke {

    protected static Storage storage;
    protected static TaskList tasks;
    protected static Ui ui;

    /**
     * Constructor to create Duke.
     * @param filePath Takes in a filepath
     * @throws FileNotFoundException Throws an error when file not found
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | DukeException e) {
            e.printStackTrace();
        }

    }

    String getResponse(String input) {
        String response;
        try {
            response = Parser.parse(input);
            return response;
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Method to run the bot.
     */
    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            try {
                ui.readCommand();
                Scanner s = new Scanner(System.in);
                String command = s.nextLine();
                String response = Parser.parse(command);
                ui.printMessage(response);
                if (response.contains("Bye")) {
                    break;
                }
            } catch (DukeException | IOException e) {
                Ui.printMessage(e.getMessage());
            } finally {
                ui.showDivider();
            }
        }
    }

    /**
     * Method to mark a task done by index.
     * @param i the index for task to remove
     * @throws DukeException throw error
     * @return result
     */
    public static String markDone(int i) throws DukeException {
        if (i > tasks.getSize()) {
            throw new DukeException("OOPS!!! Invalid index.\nPlease choose between 1 to " + tasks.getSize());
        }
        String start = "Nice! I've marked this task as done:";
        tasks.getTask(i - 1).markAsDone();
        TaskList.updateMemory(storage.getPath(), tasks);
        String res = tasks.getTask(i-1).toString();
        String end = "\n" + "  " + res;
        return start + end;
    }

    /**
     * Method to get the Duke TaskList
     */
    public static String getTaskList() {
        if (tasks.getSize() <= 0) {
            return "Hey!! Your list is empty lahhh! \n \n Please add some tasks!!";
        }
        String start = "Sure!! Here are the " + tasks.getSize() + " tasks in your list: \n";
        String res = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            res += "\n" + (i + 1) + ". " + tasks.getTask(i);
        }
        return start + res;
    }

    /**
     * Method to handle toDo tasks.
     * @param input takes in a command by the user
     * @throws DukeException throws an error
     */
    public static String toDo(String input) throws DukeException {
        if (input.equals("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
        }
        String t = input.split("todo ")[1];
        ToDo td = new ToDo(t);
        tasks.addNewTask(td);
        TaskList.updateMemory(storage.getPath(), tasks);
        return "Got it. I've added this task: \n " + "  " + td +
                "\n" + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Method to handle Deadline tasks.
     * @param input takes in a command by the user
     * @throws DukeException throws an error
     */
    public static String deadline(String input) throws DukeException {
        if (!input.contains("/by")){
            String message = "OOPS!!! Please follow this format: \n deadline {task} /by {YYYY-MM-DDTHH:MM}";
            throw new DukeException(message);
        }
        String t = input.split("deadline ")[1];
        Deadline dl = new Deadline(t);
        tasks.addNewTask(dl);
        TaskList.updateMemory(storage.getPath(), tasks);
        return "Got it. I've added this task: \n " + "  " + dl
                + "\n" + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Method to handle Event tasks.
     * @param input takes in a command by the user
     * @throws DukeException throws an error
     */
    public static String event(String input) throws DukeException {
        if (!input.contains("/at ")){
            String message = "OOPS!!! Please follow this format: \n event {task} /at {YYYY-MM-DDTHH:MM}";
            throw new DukeException(message);
        }
        String t = input.split("event ")[1];
        Event e = new Event(t);
        tasks.addNewTask(e);
        TaskList.updateMemory(storage.getPath(), tasks);
        return "Got it. I've added this task: \n " + "  " + e
                + "\n"  + "Now you have " + tasks.getSize()  + " tasks in the list.";
    }

    /**
     * Method to handle delete tasks.
     * @param i takes in a command by the user
     * @throws DukeException throws an error
     */
    public static String deleteTask(int i) throws DukeException {
        if (i > tasks.getSize()) {
            throw new DukeException("OOPS!!! Invalid index.\nPlease choose between 1 to " + tasks.getSize());
        }
        String start = "Noted. I've removed this task: \n";
        String deleted = tasks.getTask(i-1).toString();
        tasks.deleteGivenTask(i);
        TaskList.updateMemory(storage.getPath(), tasks);
        String mid = "  " + deleted;
        String end = "\nNow you have " + tasks.getSize() + " tasks in the list.";
        return start + mid + end;
    }

    /**
     * Method to find task using a search key.
     * @param input search key
     */
    public static String findTask(String input) {
        String searchKey = input.split("find ")[1];
        String start = "Here are the matching tasks in your list: ";
        String res = "";
        int currIndex = 1;
        for (int i = 0; i < tasks.getSize(); i++) {
            String currTask = tasks.getTask(i).getTaskInfo();
            if (currTask.contains(searchKey)) {
                String foundTask = "\n" + currIndex + ". " + tasks.getTask(i);
                res += foundTask;
                currIndex++;
            }
        }
        return start + res;
    }

    /**
     * Main method to run the bot.
     * @param args Takes in user input
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}