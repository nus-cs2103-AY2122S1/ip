package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.tasks.taskType;
import duke.ui.Ui;


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
    public String execute(TaskList taskList) {
        String[] fields = new String[] {description};
        taskList.addTask(taskType.TODO, fields);

        return Ui.showAddedTask(taskList) + Ui.showTaskCount(taskList);
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDoCommand) {
            return ((ToDoCommand)obj).description.equals(description);
        } else {
            return false;
        }
    }
}

