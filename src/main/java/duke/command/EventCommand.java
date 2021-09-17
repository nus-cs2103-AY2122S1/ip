package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;
import duke.task.Event;
import duke.task.Task;


public class EventCommand extends Command {
    public static final String COMMAND = "event";
    private static final String COMMAND_TYPE = "Event Command";
    private String desc;
    private LocalDateTime at;
    private Event event;

    /**
     * Constructor for Event Command
     *
     * @param desc Description of task
     * @param at Date when task starts
     *
     */
    public EventCommand(String desc, LocalDateTime at) {
        this.desc = desc;
        this.at = at;
    }

    /**
     * Executes Events Command, adds an event task to the list, returns response
     * and stores updated list in file.
     *
     * @param taskList Current list
     * @param rf Response Formatter
     * @param storage Current storage
     * @param history List of previous commands
     * @throws IOException When there is file save error
     * @return Event created message formatted
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
        return rf.formatUndo(COMMAND_TYPE);
    }
}
