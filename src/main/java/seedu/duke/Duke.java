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
public class Duke {
    /**
     * Represents a parser for interpreting user inputs.
     * A <code>Parser</code> object takes in user inputs and
     * interprets it to Duke to execute relevant tasks.
     */
    private class Parser {
        private String[] listOfWords = new String[0];
        private DateTimeManager manager = new DateTimeManager(
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        private int taskIndex = -1;
        private LocalDate date = LocalDate.now();

        private Command parseString(String userInput) throws DukeException {
            // Separate them with space
            String[] arrOfCommandWords = userInput.split(" ");
            this.listOfWords = arrOfCommandWords;
            String commandWord = arrOfCommandWords[0];

            if (arrOfCommandWords.length <= 1) {
                return parseOneWordCommand(commandWord);
            }

            switch (commandWord) {
            case "todo": // Fallthrough
            case "deadline": // Fallthrough
            case "event":
                return parseTaskType(commandWord, userInput);
            case "delete": // Fallthrough
            case "done":
                // arrOfCommandWords = {"done", "taskNumber"}
                return parseTaskModification(commandWord, arrOfCommandWords[1]);
            case "find":
                // arrOfCommandWords = {"find", "taskNumber"}
                return new FindCommand(ui, taskList, arrOfCommandWords[1]);
            case "get":
                // arrOfCommandWords = {"get", "dateString"}
                parseGetTasksOnDate(arrOfCommandWords[1]);
            default:
                throw new DukeException("Sorry, I don't know what that means.");
            }
        }

        private Command parseOneWordCommand(String commandWord) throws DukeException {
            switch (commandWord) {
            case "bye":
                return new ExitCommand(ui, taskList);
            case "list":
                return new ListCommand(ui, taskList);
            case "help":
                return new HelpCommand(ui, taskList);
            default:
                handleInvalidInputs(commandWord);
                // Will not reach here since handleInvalidInputs(commandWord)
                // will throw an error
                return null;
            }
        }

        private Command parseGetTasksOnDate(String dateString) throws DukeException {
            try {
                LocalDate tasksDate = manager.parseDateTime(dateString);
                return new GetCommand(ui, taskList, tasksDate, dateTasks);
            } catch (DateTimeParseException | DukeException e) {
                throw new DukeException("Invalid date format.");
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
                listOfWords[1] = description;
                LocalDate date = manager.parseDateTime(
                        userInput.substring(indexOfDate + command.length())
                );
                this.date = date;
            } catch (DateTimeParseException | DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        private Command parseTaskModification(String commandWord, String numberInput)
                throws DukeException {
            try {
                int index = Integer.parseInt(numberInput) - 1;
                this.taskIndex = index;
                switch (commandWord) {
                case "done":
                    return new DoneCommand(ui, taskList, taskIndex, storage);
                case "delete":
                    return new DeleteCommand(ui, taskList, taskIndex, storage);
                default:
                    throw new DukeException("Unable to update task.");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number");
            }
        }

        private Command parseTaskType(String type, String userInput) throws DukeException {
            switch (type) {
            case "todo":
                int startOfDescription = userInput.indexOf(" ") + 1;
                String description = userInput.substring(startOfDescription);
                return new ToDoCommand(ui, taskList,
                        description, storage);
            case "deadline":
                parseDescription(userInput, "/by ");
                // listOfWords = {"commandType", "date"}
                return new DeadlineCommand(ui, taskList, listOfWords[1],
                        date, storage);
            case "event":
                parseDescription(userInput, "/at ");
                // listOfWords = {"commandType", "date"}
                return new EventCommand(ui, taskList, listOfWords[1],
                        date, storage);
            default:
                throw new DukeException("Type of task is invalid.");
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
        private String executeTasks(Command type) throws DukeException {
            String response = type.execute();
            type.updateDateTasks(dateTasks, manager);
            if (type.updatesTaskList()) {
                taskList = type.getTaskList();
            }
            isExit = type.isExit();

            return response;
        }

    }

    /**
     * Task list to keep track.
     */
    private TaskList taskList = new TaskList();

    private boolean isExit = false;

    /**
     * Storage to handle file manipulation.
     */
    private Storage storage = new Storage("./data/duke.text");
    private Ui ui = new Ui();
    private Parser parser = new Parser();
    private HashMap<LocalDate, ArrayList<Task>> dateTasks = new HashMap<>();

    public Duke() {
        taskList = storage.loadData(dateTasks, taskList);
    }

    /**
     * Public constructor for Duke
     */
    public Duke(TaskList taskList, Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = storage.loadData(dateTasks, taskList);
    }

    /**
     * Prints Duke's greetings.
     */
    protected String getGreeting() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        try {
            Command command = parser.parseString(input);
            String response = parser.executeTasks(command);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    protected boolean getExit() {
        return isExit;
    }

    /**
     * Runs the Duke chatbot.
     */
    private void run() {
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

        Duke duke = new Duke();
        duke.run();
    }
}
