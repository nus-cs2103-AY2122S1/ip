import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

public enum TaskType {
    TODO,
    EVENT,
    DEADLINE;

    /**
     * Based on the type provided, return the corresponding class
     *
     * @param input Groups of information needed for the task
     * @param type Type of task to use
     * @return A Task that matches that of its TaskType
     */
    public static Task getTask(Matcher input, TaskType type) {
        try {
            switch(type) {
                case TODO:
                    return new TaskTodo(input.group(1));
                case DEADLINE:
                    // Description, date, time
                    return new TaskDeadline(input.group(1), getDate(input.group(2)), input.group(3));
                case EVENT:
                    // Description, date, time
                    return new TaskEvent(input.group(1), getDate(input.group(2)), input.group(3));
                default: return null;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Please enter a valid date! :(");
            return null;
        }
    }

    private static LocalDate getDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(date, formatter);
    }


}
