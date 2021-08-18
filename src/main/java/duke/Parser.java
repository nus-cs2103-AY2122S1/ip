package duke;
import duke.command.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class Parser {
    private static void checkDescription(String in) throws DukeException {
        if (in.equals("")) {
            throw new DukeException("Command has empty description");
        }
    }

    public static Command parse(String in) throws DukeException,IndexOutOfBoundsException,
            NumberFormatException, DateTimeParseException {
        if (in.equals("list")) {
            return new ListCommand();
        } else if (in.startsWith("done")) {
            String[] temp = in.split(" ");
            return new MarkDoneCommand(Integer.parseInt(temp[1]) - 1);
        } else if (in.startsWith("todo") || in.startsWith("deadline") || in.startsWith("event")) {
            String type;
            if (in.startsWith("todo")) {
                in = in.replaceFirst("todo", "");
                type = "todo";
                checkDescription(in);
                return new AddCommand(type, in);
            } else {
                if (in.startsWith("deadline")) {
                    in = in.replaceFirst("deadline", "");
                    type = "deadline";
                } else {
                    in = in.replaceFirst("event", "");
                    type = "event";
                }
                String[] arr = in.split("/", 2);
                LocalDate date = LocalDate.parse(arr[1].substring(3));
                String label = arr[0];
                checkDescription(label);
                return new AddCommand(type, label, date);
            }
        } else if (in.startsWith("delete")) {
            String[] temp = in.split(" ");
            return new DeleteCommand(Integer.parseInt(temp[1]) - 1);
        } else if (in.equals("bye")) {
            return new DoneCommand();
        } else {
            return new InvalidCommand();
        }
    }
}
