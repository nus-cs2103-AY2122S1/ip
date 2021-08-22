import java.util.Arrays;

abstract class CommandHandler {
    protected TaskList taskList;

    protected CommandHandler(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * @param input - the input entered by the user
     * @return response
     */
    abstract String handle(String input) throws BlueException;

    protected static String[] getArguments(String input) {
        if (input.length() > 0) {
            String[] split = input.split(" ");
            if (split.length >= 2)
                return Arrays.copyOfRange(split, 1, split.length);
        }
        return new String[]{};
    }
}
