package parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parsed input that can easily be processed into a task.
 */
public class ParsedInput {
    public CommandType commandType;
    public String taskDescription = "";
    public LocalDate deadline;
    public String eventPeriod = "";
    public int taskIndex = -1;

    InputValidator inputValidator = InputValidator.getInstance();

    /**
     * Generates parsed input for task.
     * @param input
     */
    public ParsedInput(String input) {
        String[] splitInput = input.split(" ");
        switch (splitInput[0]) {
            case "todo":
                if(!inputValidator.checkTodo(splitInput)) {
                    this.commandType = CommandType.ERROR;
                    break;
                }
                this.commandType = CommandType.TODO;
                this.taskDescription = joinStrings(splitInput, 1, splitInput.length - 1);
                break;
            case "deadline":
                this.commandType = CommandType.DEADLINE;
                int byIndex = findFirstIndexOf("/by", splitInput);
                this.taskDescription = joinStrings(splitInput, 1, byIndex - 1);
                String deadlineString = joinStrings(splitInput, byIndex + 1, splitInput.length - 1);
                try {
                    this.deadline = LocalDate.parse(deadlineString);
                } catch (DateTimeParseException e) {
                    this.deadline = LocalDate.now().plusWeeks(1);
                }
                break;
            case "event":
                this.commandType = CommandType.EVENT;
                int atIndex = findFirstIndexOf("/at", splitInput);
                this.taskDescription = joinStrings(splitInput, 1, atIndex - 1);
                this.eventPeriod = joinStrings(splitInput, atIndex + 1, splitInput.length - 1);
                break;
            case "list":
                this.commandType = CommandType.LIST;
                break;
            case "done":
                this.commandType = CommandType.DONE;
                this.taskIndex = Integer.parseInt(splitInput[1]);
                break;
            case "bye":
                this.commandType = CommandType.BYE;
                break;
            case "delete":
                this.commandType = CommandType.DELETE;
                this.taskIndex = Integer.parseInt(splitInput[1]);
                break;
            case "find":
                this.commandType = CommandType.FIND;
                this.taskDescription = joinStrings(splitInput, 1, splitInput.length - 1);
                break;
            default:
                this.commandType = CommandType.ERROR;
        }
    }

    public static String joinStrings(String[] strArr, int start, int end) {
        String result = "";
        for(int i = start; i < strArr.length && i <= end; i++) {
            result += strArr[i] + " ";
        }
        return result.stripTrailing();
    }

    public static int findFirstIndexOf(String option, String[] splitInput) {
        for(int i=0; i<splitInput.length; i++) {
            if (splitInput[i].equals(option)) {
                return i;
            }
        }
        return -1;
    }
}
