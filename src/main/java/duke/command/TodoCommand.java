package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND = "todo";
    private static final String COMMAND_TYPE = "Todo Command";

    private String desc;
    private Todo todo;

    /**
     * Constructor for Todo Command
     *
     * @param desc Description of todo task
     */
    public TodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes Todo Command, adds a todo task to the list, returns response
     * and stores updated list in file
     *
     * @param taskList Current list
     * @param rf Response Formatter
     * @param storage Current storage
     * @param history List of previous commands
     * @throws IOException When there is file save error
     *
     * @return Todo Created message formatted
     */
    @Override
    public String execute(TaskList taskList, ResponseFormatter rf,
                          Storage storage, History history) throws IOException {
        if (this.desc.equals("")) {
            throw new IllegalArgumentException();
        }
        todo = new Todo(this.desc);
        taskList.add(todo);
        storage.writeToFile(taskList);
        history.addHistory(this);
        return rf.formatAdd(todo, taskList.getList().size());
    }

    @Override
    public String undo(TaskList taskList, ResponseFormatter rf, Storage storage) throws IOException {
        ArrayList<Task> currentList = taskList.getList();
        currentList.removeIf(task ->
                task instanceof Todo
                        && task.getDescription().equals(this.todo.getDescription())
        );
        taskList.updateTaskList(currentList);

        storage.writeToFile(taskList);
        return rf.formatUndo(COMMAND_TYPE);
    }
}
