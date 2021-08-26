package duke.command;

import duke.task.Event;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

public class EventCommand extends Command {
    public static final String COMMAND = "event";
    private  String desc;
    private LocalDateTime at;

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
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Event newEvent = new Event(this.desc, this.at);
        taskList.add(newEvent);
        storage.writeToFile(taskList);
        ui.printAdd(newEvent, taskList.getList().size());
    }
}
