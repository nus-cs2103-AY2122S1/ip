package winston;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

/**
 * A class that manages user commands.
 */
public class Parser {
    private final TaskList taskList;

    /**
     * Constructor for class Parser.
     * 
     * @param taskList the TaskList that will be modified based on commands.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Method that parses the given user input.
     * 
     * @param str user input.
     * @return A command depending on the user input.
     */
    public Command parse(String str) {
        String[] splitBySpace = str.split(" ");
        String cmd = splitBySpace[0].toLowerCase();
        final String invalidDateMessage = "Invalid date format. Please give a valid date format. E.g 2021-12-12";
        if (cmd.equals("bye")) {
            return new TerminateCommand(this.taskList);
        } else if (cmd.equals("list")) {
            return new ShowListCommand(this.taskList);
        } else if (cmd.equals("done")) {
            try {
                int index = Integer.parseInt(splitBySpace[1]);
                if (index <= taskList.size() && index > 0) {
                    return new DoneCommand(this.taskList, index);
                } else {
                    return new InvalidCommand(taskList);
                }
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                return new InvalidCommand(taskList);
            }
        } else if (cmd.equals("delete")) {
            try {
                int index = Integer.parseInt(splitBySpace[1]);
                if (index <= taskList.size() && index > 0) {
                    return new DeleteCommand(this.taskList, index);
                } else {
                    return new InvalidCommand(taskList);
                }
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
                return new InvalidCommand(taskList, invalidDateMessage);
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
                return new InvalidCommand(taskList, invalidDateMessage);
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                return new InvalidCommand(taskList);
            }
        } else if (cmd.equals("findstring")) {
            try {
                int index = str.indexOf(" ");
                String keyword = str.substring(index + 1);
                return new FindCommand(taskList, keyword);
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                return new InvalidCommand(taskList);
            }
        } else if (cmd.equals("update")) {
            return new UpdateCommand(taskList, splitBySpace, str);
        } else {
            return new InvalidCommand(taskList);
        }
    }
}
