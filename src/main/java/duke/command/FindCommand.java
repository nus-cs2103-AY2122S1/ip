package duke.command;

import duke.parser.Parser;
import duke.taskList.TaskList;

public class FindCommand extends Command {
    private boolean isExit;

    public FindCommand(TaskList tasks, String input) {
        super(tasks, input);
    }


    @Override
    public boolean isExitCommand() {
        return isExit;
    }

    public String find() {
        Parser parser = new Parser(input);
        String keyword = parser.getKeyword();
        String result = "Here are the matching tasks in your list:";
        int count = 1;
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).contains(keyword)) {
                result += "\n" + count + "." + tasks.getTask(i);
                count += 1;
            }
        }
        return result;
    }
}