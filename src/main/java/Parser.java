import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateValidator dateValidator = new DateValidator(DateTimeFormatter.ISO_LOCAL_DATE);

    public static Task check(String str, String check) throws DukeException {
        int partition = str.indexOf(check);
        if (partition < 0 || partition + check.length() > str.length()) {
            throw new DukeException("☹ OOPS!!! The task is formatted wrongly.");
        }
        String str1 = str.substring(0, partition);
        String str2 = str.substring(partition + check.length());
        System.out.println(str2);
        LocalDate date = null;

        if (dateValidator.isValid(str2)) {
            date = LocalDate.parse(str2);
        }

        switch (check) {
            case "/by ":
                if (date == null) {
                    return new Deadline(str1, str2);
                } else {
                    return new Deadline(str1, date);
                }

            case " (by: ":
                return new Deadline(str1, str2);

            case "/at ":

            case " (at: ":
                return new Event(str1, str2);
                if (date == null) {
                    return new Event(str1, str2);
                } else {
                    return new Event(str1, date);
                }

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static Task read(String string) throws DukeException {
        Task task;
        String taskType = string.substring(0, 3);
        char doneCheck = string.charAt(4);

        switch (taskType) {
            case "[T]":
                String tdLabel = string.substring(7);
                task = new Todo(tdLabel);
                break;

            case "[D]":
                String dlLabel = string.substring(7, string.length()-1);
                task = Parser.check(dlLabel, " (by: ");
                break;

            case "[E]":
                String eLabel = string.substring(7, string.length()-1);
                task = Parser.check(eLabel, " (at: ");
                break;

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (doneCheck == 'X') {
            task.Done();
        }
        return task;

    }
}
