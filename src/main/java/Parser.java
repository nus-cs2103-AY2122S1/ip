import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class Parser {
    private static void checkDescription(String in) throws DukeException {
        if (in.equals("")) {
            throw new DukeException("Command has empty description");
        }
    }

    public static Command parse(String in) throws DukeException {
        if (in.equals("list")) {
            return new ListCommand();
        } else if (in.startsWith("done")) {
            try {
                String[] temp = in.split(" ");
                return new MarkDoneCommand(Integer.parseInt(temp[1]) - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Sorry! Your done command has an invalid index choice");
            } catch (NumberFormatException e) {
                System.out.println("Sorry! I can't understand the index for your done command");
            }
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

                try {
                    String[] arr = in.split("/", 2);
                    LocalDate date = LocalDate.parse(arr[1].substring(3));
                    String label = arr[0];
                    checkDescription(label);
                    return new AddCommand(type, label, date);
                } catch (IndexOutOfBoundsException e) {
                    //if '/' doesn't split, command was wrong
                    throw new DukeException("Command had an invalid format");
                } catch (DateTimeParseException e) {
                    throw new DukeException("Your date had an invalid format");
                }
            }
        } else if (in.startsWith("delete")) {
            try {
                String[] temp = in.split(" ");
                return new DeleteCommand(Integer.parseInt(temp[1]) - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Sorry! Your delete command has an invalid index choice");
            } catch (NumberFormatException e) {
                System.out.println("Sorry! I can't understand the index for your delete command");
            }
        } else if (in.equals("bye")) {
            return new DoneCommand();
        } else {
            return new InvalidCommand();
        }
        return new InvalidCommand();
    }
}
