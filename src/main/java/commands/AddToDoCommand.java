package commands;
import tasks.ToDoTask;


public class AddToDoCommand extends AddCommand {
    public static final String KEYWORD = "todo";

    public AddToDoCommand (String userInput) throws DukeException {
        String inputFormat = String.format("\t\"%s [task]\"", KEYWORD);
        String inputData = userInput.substring(KEYWORD.length()).trim();
        // Check whether input contains task
        boolean hasTask = !inputData.isEmpty();
        if (!hasTask) {
            throw new DukeException("OOPS!!! " +
                    "Please ensure your input is in the following format:\n" + inputFormat);
        }
        this.task = new ToDoTask(inputData);
    }
}
