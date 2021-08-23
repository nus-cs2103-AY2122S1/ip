import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {
    private final static DateTimeFormatter DT_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mma");

    public enum CommandEnum {
        EXIT("bye"),
        LIST("list"),
        DELETE("delete"),
        COMPLETE("done"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

        public final String label;

        private CommandEnum(String label) {
            this.label = label;
        }

        public static CommandEnum valueOfLabel(String label) {
            for (CommandEnum e : values()) {
                if (e.label.equals(label)) {
                    return e;
                }
            }
            return null;
        }
    }

    public static Command parse(String fullCommand) throws DukeException {
        String[] tokens = fullCommand.strip().split("\\s+", 2);
        String eventType = tokens[0];
        String remainder = tokens.length == 1 ? null : tokens[1];

        switch (CommandEnum.valueOfLabel(eventType)) { // Exit routine
        case EXIT:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case DELETE: {
            int index = Integer.parseInt(remainder) - 1;
            return new DeleteCommand(index);
        }
        case COMPLETE: {
            int index = Integer.parseInt(remainder) - 1;
            return new CompleteCommand(index);
        }
        case TODO: {
            if (remainder == null) {
                throw DukeException.emptyDesc();
            }
            Todo event = new Todo(false, remainder);

            return new AddCommand(event);
        }
        case DEADLINE: {
            if (remainder == null) {
                throw DukeException.emptyDesc();
            }
            String[] deadlineTokens = remainder.split(" /by ", 2);
            if (tokens.length == 1) {
                throw DukeException.emptyTime();
            }
            LocalDateTime deadline = LocalDateTime.parse(tokens[1], DT_INPUT_FORMAT);
            Deadline event = new Deadline(false, tokens[0], deadline);

            return new AddCommand(event);
        }
        case EVENT: {
            if (remainder == null) {
                throw DukeException.emptyDesc();
            }
            String[] eventTokens = remainder.split(" /at ", 2);
            LocalDateTime startTime = LocalDateTime.parse(tokens[1], DT_INPUT_FORMAT);
            Event event = new Event(false, tokens[0], startTime);

            return new AddCommand(event);
        }
        default:
            throw DukeException.invalidCommand();
            //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    public static String arrayToString(ArrayList<Task> list) {
        String answer = "";
        int counter = 1;
        for (Task item : list) {
            answer += String.format("%d: %s\n", counter, item.toString());
            counter++;
        }
        return answer;
    }
}
