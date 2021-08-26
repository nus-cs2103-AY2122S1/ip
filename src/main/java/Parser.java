import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static LocalDate parseDate(String str) throws IllegalArgumentException {
        LocalDate date;
        try {
            date = LocalDate.parse(str);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("Date could not be parsed!");
        }
        return date;
    }

    public static Task parseData(String str) {
        String[] splitArray = str.split("\\|");
        switch (splitArray[0]) {
        case "E":
            Task event = new Events(splitArray[2], Parser.parseDate(splitArray[3]));
            if (splitArray[1].equals("1")) { event.setCompleted(); }
            return event;
        //no need for break, as function has terminated at the return statement
        case "D":
            Task deadline = new Deadlines(splitArray[2], Parser.parseDate(splitArray[3]));
            if (splitArray[1].equals("1")) { deadline.setCompleted(); }
            return deadline;
        //no need for break, as function has terminated at the return statement
        //last case will always be "T"
        default:
            Task todo = new Todos(splitArray[2]);
            if (splitArray[1].equals("1")) { todo.setCompleted(); }
            return todo;
        }
    }
}
