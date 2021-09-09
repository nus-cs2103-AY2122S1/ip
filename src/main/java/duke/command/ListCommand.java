package duke.command;

import duke.taskList.TaskList;

public class ListCommand extends Command {
    private boolean isExit;

    public ListCommand(TaskList tasks, String input) {
        super(tasks, input);
    }


    @Override
    public boolean isExitCommand() {
        return isExit;
    }

    public String list() {
        int count = 1;
        String str = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.getSize(); i++) {
            str += "\n" + count + "." + tasks.getTask(i);
            count += 1;
        }
        return str;
    }

}