package duke.command;

import duke.taskList.TaskList;

/**
 * Represents a command class that list out all tasks.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class ListCommand extends Command {

    /**
     * A constructor for ListCommand.
     *
     * @param tasks A list of current Tasks.
     * @param input User input.
     */
    public ListCommand(TaskList tasks, String input) {
        super(tasks, input);
    }

    /**
     * List out all tasks.
     *
     * @return String representation of the list of tasks.
     */
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