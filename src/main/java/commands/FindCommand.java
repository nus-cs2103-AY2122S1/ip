package commands;
import tasks.TaskList;

public class FindCommand extends Command {
        public static final String KEYWORD = "find";
        private final String keyTerm;

        public FindCommand(String userInput) {
            String inputFormat = String.format("\t\"%s [keyword/(s)]\"", KEYWORD);
            String inputData = userInput.substring(KEYWORD.length()).trim();
            this.keyTerm = inputData.toLowerCase();
        }

        public String execute(TaskList taskList) {
            // If task list is empty, only a "\n" will be printed
            TaskList foundTasks = taskList.findTasks(keyTerm);
            String output = "Here are the matching tasks in your list:\n";
            output += foundTasks.toString();
            return output;
        }
}
