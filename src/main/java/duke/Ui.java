package duke;

import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.Undo;

/**
 * Handles all the interaction with the user.
 */
public class Ui {

    /**
     * Global variables
     */
    private static final String LINE = "\t______________________________________________________________________";
    private static final String END_LINE = "\t======================================================================\n";
    private static final String LOGO = "\n"
            + "███████████████████████████████\n"
            + "█▄─▄─▀█─█─█▄─██─▄█─▄─▄─█▄─██─▄█\n"
            + "██─▄─▀█─▄─██─██─████─████─██─██\n"
            + "▀▄▄▄▄▀▀▄▀▄▀▀▄▄▄▄▀▀▀▄▄▄▀▀▀▄▄▄▄▀▀";
    private Items items;
    private Scanner sc = new Scanner(System.in);
    private Storage storage;
    private Parser parser;
    private String[] prevCommand;
    private Undo undo;

    /**
     * Instantiates a new ui object.
     */
    public Ui(Storage storage) {
        items = new Items();
        this.storage = storage;
        parser = new Parser();
        undo = new Undo(items);
    }

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println(LOGO);
        printMessage("Hello! I'm Bhutu, your personal chatbot!\nWhat can I do for you?");
    }

    /**
     * Gets the user input.
     *
     * @return The string representation of the user input.
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Prints the goodbye text.
     */
    public void printGoodBye() {
        printMessage("Going so soon? Hope to see you again!");
    }

    /**
     * Prints all bot messages in a specific format.
     *
     * @param message message from the bot.
     */
    public static void printMessage(String message) {
        message = "\t" + message.replace("\n", "\n\t");
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(END_LINE);
    }

    /**
     * Interacts with the user.
     */
    public String getResponse(String input) {
        String[] inputWords;
        String output = "";
        String fileTask;
        inputWords = input.split("\\s+");

        if (inputWords.length == 0) {
            output = "Sorry, I don't understand empty statements.";
            return output;
        }
        String command = inputWords[0];

        assert command != null : "Command cannot be NULL";
        try {
            String[] task = parser.compileInput(inputWords);
            switch (command) {
            case "list":
                output = items.printList();
                break;
            case "done":
                int idx = Integer.parseInt(inputWords[1]);
                output = items.markDone(idx);
                fileTask = storage.getFileLine(idx);
                fileTask = fileTask.substring(0, 4) + "1" + fileTask.substring(5);
                storage.updateListTask(idx, fileTask);
                break;
            case "bye":
                Duke.isRunning = false;
                output = "Going so soon? I'll be waiting for you.";
                break;
            case "todo":
                output = items.addItem(new Todo(task[0]));
                fileTask = "T | 0 | " + task[0];
                storage.addToFile(fileTask);
                break;
            case "event":
                output = items.addItem(new Event(task[0], task[1]));
                fileTask = "E | 0 | " + task[0] + " | " + task[1];
                storage.addToFile(fileTask);
                break;
            case "deadline":
                output = items.addItem(new Deadline(task[0], task[1]));
                fileTask = "D | 0 | " + task[0] + " | " + task[1];
                storage.addToFile(fileTask);
                break;
            case "delete":
                int id = Integer.parseInt(inputWords[1]);
                output = items.deleteItem(id);
                storage.deleteFromFile(id);
                break;
            case "find":
                output = items.findTask(task[0]);
                break;
            case "undo":
                undo.undoTask(task);
                int index = Integer.parseInt(inputWords[1]);
                fileTask = storage.getFileLine(index);
                fileTask = fileTask.substring(0, 4) + "0" + fileTask.substring(5);
                storage.updateListTask(index, fileTask);
                break;
            default:
                output = "I don't recognise this command\n"
                        + "Try 'list', 'todo', 'event', 'deadline', 'done', 'find', 'undo' or 'bye'";
                break;
            }
            assert !output.equals(""): "Unable to generate response. Please try again.";
            prevCommand = inputWords;
            return output;
        } catch (Exception dukeException) {
            return dukeException.getMessage();
        }
    }
}
