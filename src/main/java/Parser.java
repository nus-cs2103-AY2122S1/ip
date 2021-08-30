import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    public Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    public Command parseUserInput(String userInput) throws DukeException{
        String[] splitUserInput = userInput.split(" ");
        String commandType = splitUserInput[0];
        String commandDetails = userInput.substring(userInput.indexOf(" ") + 1);
        if (!(commandType.equals("list") || commandType.equals("bye")) &&
                (commandDetails.isBlank() || userInput.indexOf(" ") == -1)) {
            throw new NoTaskDecriptionException(ui);
        }

        switch (commandType) {
        case "list":
            return new ListCommand(taskList, storage, ui);

        case "bye":
            return new ExitCommand(taskList, storage, ui);

        case "deadline":
            return new AddCommand(taskList, storage, ui, parseTaskInput(TaskType.DEADLINE, userInput));

        case "event":
            return new AddCommand(taskList, storage, ui, parseTaskInput(TaskType.EVENT, userInput));

        case "todo":
            return new AddCommand(taskList, storage, ui, parseTaskInput(TaskType.TODO, userInput));

        case "done":
            return new DoneCommand(taskList, storage, ui, Integer.parseInt(splitUserInput[1]));

        case "delete":
            return new DeleteCommand(taskList, storage, ui, Integer.parseInt(splitUserInput[1]));

        default:
            throw new NoSuchCommandException(ui);
        }
    }

    public Task parseTaskInput(TaskType taskType, String userInput) throws DukeException {
        String[] splitUserInput = userInput.split(" ");
        String commandDetails = userInput.substring(userInput.indexOf(" ") + 1);
        if (commandDetails.isBlank() || userInput.indexOf(" ") == -1) {
            throw new NoTaskDecriptionException(ui);
        }

        switch (taskType) {
        case DEADLINE:
            try {
                int byIndex = commandDetails.indexOf("/by") - 1 ;
                if (byIndex <= 0) {
                    throw new NoDateTimeException(ui);
                }
                String deadlineDetails = commandDetails.substring(0, byIndex);
                if (deadlineDetails.isBlank()) {
                    throw new NoTaskDecriptionException(ui);
                }
                try {
                    LocalDateTime by = LocalDateTime.parse(commandDetails.substring(deadlineDetails.indexOf("by") + 3),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    return new Deadline(commandDetails, false, by);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException(ui);
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new NoTaskDecriptionException(ui);
            }


        case EVENT:
            try{
                int atIndex = commandDetails.indexOf("/at") - 1 ;
                if (atIndex <= 0) {
                    throw new NoDateTimeException(ui);
                }
                String eventDetails = commandDetails.substring(0, atIndex);
                if (eventDetails.isBlank()) {
                    throw new NoTaskDecriptionException(ui);
                }
                try {
                    LocalDateTime at = LocalDateTime.parse(commandDetails.substring(eventDetails.indexOf("at") + 3),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    return new Event(commandDetails, false, at);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException(ui);
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new NoTaskDecriptionException(ui);
            }

        default:
            return new ToDo(commandDetails, false);
        }
    }
 }
