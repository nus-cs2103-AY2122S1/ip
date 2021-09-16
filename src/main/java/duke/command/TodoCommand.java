package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND = "todo";
    private String desc;
    private Todo todo;

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
     * @return
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, History history) throws IOException {
        if (this.desc.equals("")) {
            throw new IllegalArgumentException();
        }
        todo = new Todo(this.desc);
        taskList.add(todo);
        storage.writeToFile(taskList);
        ui.printAdd(todo, taskList.getList().size());
        history.addHistory(this);
    }

    /**
     * Executes Todo Command, adds a todo task to the list, returns response
     * and stores updated list in file
     *
     * @param taskList current list
     * @param rf Response Formatter
     * @param storage current storage
     * @throws IOException when there is file save error
     *
     * @return formatted response
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
        return rf.formatUndo("Todo Command");
    }
}
