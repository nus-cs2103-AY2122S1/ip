public class ParsedInput {
    CommandType commandType;
    String taskDescription = "";
    String deadline = "";
    String eventPeriod = "";
    int taskIndex = -1;

    InputValidator inputValidator = InputValidator.getInstance();

    public ParsedInput(String input) throws JadenInputException {
        String[] splitInput = input.split(" ");
        switch (splitInput[0]) {
            case "todo":
                if(!inputValidator.checkTodo(splitInput)) {
                    throw JadenInputException.invalidTodo();
                }
                this.commandType = CommandType.TODO;
                this.taskDescription = joinStrings(splitInput, 1, splitInput.length - 1);
                break;
            case "deadline":
                this.commandType = CommandType.DEADLINE;
                int byIndex = findFirstIndexOf("/by", splitInput);
                this.taskDescription = joinStrings(splitInput, 1, byIndex - 1);
                this.deadline = joinStrings(splitInput, byIndex + 1, splitInput.length - 1);
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
            default:
                throw JadenInputException.unrecognized();
        }
    }

    private static String joinStrings(String[] strArr, int start, int end) {
        String result = "";
        for(int i = start; i < strArr.length && i <= end; i++) {
            result += strArr[i] + " ";
        }
        return result.stripTrailing();
    }

    private static int findFirstIndexOf(String option, String[] splitInput) {
        for(int i=0; i<splitInput.length; i++) {
            if (splitInput[i].equals(option)) {
                return i;
            }
        }
        return -1;
    }
}
