package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.ui.Ui;
import duke.tasks.taskType;

/**
 * A ToDoCommand class encapsulates the instructions for Duke to add a ToDo Task
 */
public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * executes the command on the specified tasklist
     *
     * @param taskList tasklist to be operated on
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList) {
        String[] fields = new String[] {description};
        taskList.addTask(taskType.TODO, fields);
        Ui.showAddedTask(taskList);
        Ui.showTaskCount(taskList);
    }

    /**
     * returns the type of command
     *
     * @return todo
     */
    @Override
    public String getType() {
        return "todo";
    }
}

