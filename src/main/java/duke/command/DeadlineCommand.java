package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Task;


public class DeadlineCommand extends Command {
    public static final String COMMAND = "deadline";
    private static final String COMMAND_TYPE= "Deadline Command";
    private String desc;
    private LocalDateTime by;
    private Deadline deadline;

    /**
     * Constructor of Deadline Command
     *
     * @param desc Description of the task
     * @param by Due date
     *
     */
    public DeadlineCommand(String desc, LocalDateTime by) {
        this.desc = desc;
        this.by = by;
    }

    /**
     * Executes Deadline Command, adds a deadline task to the list, returns response
     * and stores updated list in file.
     *
     * @param taskList Current list
     * @param rf Response Formatter
     * @param storage Current storage
     * @param history List of previous commands
     * @throws IOException When there is file save error
     *
     * @return Deadline created message formatted.
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
        return rf.formatUndo(COMMAND_TYPE);
    }
}
