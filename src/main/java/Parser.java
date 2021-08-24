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

            case "/at ":
                if (date == null) {
                    return new Event(str1, str2);
                } else {
                    return new Event(str1, date);
                }

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
