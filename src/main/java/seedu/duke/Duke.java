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
import seedu.duke.command.ReminderCommand;
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
        private final DateTimeManager manager = new DateTimeManager(
                DateTimeFormatter.ofPattern("d/MM/yyyy"));
        private int taskIndex = -1;
        private LocalDate date = LocalDate.now();

        private Command parseString(String userInput) throws DukeException {
            // Separate them with space
            String[] arrOfCommandWords = userInput.split(" ");
            String commandWord = arrOfCommandWords[0];
            Command.CommandType type = Command.CommandType.parseTypeFromCommandWord(commandWord);

            if (arrOfCommandWords.length <= 1) {
                Command command = parseOneWordCommand(type);
                assert command != null: "Command is valid.";
                return command;
            }

            switch (type) {
            case TODO: // Fallthrough
            case DEADLINE: // Fallthrough
            case EVENT:
                return parseTaskType(type, userInput);
            case DELETE: // Fallthrough
            case DONE:
                // arrOfCommandWords is {"done", "taskNumber"}
                return parseTaskModification(type, arrOfCommandWords[1]);
            case FIND:
                // arrOfCommandWords is {"find", "taskNumber"}
                return new FindCommand(ui, taskList, arrOfCommandWords[1]);
            case GET:
                // arrOfCommandWords is {"get", "dateString"}
                return parseGetTasksOnDate(arrOfCommandWords[1]);
            default:
                throw new DukeException("Sorry, I don't know what that means.");
            }
        }

        private Command parseOneWordCommand(Command.CommandType commandWord) throws DukeException {
            switch (commandWord) {
            case BYE:
                return new ExitCommand(ui, taskList);
            case LIST:
                return new ListCommand(ui, taskList);
            case HELP:
                return new HelpCommand(ui, taskList, dateTasks);
            case REMINDER:
                return new ReminderCommand(ui, taskList, dateTasks);
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

        private String parseDescriptionWithDate(String userInput, String command) {
            String description = "";

            assert command.equals("/by ") || command.equals("/at ")
                    : "Date indicated by /by or /at ";

            try {
                int indexOfDate = userInput.indexOf(command);
                int startOfDescription = userInput.indexOf(' ');

                assert startOfDescription != -1 : "Description is not empty.";

                if (indexOfDate < 0) {
                    throw new DukeException("No date specified for task.");
                }

                this.date = manager.parseDateTime(
                        userInput.substring(indexOfDate + command.length())
                );
                description = userInput.substring(startOfDescription, indexOfDate).strip();
                return description;
            } catch (DateTimeParseException | DukeException e) {
                System.out.println(e.getMessage());
            }
            return description;
        }

        private Command parseTaskModification(Command.CommandType commandType, String numberInput)
                throws DukeException {
            try {
                taskIndex = Integer.parseInt(numberInput) - 1;
                switch (commandType) {
                case DONE:
                    return new DoneCommand(ui, taskList, taskIndex, storage);
                case DELETE:
                    return new DeleteCommand(ui, taskList, dateTasks, taskIndex, storage);
                default:
                    throw new DukeException("Unable to update task.");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number");
            }
        }

        private Command parseTaskType(Command.CommandType type, String userInput) throws DukeException {
            switch (type) {
            case TODO:
                int startOfDescription = userInput.indexOf(" ") + 1;
                String description = userInput.substring(startOfDescription).strip();
                return new ToDoCommand(ui, taskList, description, storage);
            case DEADLINE:
                description = parseDescriptionWithDate(userInput, "/by ");

                assert !description.equals("") : "Description is not empty.";

                // listOfWords = {"commandType", "date"}
                return new DeadlineCommand(ui, taskList, description,
                        date, storage);
            case EVENT:
                description = parseDescriptionWithDate(userInput, "/at ");

                assert !description.equals("") : "Description is not empty.";

                // listOfWords = {"commandType", "date"}
                return new EventCommand(ui, taskList, description,
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
        private void handleInvalidInputs(Command.CommandType input) throws DukeException {
            switch (input) {
            case TODO: // fallthrough
            case DEADLINE: // fallthrough
            case EVENT: {
                throw new DukeException(
                        String.format(
                                "☹ OOPS!!! The description of a %s cannot be empty.",
                                input
                        )
                );
            }
            case DONE: // fallthrough
            case DELETE:
                throw new DukeException("Please enter the task index.");
            case GET:
                throw new DukeException("Please enter a date in dd/MM/yyyy format.");
            case FIND:
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

            if (type.isUpdatesTaskList()) {
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

    private final Ui ui = new Ui();
    private final Parser parser = new Parser();
    private HashMap<LocalDate, ArrayList<Task>> dateTasks = new HashMap<>();

    /**
     * Storage to handle file manipulation.
     */
    private final Storage storage = new Storage("./data/duke.text", dateTasks);

    public void init() {
        String directoryPath = "./data";

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

        taskList = storage.loadData(taskList);
    }

    /**
     * Prints Duke's greetings.
     */
    protected String getGreeting() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    protected HashMap<LocalDate, ArrayList<Task>> getDateTasks() {
        return dateTasks;
    }

    protected Storage getStorage() {
        return storage;
    }

    protected TaskList getTaskList() {
        return taskList;
    }

    protected String getResponse(String input) {
        try {
            Command command = parser.parseString(input);
            String response = parser.executeTasks(command);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    protected String getReminder() {
        Command reminder = new ReminderCommand(ui, taskList, dateTasks);
        try {
            String message = reminder.execute();
            return message;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    protected boolean getExit() {
        return isExit;
    }

    /**
     * Runs the Duke chat bot.
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
                new HelpCommand(ui, taskList, dateTasks).execute();
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
