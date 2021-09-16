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
 * A TaskList Bot that allows user to add or update various types of Tasks.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class Duke {

    protected static Storage storage;
    protected static TaskList tasks;
    protected static Ui ui;
    protected boolean status;

    /**
     * Constructor to create Duke.
     * @param filePath Takes in a filepath
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
            status = true;
        } catch (FileNotFoundException | DukeException e) {
            e.printStackTrace();
        }

    }

    /**
     * Returns a response from Duke based on the user input. Duke will terminate when "bye" command is recieved.
     * @param input A string of commands from the user.
     * @return A response from Duke associated with the given input.
     */
    public String getResponse(String input) {
        String response;
        try {
            response = Parser.parse(input);
            if (input.equals("bye")) {
                endDuke();
            }
            return response;
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Return a Boolean value to check if Duke has been terminated by the user.
     * @return A Boolean value to represent status of Duke.
     */
    public Boolean isAlive() {
        return this.status;
    }

    /**
     * A method to update Duke's status to false which represents termination.
     */
    public void endDuke() {
        this.status = false;
    }

    /**
     * A method to start up Duke bot for CLI.
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
     * Returns a String message associated to markDone. It also updates the duke.txt file.
     * @param i The index of the task that the user wants to remove.
     * @throws DukeException throw a Duke error.
     * @return A String message associated to markDone.
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
     * Returns a String message containing the Task List after reading Duke.txt.
     * @return The Task List stored in Duke.
     */
    public static String getTaskList() {
        if (tasks.getSize() <= 0) {
            return "Hey!! Your list is empty lahhh! \n \n Please add some tasks!!";
        }
        String start = "Sure!! You have " + tasks.getSize() + " tasks in your list: \n";
        String res = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            res += "\n" + (i + 1) + ". " + tasks.getTask(i);
        }
        return start + res;
    }

    /**
     * Returns a String message associated to toDo. It also adds a new todo task into duke.txt file.
     * @param input takes in a command by the user
     * @throws DukeException throw a Duke error.
     * @return A String message associated to toDo.
     */
    public static String toDo(String input) throws DukeException {
        if (input.equals("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
        }
        String t = input.split("todo ")[1];
        ToDo td = new ToDo(t);
        tasks.addNewTask(td);
        TaskList.updateMemory(storage.getPath(), tasks);
        return "Got it. I've added this task: \n " + "  " + td + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Returns a String message associated to deadline. It also adds a new deadline task into duke.txt file.
     * @param input takes in a command by the user
     * @throws DukeException throw a Duke error.
     * @return A String message associated to deadline.
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
        return "Got it. I've added this task: \n " + "  " + dl + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Returns a String message associated to event. It also adds a new event task into duke.txt file.
     * @param input takes in a command by the user
     * @throws DukeException throw a Duke error.
     * @return A String message associated to event.
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
        return "Got it. I've added this task: \n " + "  " + e + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Returns a String message associated to deleteTask action. It also removes that task from duke.txt file.
     * @param i takes in an index of task to be deleted.
     * @throws DukeException throw a Duke error.
     * @return A String message associated to deleteTask action.
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
     * Returns a String message associated to findTask after searching through duke.txt file to find the task
     * related to the user input.
     * @param input takes in a command by the user
     * @throws DukeException throw a Duke error.
     * @return A String message of the relevant result from the search.
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
     * A Main method to run Duke.
     * @param args Takes in user input.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}