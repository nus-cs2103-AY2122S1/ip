import java.beans.IntrospectionException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Parser {

    public static Command parse(String input) throws DukeException{
        Instruction instruction = Instruction.valueOfLabel(input.split(" ")[0]);
        switch (instruction) {
        case LIST:
            return new ListCommand();
        case DONE:
            int doneIndex = extractIndex(input, Instruction.DONE);
            return new DoneCommand(doneIndex);
        case HELP:
            return new HelpCommand();
        case TODO:
            String toDoInfo = extractInfo(input, Instruction.TODO);
            ToDo toDo = new ToDo(toDoInfo);
            return new AddCommand(toDo);
        case DEADLINE:
            String[] deadlineInfo = extractDeadline(extractInfo(input, Instruction.DEADLINE));
            Deadline deadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
            return new AddCommand(deadline);
        case EVENT:
            String[] eventInfo = extractEvent(extractInfo(input, Instruction.EVENT));
            Event event = new Event(eventInfo[0], eventInfo[1]);
            return new AddCommand(event);
        case DELETE:
            int deleteIndex = extractIndex(input, Instruction.DELETE);
            return new DeleteCommand(deleteIndex);
        case FILTER:
            String filterDate = extractInfo(input, Instruction.FILTER);
            return new FilterCommand(parseDate(filterDate));
        case BYE:
            return new ExitCommand();
        default:
            throw new DukeException("Invalid command @_@ Try typing 'help' to see my list of commands!");
        }
    }

    /**
     * Extract out the information given in user input by separating out command.
     *
     * @param input   User raw input.
     * @param instruction The specific command given by user.
     * @return String containing information that we need.
     * @throws DukeException Prevent empty descriptions.
     */
    private static String extractInfo(String input, Instruction instruction) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length < 2) {
            throw new DukeException(String.format("Error: OOPS!!! The description of %s cannot be empty.",
                    instruction.label));
        }
        return info[1];
    }

    /**
     * Extract out the index given in user input by separating out command.
     *
     * @param input   User raw input.
     * @param instruction The specific command given by user.
     * @return int of the task user wants to select.
     * @throws DukeException Prevent empty indexes.
     */
    public static int extractIndex(String input, Instruction instruction) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length < 2 || info[1].equals("")) {
            throw new DukeException(String.format("Error: OOPS!!! The index argument for %s cannot be empty.",
                    instruction.label));
        }
        return Integer.parseInt(info[1]) - 1;
    }

    /**
     * Format the raw information by splitting it to get the description and due date of deadline.
     *
     * @param info User input containing relevant information.
     * @return String array with information of deadline split to description and date.
     * @throws DukeException For missing arguments.
     */
    public static String[] extractDeadline(String info) throws DukeException {
        if (!info.contains("/by")) {
            throw new DukeException("Error: '/by' argument is missing!");
        }
        String[] description = info.split(" /by ", 2);

        if (description.length < 2 || description[1].equals("")) {
            throw new DukeException("Error: OOPS!!! Argument after '/by' is empty!");
        }
        return description;
    }

    /**
     * Format the raw information by splitting it to get the description and timeframe of event.
     *
     * @param info User input containing relevant information.
     * @return String array with information of event split to description and event timeframe.
     * @throws DukeException For missing arguments.
     */
    public static String[] extractEvent(String info) throws DukeException {
        if (!info.contains("/at")) {
            throw new DukeException("Error: '/at' argument is missing!");
        }
        String[] description = info.split(" /at ", 2);
        if (description.length < 2 || description[1].equals("")) {
            throw new DukeException("Error: OOPS!!! Argument after '/at' is empty!");
        }
        return description;
    }

    public static LocalDate parseDate(String date) throws DukeException {
        try {
            // Reuse regex from https://www.javacodeexamples.com/java-regular-expression-validate-date-example-regex/1504
            String[] dateSplit;
            if (date.matches("\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|[3][01])")) {
                // for yyyy-mm-dd
                return LocalDate.parse(date);
            } else if (date.matches("(0[1-9]|[12][0-9]|[3][01])-(0[1-9]|1[012])-\\d{4}")) {
                // for dd-mm-yyyy
                dateSplit = date.split("-", 3);
                return LocalDate.parse(String.format("%s-%s-%s", dateSplit[2], dateSplit[1], dateSplit[0]));
            } else if (date.matches("(0[1-9]|[12][0-9]|[3][01])/(0[1-9]|1[012])/\\d{4}")) {
                // for dd/mm/yyyy
                dateSplit = date.split("/", 3);
                return LocalDate.parse(String.format("%s-%s-%s", dateSplit[2], dateSplit[1], dateSplit[0]));
            } else {
                throw new DateTimeException("Invalid Date");
            }
        } catch (DateTimeException e) {
            throw new DukeException("Invalid Date @_@\nDate formats: dd/mm/yyyy, dd-mm-yyyy, yyyy-mm-dd");
        }
    }

    public static LocalTime parseTime(String time) throws DukeException {
        try {
            // Reuse regex from https://www.geeksforgeeks.org/how-to-validate-time-in-24-hour-format-using-regular-expression/
            if (time.length() == 4) {
                String t = String.format("%s:%s", time.substring(0,2), time.substring(2,4));
                if (t.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
                    return LocalTime.parse(t);
                } else {
                    throw new DateTimeException("Invalid Time");
                }
            } else if (time.length() == 5 && time.split(":",2).length == 2) {
                return LocalTime.parse(time);
            } else {
                throw new DateTimeException("Invalid Time");
            }
        } catch (DateTimeException e) {
            throw new DukeException("Invalid Time @_@\nTime format: HHmm HH:mm");
        }
    }
}
