package duke.classes.commands;

import duke.classes.TaskList;
import duke.classes.tasks.ToDo;

public class TodoCommand extends Command {
    private String str;
    private TaskList taskList;

    /**
     * Class Constructor
     *
     * @param taskList The tasklist to be modified
     * @param str The User input to process
     */
    public TodoCommand(TaskList taskList, String str) {
        this.taskList = taskList;
        this.str = str;
    }

    /**
     * Creates new to do Task
     *
     * @return String to be output by Duke
     */
    public String execute() {
        str = str.toLowerCase();
        String desc = str.replaceFirst("todo ", "");
        desc.trim();
        ToDo newTask = new ToDo(desc);
        taskList.add(newTask);
        return "Got it. I've added this task:\n\t" + newTask
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }
}
