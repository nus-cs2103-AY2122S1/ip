import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class Parser {
    public enum CommandType {
        EXIT,
        LIST,
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        UNKNOWN
    }

    public static CommandType decipherInput(String input) {
        if (input.equals("bye")) {
            return CommandType.EXIT;

        } else if (input.equals("list")) {
            return CommandType.LIST;

        } else if (input.startsWith("done")) {
            return CommandType.DONE;

        } else if (input.startsWith("delete")) {
            return CommandType.DELETE;

        } else if (input.startsWith("todo")) {
            return CommandType.TODO;

        } else if (input.startsWith("deadline")) {
            return CommandType.DEADLINE;

        } else if (input.startsWith("event")) {
            return CommandType.EVENT;

        } else {
            return CommandType.UNKNOWN;
        }
    }

    /**
     * Takes in the string representation of a task and returns the task represented by it.
     * @param taskString The string representation of a task.
     * @return The task represented by the string.
     */
    public static Task processTaskString(String taskString) {
        char type = taskString.charAt(1);
        boolean isDone = taskString.charAt(0) == 'X';
        String[] split = taskString.split("/");
        switch (type) {
            case 'T':
                return new ToDo(split[1], isDone);

            case 'D':
                String deadlineName = split[1];
                String deadlineDateAndTime = split[2];
                return new Deadline(deadlineName, LocalDateTime.parse(deadlineDateAndTime), isDone);

            default:
                String eventName = split[1];
                String eventDateAndTime = split[2];
                String eventEndTime = split[3];
                return new Event(eventName, LocalDateTime.parse(eventDateAndTime),
                        LocalTime.parse(eventEndTime), isDone);
        }
    }

    /**
     * Takes in a string representation of a date and time and parses it into a LocalDateTime object.
     * @param dateAndTime The given date and time.
     * @throws DukeException If the input date and time are incorrectly configured.
     * @return A LocalDateTime object.
     */
    public static LocalDateTime formatDateTime(String dateAndTime) throws DukeException {
        try {
            String[] split = dateAndTime.split(", ");
            String toFormat = split[0];
            if (split.length > 1) {
                toFormat += "T" + split[1];
            } else {
                toFormat += "T" + "00:00";
            }
            return LocalDateTime.parse(toFormat);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Please use the following format for date and time:\n"
                    + "yyyy-MM-dd, HH:mm");
        }
    }
}
