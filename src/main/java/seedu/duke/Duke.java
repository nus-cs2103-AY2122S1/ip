package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.DeadlineCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.EventCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.GetCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.ToDoCommand;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Represents a chatbot for organising user tasks and to do list.
 * A <code>Duke</code> object takes in user commands and updates the
 * user's tasks.
 */
class Duke {

    private enum UserCommands {
        DONE, TODO, DEADLINE, EVENT, FIND, GET, DELETE, LIST, BYE;
    }

    /**
     * Represents a parser for interpreting user inputs.
     * A <code>Parser</code> object takes in user inputs and
     * interprets it to Duke to execute relevant tasks.
     */
    private class Parser {
        private String[] list_of_words = new String[0];
        private String userInput ="";
        private DateTimeManager manager = new DateTimeManager(
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        private int taskIndex = -1;
        private LocalDate date = LocalDate.now();

        private Command parseString(String userInput) throws DukeException {
            this.userInput = userInput;

            if (userInput.equals("bye")) {
                return new ExitCommand(ui, taskList);
            } else if (userInput.equals("list")) {
                return new ListCommand(ui, taskList);
            } else if (userInput.equals("help")) {
                return new HelpCommand(ui, taskList);
            }

            // Separate them with space
            String[] arrOfCommandWords = userInput.split(" ");
            if (arrOfCommandWords.length <= 1) {
                handleInvalidInputs(userInput);
            }

            this.list_of_words = arrOfCommandWords;
            // Check the command word
            String commandWord = arrOfCommandWords[0];
            switch (commandWord) {
            case "todo":
                return new ToDoCommand(ui, taskList,
                        arrOfCommandWords[1], storage);
            case "deadline":
                parseDescription(userInput, "/by ");
                return new DeadlineCommand(ui, taskList, list_of_words[1],
                        date, storage);
            case "event":
                parseDescription(userInput, "/at ");
                return new EventCommand(ui, taskList, list_of_words[1],
                        date, storage);
            case "done":
                try {
                    int index = Integer.parseInt(arrOfCommandWords[1]) - 1;
                    this.taskIndex = index;
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid task number");
                }
                return new DoneCommand(ui, taskList, taskIndex, storage);
            case "find":
                return new FindCommand(ui, taskList, list_of_words[1]);
            case "get":
                try {
                    manager.parseDateTime(arrOfCommandWords[1]);
                    return new GetCommand(ui, taskList, list_of_words[1], dateTasks);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Invalid date format.");
                }
            case "delete":
                try {
                    int index = Integer.parseInt(arrOfCommandWords[1]) - 1;
                    this.taskIndex = index;
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid task number");
                }
                return new DeleteCommand(ui, taskList, taskIndex, storage);
            default:
                throw new DukeException("Sorry, I don't know what that means.");
            }
        }

        private void parseDescription(String userInput, String command) {
            try {
                int indexOfDate = userInput.indexOf(command);
                int startOfDescription = userInput.indexOf(' ');
                if (indexOfDate < 0) {
                    throw new DukeException("No date specified for task.");
                }

                String description = userInput.substring(startOfDescription, indexOfDate);
                list_of_words[1] = description;
                LocalDate date = manager.parseDateTime(
                        userInput.substring(indexOfDate + command.length())
                );
                this.date = date;
            } catch (DateTimeParseException | DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        /**
         * Handles invalid inputs by the user.
         *
         * @param input The user input to Duke.
         */
        private void handleInvalidInputs(String input) throws DukeException {
            switch (input) {
            case "todo": // fallthrough
            case "deadline": // fallthrough
            case "event": {
                throw new DukeException(
                        String.format(
                                "☹ OOPS!!! The description of a %s cannot be empty.",
                                input
                        )
                );
            }
            case "done": // fallthrough
            case "delete":
                throw new DukeException("Please enter the task index.");
            case "get":
                throw new DukeException("Please enter a date in dd/MM/yyyy format.");
            case "find":
                throw new DukeException("Please enter keyword to search for.");
            default:
                throw new DukeException(
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                );
            }
        }

        /**
         * Execute program per the user input parsed.
         *
         * @param type The type of Command specifying the action to
         *             be executed.
         */
        private void executeTasks(Command type) throws DukeException {
            type.execute();
            if (type.updatesTaskList()) {
                taskList = type.getTaskList();
            }
        }

    }

    /**
     * Task list to keep track.
     */
    private TaskList taskList;
    /**
     * Storage to handle file manipulation.
     */
    private Storage storage;
    private Ui ui;
    private Parser parser = new Parser();
    private HashMap<LocalDate, ArrayList<Task>> dateTasks = new HashMap<>();

    /**
     * Public constructor for Duke
     */
    public Duke(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Prints Duke's greetings.
     */
    private void greet() {
        ui.divide();
        ui.outputMessage(
                String.format("Hello! I'm Duke\n%4sWhat can I do for you?",
                        " "));
        ui.divide();
    }

    /**
     * Runs the Duke chatbot.
     */
    private void run() {
        taskList = storage.loadData(dateTasks, taskList);

        // Greeting the user
        greet();

        // Taking in commands
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine().strip();
            try {
                Command type = parser.parseString(command);
                parser.executeTasks(type);
                if (type.isExit()) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                new HelpCommand(ui, taskList).execute();
            }
        }
        // Close the scanner if "bye" command is given and
        // the Duke exits the while loop.
        sc.close();
    }

    /**
     * Main method to execute Duke's functions.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String directoryPath = "./data";
        String filePath = "./data/duke.text";

        File directory = new File(directoryPath);
        // Check folder exists
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Successfully created directory.");
            } else {
                System.out.println("An error occurred");
            }
        }

        Duke duke = new Duke(new TaskList(), new Storage(filePath), new Ui());
        duke.run();
    }
}
