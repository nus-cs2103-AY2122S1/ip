package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.task.Todo;
import duke.Ui;

import java.io.IOException;

public class TodoCommand extends Command {
    public static final String COMMAND = "todo";
    private String desc;

    /**
     * Constructor for Todo Command
     *
     * @param desc description of todo task
     */
    public TodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes Todo Command, adds a todo task to the list, prints response
     * and stores updated list in file
     *
     * @param taskList current list
     * @param ui current ui to access print responses
     * @param storage current storage
     * @throws IOException when there is file save error
     *
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        if (this.desc.equals("")) {
            throw new IllegalArgumentException();
        }
        Todo newTodo = new Todo(this.desc);
        taskList.add(newTodo);
        storage.writeToFile(taskList);
        ui.printAdd(newTodo, taskList.getList().size());
    }
}
