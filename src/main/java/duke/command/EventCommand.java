package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;


public class EventCommand extends Command {
    public static final String COMMAND = "event";
    private String desc;
    private LocalDateTime at;
    private Event event;

    /**
     * Constructor for Event Command
     *
     * @param desc description of task
     * @param at when task starts
     *
     */
    public EventCommand(String desc, LocalDateTime at) {
        this.desc = desc;
        this.at = at;
    }

    /**
     * Executes Events Command, adds an event task to the list, prints response
     * and stores updated list in file
     *
     * @param taskList current list
     * @param ui current ui to access print responses
     * @param storage current storage
     * @throws IOException when there is file save error
     * @return
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, History history) throws IOException {
        event = new Event(this.desc, this.at);
        taskList.add(event);
        storage.writeToFile(taskList);
        ui.printAdd(event, taskList.getList().size());
        history.addHistory(this);
    }

    /**
     * Executes Events Command, adds an event task to the list, returns response
     * and stores updated list in file
     *
     * @param taskList current list
     * @param rf Response Formatter
     * @param storage current storage
     * @throws IOException when there is file save error
     * @return
     */
    @Override
    public String execute(TaskList taskList, ResponseFormatter rf,
                          Storage storage, History history) throws IOException {
        this.event = new Event(this.desc, this.at);
        taskList.add(event);
        storage.writeToFile(taskList);
        history.addHistory(this);
        return rf.formatAdd(event, taskList.getList().size());
    }

    @Override
    public String undo(TaskList taskList, ResponseFormatter rf, Storage storage) throws IOException {
        ArrayList<Task> currentList = taskList.getList();
        currentList.removeIf(task ->
                task instanceof Event
                        && task.getDescription().equals(this.event.getDescription())
        );
        taskList.updateTaskList(currentList);

        storage.writeToFile(taskList);
        return rf.formatUndo("Event Command");
    }
}
