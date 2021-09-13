package duke;

import java.time.LocalDate;

/**
 * Represents <code>Parser</code> object to parse user input
 */
public class Parser {

    public static final String INVALID_DATE_ERROR = "Please enter a valid date (yyyy-mm-dd) :(";
    public static final String INVALID_FORMAT_ERROR = "Please enter a valid number :(";

    /**
     * Returns corresponding <code>Command</code> based on input supplied
     * @param input User input
     * @return <code>Command</code> object
     * @throws DukeException when an exception is caught
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        StringBuilder body = new StringBuilder();
        String date = inputArr[inputArr.length - 1];
        boolean hasDate = false;
        for (int i = 1; i < inputArr.length; i++) {
            boolean isByOrAt = inputArr[i].equals("/by") || inputArr[i].equals("/at");
            if (isByOrAt) {
                hasDate = true;
                break;
            }
            body.append(" ").append(inputArr[i]);
        }
        String lowerCaseInput = inputArr[0].toLowerCase();
        switch (lowerCaseInput) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            return getDoneCommand(inputArr);
        case "todo":
            return getAddTodoCommand(body.toString());
        case "deadline":
            return getAddDeadlineCommand(body.toString(), date, hasDate);
        case "event":
            return getAddEventCommand(body.toString(), date, hasDate);
        case "delete":
            return getDeleteCommand(inputArr);
        case "get":
            return getGetCommand(inputArr);
        case "find":
            return getFindCommand(inputArr);
        case "help":
            return new HelpCommand();
        default:
            throw new DukeException("I'm sorry but I don't understand what that means :(");
        }
    }

    /**
     * Returns <code>FindCommand</code> if input is valid
     * @param inputArr <code>String</code> array containing inputs
     * @return <code>FindCommand</code> with the corresponding input
     * @throws DukeException when input is not valid
     */
    private static FindCommand getFindCommand(String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Please enter a description!");
        }
        String matchString = inputArr[1];
        return new FindCommand(matchString);
    }

    /**
     * Returns <code>GetCommand</code> if input is valid
     * @param inputArr <code>String</code> array containing inputs
     * @return <code>GetCommand</code> with the corresponding input
     * @throws DukeException when input is not valid
     */
    private static GetCommand getGetCommand(String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Please enter a date!");
        }
        String[] dateArr = inputArr[1].split("-");
        if (dateArr.length < 3) {
            throw new DukeException(INVALID_DATE_ERROR);
        }
        int year = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int day = Integer.parseInt(dateArr[2]);
        if (month > 12 || day > 31) {
            throw new DukeException(INVALID_DATE_ERROR);
        }
        LocalDate newDate = LocalDate.of(year, month, day);
        return new GetCommand(newDate);
    }

    /**
     * Returns <code>DeleteCommand</code> if input is valid
     * @param inputArr <code>String</code> array containing inputs
     * @return <code>DeleteCommand</code> with the corresponding input
     * @throws DukeException when input is not valid
     */
    private static DeleteCommand getDeleteCommand(String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Please enter a number!");
        }
        int index;
        try {
            index = Integer.parseInt(inputArr[1]);
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_FORMAT_ERROR);
        }
        return new DeleteCommand(index);
    }

    /**
     * Returns <code>AddEventCommand</code> if input is valid
     * @param body the message body of <code>Event</code>
     * @param date the date of <code>Event</code>
     * @param hasDate the presence of date
     * @return <code>AddEventCommand</code> with the corresponding input
     * @throws DukeException when input is not valid
     */
    private static AddEventCommand getAddEventCommand(String body, String date, boolean hasDate) throws DukeException {
        if (!hasDate) {
            throw new DukeException("Date of event cannot be empty :(");
        }
        if (date.length() != 10) {
            throw new DukeException(INVALID_DATE_ERROR);
        }
        String[] dateArr = date.split("-");
        int year = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int day = Integer.parseInt(dateArr[2]);
        if (month > 12 || day > 31) {
            throw new DukeException(INVALID_DATE_ERROR);
        }
        LocalDate newDate = LocalDate.of(year, month, day);
        Event event = new Event(body, newDate);
        return new AddEventCommand(event);
    }

    /**
     * Returns <code>AddDeadlineCommand</code> if input is valid
     * @param body the message body of <code>Deadline</code>
     * @param date the date of <code>Deadline</code>
     * @param hasDate the presence of date
     * @return <code>AddDeadlineCommand</code> with the corresponding input
     * @throws DukeException when input is not valid
     */
    private static AddDeadlineCommand getAddDeadlineCommand(String body, String date, boolean hasDate)
            throws DukeException {
        if (!hasDate) {
            throw new DukeException("Deadline of deadline cannot be empty :(");
        }
        if (date.length() != 10) {
            throw new DukeException(INVALID_DATE_ERROR);
        }
        String[] dateArr = date.split("-");
        int year = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int day = Integer.parseInt(dateArr[2]);
        if (month > 12 || day > 31) {
            throw new DukeException(INVALID_DATE_ERROR);
        }
        LocalDate newDate = LocalDate.of(year, month, day);
        Deadline deadline = new Deadline(body, newDate);
        return new AddDeadlineCommand(deadline);
    }

    /**
     * Returns <code>AddTodoCommand</code> if input is valid
     * @param body the message body of <code>Todo</code>
     * @return <code>AddTodoCommand</code> with the corresponding input
     * @throws DukeException when input is not valid
     */
    private static AddTodoCommand getAddTodoCommand(String body) throws DukeException {
        if (body.equals("")) {
            throw new DukeException("Description of todo cannot be empty :(");
        }
        Todo todo = new Todo(body);
        return new AddTodoCommand(todo);
    }

    /**
     * Returns <code>DoneCommand</code> if input is valid
     * @param inputArr <code>String</code> array containing inputs
     * @return <code>DoneCommand</code> with the corresponding input
     * @throws DukeException when input is not valid
     */
    private static DoneCommand getDoneCommand(String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Please enter a number!");
        }
        int index;
        try {
            index = Integer.parseInt(inputArr[1]);
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_FORMAT_ERROR);
        }
        return new DoneCommand(index);
    }
}
