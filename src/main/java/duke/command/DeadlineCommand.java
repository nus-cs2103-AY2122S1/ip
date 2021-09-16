package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;


public class DeadlineCommand extends Command {
    public static final String COMMAND = "deadline";
    private String desc;
    private LocalDateTime by;
    private Deadline deadline;

    /**
     * Constructor of Deadline Command
     *
     * @param desc the description of the task
     * @param by due date
     *
     */
    public DeadlineCommand(String desc, LocalDateTime by) {
        this.desc = desc;
        this.by = by;
    }

    /**
     * Executes Deadline Command, adds a deadline task to the list, prints response
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
        this.deadline = new Deadline(this.desc, this.by);
        taskList.add(deadline);
        storage.writeToFile(taskList);
        history.addHistory(this);
        ui.printAdd(deadline, taskList.getList().size());
    }

    /**
     * Executes Deadline Command, adds a deadline task to the list, returns response
     * and stores updated list in file
     *
     * @param taskList current list
     * @param rf Response Formatter
     * @param storage current storage
     * @throws IOException when there is file save error
     *
     * @return
     */
    @Override
    public String execute(TaskList taskList, ResponseFormatter rf,
                          Storage storage, History history) throws IOException {
        this.deadline = new Deadline(this.desc, this.by);
        taskList.add(deadline);
        storage.writeToFile(taskList);
        history.addHistory(this);
        return rf.formatAdd(deadline, taskList.getList().size());
    }

    @Override
    public String undo(TaskList taskList, ResponseFormatter rf, Storage storage) throws IOException {
        ArrayList<Task> currentList = taskList.getList();
        currentList.removeIf(task ->
                task instanceof Deadline
                        && task.getDescription().equals(this.deadline.getDescription())
        );
        taskList.updateTaskList(currentList);

        storage.writeToFile(taskList);
        return rf.formatUndo("Deadline Command");
    }
}
