package winston;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.time.LocalDate;


public class Parser {
    private final TaskList taskList;
    
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }
    
    public Command parse(String str) {
        String[] splitBySpace = str.split(" ");
        String cmd = splitBySpace[0];
        if (cmd.equals("bye")) {
            return new TerminateCommand(this.taskList);
        } else if (cmd.equals("list")) {
            return new ShowListCommand(this.taskList);
        } else if (cmd.equals("done")) {
            try {
                return new DoneCommand(this.taskList, Integer.parseInt(splitBySpace[1]));
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                return new InvalidCommand(taskList);
            }
        } else if (cmd.equals("delete")) {
            try {
                return new DeleteCommand(taskList, Integer.parseInt(splitBySpace[1]));
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                return new InvalidCommand(taskList);
            }
        } else if (cmd.equals("todo")) {
            try {
                return new AddTodoCommand(this.taskList, str.substring(5));
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                return new InvalidCommand(taskList);
            }
        } else if (cmd.equals("deadline")) {
            try {
                int endIndex = str.indexOf("/by ");
                String date = str.substring(endIndex + 4);
                String task = str.substring(str.indexOf(" ") + 1, endIndex);
                LocalDate.parse(date);
                return new AddDeadlineCommand(this.taskList, task, date);
            } catch (DateTimeParseException e) {
                Ui.invalidDateFormat();
                return new InvalidCommand(taskList);
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                return new InvalidCommand(taskList);
            }

        } else if (cmd.equals("event")) {
            try {
                int endIndex = str.indexOf("/at ");
                String date = str.substring(endIndex + 4);
                String task = str.substring(str.indexOf(" ") + 1, endIndex);
                LocalDate.parse(date);
                return new AddEventCommand(this.taskList, task, date);
            } catch (DateTimeParseException e) {
                Ui.invalidDateFormat();
                return new InvalidCommand(taskList);
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                return new InvalidCommand(taskList);
            }
        } else {
            return new InvalidCommand(taskList);
        }
    }
}
