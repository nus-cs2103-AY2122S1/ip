package duke.utility;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * <h2>Parser</h2>
 * Auxiliary class that parses commands from the user and hands the parsed commands and details to
 * {@link duke.utility.TaskList} to track.
 */

public class Parser {

    public static final String UNABLE_TO_LOG_TASK_MESSAGE = "Unable to log task.";
    public static final String MISSING_DETAILS_MESSAGE = "Invalid task description: missing details!";
    public static final String UNKNOWN_ERROR_MESSAGE = "Unexpected error occurred. Please check input.";
    public static final String EMPTY_INPUT_MESSAGE = "Please type some commands!";
    public static final String INVALID_COMMAND_MESSAGE = "Invalid command!";
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String TIME_FORMAT = "HHmm";
    public static final String INVALID_LINE_NUMBER_MESSAGE = "Invalid input for delete command. Please enter [delete] "
            + "followed by the number of the line to delete\ne.g. delete 2";
    public static final String INVALID_DATETIME_MESSAGE = "Invalid task description: invalid date/time\n"
            + "Please use [command type] [task name] / [dd-mm-yyyy] [time (in 24hr format)]\n"
            + "e.g. event lecture / 21-02-2021 1500";
    public static final String HELP_MESSAGE = "visit https://whoisjustinngo.github.io/ip/ to view the user guide";

    private final TaskList taskList;
    private final Storage storage;


    Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }


    /**
     * Parses a given command from the user.
     * @param command command from the user.
     * @return a string that updates the user of the outcome of the command.
     */
    String parseCommand(String command) {
        if (command.trim().length() == 0) {
            return EMPTY_INPUT_MESSAGE;
        }
        String[] commandTokens = getStringTokens(command, " ");
        // parse command
        try {
            switch (commandTokens[0]) {
            case "bye":
                return "TERMINATE";
            case "done":
                return this.parseDoneTask(command.substring(5).trim());
            case "delete":
                return this.parseDeleteTask(command.substring(7).trim());
            case "schedule":
                return this.parseViewScheduleTask(command.substring(9).trim());
            case "find":
                return this.taskList.searchTasks(command.substring(5).trim());
            case "list":
                return this.taskList.getAllTasks();
            case "help":
                return HELP_MESSAGE;
            default:
                return this.parseNewTask(command.trim());
            }
        } catch (DukeException ex) {
            return ex.getMessage();
        } catch (DateTimeParseException ex) {
            return INVALID_DATETIME_MESSAGE;
        } catch (IOException ex) {
            return UNABLE_TO_LOG_TASK_MESSAGE;
        } catch (StringIndexOutOfBoundsException ex) {
            return MISSING_DETAILS_MESSAGE;
        } catch (NumberFormatException ex) {
            return INVALID_LINE_NUMBER_MESSAGE;
        } catch (Exception ex) {
            return UNKNOWN_ERROR_MESSAGE;
        }
    }


    private String parseViewScheduleTask(String date) throws DukeException.EmptyScheduleException {
        LocalDate dateToView = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
        return this.taskList.getTasksOnDate(dateToView);
    }


    private String parseDeleteTask(String taskNum) throws DukeException.InvalidTaskNumException, IOException {
        int lineNumber = Integer.parseInt(taskNum.trim());
        String message = this.taskList.deleteTask(lineNumber);
        if (storageIsPresent()) {
            this.storage.modifyExistingLog(lineNumber - 1, Storage.EditTaskLogOperations.DELETE);
            // storage is 0 indexed
        }
        return message;
    }


    private String parseDoneTask(String taskName) throws IOException, DukeException.NoSuchTaskException,
            DukeException.TaskAlreadyCompleteException {
        String message = this.taskList.markAsCompleted(taskName);
        if (storageIsPresent()) {
            this.storage.modifyExistingLog(this.taskList.getTaskIndex(taskName),
                    Storage.EditTaskLogOperations.MARK_AS_COMPLETED);
        }
        return message;
    }


    private String parseNewTask(String command) throws DukeException.DuplicateTaskException,
            DukeException.InvalidTaskDescriptionException, DukeException.InvalidCommandException, IOException {

        String[] taskTypeAndDetails = preProcessCommand(command);
        String taskType = taskTypeAndDetails[0];
        String taskDetails = taskTypeAndDetails[1];

        // if task is todo, then name = details, else name will be before the "/"
        String[] detailTokens = getStringTokens(taskDetails, "/");
        String taskName = detailTokens[0].trim();
        Task task = null;
        String message = null;
        /*
        Have to hold on to the message instead of returning the message immediately because a message is confirmation
        that the task is successfully added and only after that can the task be logged in storage
        */
        if ("todo".equals(taskType)) {
            task = new ToDo(taskName);
            message = this.taskList.add(task);
            if (storageIsPresent()) {
                this.storage.append("T", "<F>", taskName);
            }
        } else {
            String[] tokens = getStringTokens(taskDetails, "/");
            // tokens has the structure [task name, dateTime]
            String[] dateAndTime = getStringTokens(tokens[1].trim(), " ");
            // dateAndTime has the structure [date, time]
            LocalDateTime dateTime = parseDateTime(dateAndTime);

            String taskTypePrefix = "E";
            if (taskType.equals("event")) {
                task = new Event(taskName, dateTime);
            } else { // deadline
                task = new Deadline(taskName, dateTime);
                taskTypePrefix = "D";
            }

            message = this.taskList.add(task);
            if (storageIsPresent()) {
                this.storage.append(taskTypePrefix, "<F>", taskName,
                        dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            }
        }
        return message;
    }


    private String[] preProcessCommand(String command) throws DukeException.InvalidCommandException,
            DukeException.InvalidTaskDescriptionException {
        if (command.length() == 0) {
            throw new DukeException.InvalidCommandException(MISSING_DETAILS_MESSAGE);
        }

        String[] commandTokens = getStringTokens(command, " ");
        String taskType = commandTokens[0];
        if (!(taskType.equals("todo") || taskType.equals("event") || taskType.equals("deadline"))) {
            throw new DukeException.InvalidCommandException(INVALID_COMMAND_MESSAGE);
        }

        String taskDetails = command.substring(taskType.length() + 1).trim();
        if (taskDetails.length() == 0) {
            throw new DukeException.InvalidTaskDescriptionException(MISSING_DETAILS_MESSAGE);
        }

        return new String[]{taskType, taskDetails};
    }


    private LocalDateTime parseDateTime(String[] dateAndTime) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(dateAndTime[0].trim(), DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalTime time = LocalTime.parse(dateAndTime[1].trim(), DateTimeFormatter.ofPattern(TIME_FORMAT));
        return LocalDateTime.of(date, time);
    }


    private String[] getStringTokens(String original, String delimiter) {
        return original.split(delimiter);
    }


    private boolean storageIsPresent() {
        return this.storage != null;
    }
}
