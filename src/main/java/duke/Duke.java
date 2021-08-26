package duke;

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
    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Method to run the bot.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.readCommand();
                Scanner s = new Scanner(System.in);
                String command = s.nextLine();
                isExit = Parser.parse(command);
                //Command c = duke.Parser.parse(fullCommand);
                //c.execute(tasks, ui, storage);
            //} catch (duke.DukeException e) {
            //    ui.showError(e.getMessage());
            } catch (DukeException | IOException e) {
                e.printStackTrace();
            } finally {
                ui.showDivider();
            }
        }
        ui.showGoodbyeMessage();
    }

    /**
     * Method to mark a task done by index.
     * @param i the index for task to remove
     * @throws DukeException throw error
     */
    public static void markDone(int i) throws DukeException {
        System.out.println("Nice! I've marked this task as done: ");
        tasks.getTask(i - 1).markAsDone();
        TaskList.updateMemory(storage.getPath(), tasks);
        String res = tasks.getTask(i-1).toString();
        System.out.println(res + "\n");
    }

    /**
     * Method to get the Duke TaskList
     */
    public static void getTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            String res = (i + 1) + ". " + tasks.getTask(i);
            System.out.println(res);
        }
    }

    /**
     * Method to handle toDo tasks.
     * @param input takes in a command by the user
     * @throws DukeException throws an error
     */
    public static void toDo(String input) throws DukeException {
        if (input.equals("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
        }
        String t = input.split("todo ")[1];
        ToDo td = new ToDo(t);
        tasks.addNewTask(td);
        TaskList.updateMemory(storage.getPath(), tasks);
        System.out.println("Got it. I've added this task: \n " + td +
                           "\n" + "Now you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Method to handle Deadline tasks.
     * @param input takes in a command by the user
     * @throws DukeException throws an error
     */
    public static void deadline(String input) throws DukeException {
        String t = input.split("deadline ")[1];
        Deadline dl = new Deadline(t);
        tasks.addNewTask(dl);
        TaskList.updateMemory(storage.getPath(), tasks);
        System.out.println("Got it. I've added this task: \n " + dl
               + "\n" + "Now you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Method to handle Event tasks.
     * @param input takes in a command by the user
     * @throws DukeException throws an error
     */
    public static void event(String input) throws DukeException {
        String t = input.split("event ")[1];
        Event e = new Event(t);
        tasks.addNewTask(e);
        TaskList.updateMemory(storage.getPath(), tasks);
        System.out.println("Got it. I've added this task: \n " + e
                            + "/n"  + "Now you have " + tasks.getSize()  + " tasks in the list.");
    }

    /**
     * Method to handle delete tasks.
     * @param input takes in a command by the user
     * @throws DukeException throws an error
     */
    public static void deleteTask(int i) throws DukeException {
        System.out.println("Noted. I've removed this task: ");
        String deleted = tasks.getTask(i-1).toString();
        tasks.deleteGivenTask(i - 1);
        TaskList.updateMemory(storage.getPath(), tasks);
        System.out.println(" " + deleted);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Method to find task using a search key.
     * @param input search key
     */
    public static void findTask(String input) {
        String searchKey = input.split("find ")[1];
        System.out.println("Here are the matching tasks in your list:");
        int currIndex = 1;
        for (int i = 0; i < tasks.getSize(); i++) {
            String currTask = tasks.getTask(i).getTaskInfo();
            if (currTask.contains(searchKey)) {
                String foundTask = currIndex + ". " + tasks.getTask(i);
                System.out.println(foundTask);
                currIndex++;
            }
        }
    }

    /**
     * Main method to run the bot.
     * @param args Takes in user input
     * @throws FileNotFoundException Throw errors
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Duke("data/duke.txt").run();
    }
}