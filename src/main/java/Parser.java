import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        try {
            if (fullCommand.equals("bye")) {
                return new ExitCommand();
            } else if (fullCommand.equals("list")) {
                return new ListCommand();
            } else if (fullCommand.startsWith("done")) {
                return detectDone(fullCommand);
            } else if (fullCommand.startsWith("todo")) {
                return detectTodo(fullCommand);
            } else if (fullCommand.startsWith("deadline")) {
                return detectDeadline(fullCommand);
            } else if (fullCommand.startsWith("event")) {
                return detectEvent(fullCommand);
            } else if (fullCommand.startsWith("delete")) {
                return detectDelete(fullCommand);
            } else {
                throw new DukeUnknownException();
            }
        } catch (DukeUnknownException e) {
            System.out.println(e.getMessage());
        } catch (DukeDoneException e) {
            System.out.println(e.getMessage());
        } catch (DukeTodoException e) {
            System.out.println(e.getMessage());
        } catch (DukeDeadlineException e) {
            System.out.println(e.getMessage());
        } catch (DukeEventException e) {
            System.out.println(e.getMessage());
        } catch (DukeDeleteException e) {
            System.out.println(e.getMessage());
        }
        return null; // unreachable statement?
    }

    public static DoneCommand detectDone(String str) throws DukeDoneException {
        try {
            int doneTaskIndex = Integer.parseInt(str.substring(5)) - 1;
            return new DoneCommand(doneTaskIndex);
        } catch (Exception e) {
            throw new DukeDoneException();
        }
    }

    public static AddTodoCommand detectTodo(String str) throws DukeTodoException {
        if (str.length() > 5) {
            String desc = str.replaceFirst("todo ", "");
            return new AddTodoCommand(desc);
        } else {
            throw new DukeTodoException();
        }
    }

    public static AddDeadlineCommand detectDeadline(String str) throws DukeDeadlineException {
        try {
            String desc = str.split(" /")[0]
                    .replaceFirst("deadline ", "");
            String by = str.split(" /by ")[1];
            return new AddDeadlineCommand(desc, by);
        } catch (Exception e) {
            throw new DukeDeadlineException();
        }
    }

    public static AddEventCommand detectEvent(String str) throws DukeEventException {
        try {
            String desc = str.split(" /")[0]
                    .replaceFirst("event ", "");
            String at = str.split(" /at ")[1];
            return new AddEventCommand(desc, at);
        } catch (Exception e) {
            throw new DukeEventException();
        }
    }

    public static DeleteCommand detectDelete(String str) throws DukeDeleteException {
        try {
            int deleteTaskIndex = Integer.parseInt(str.substring(7)) - 1;
            return new DeleteCommand(deleteTaskIndex);
        } catch (Exception e) {
            throw new DukeDeleteException();
        }
    }
}
