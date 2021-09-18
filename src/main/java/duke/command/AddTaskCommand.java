package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import duke.Storage;
import duke.Ui;

import duke.exception.DukeException;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskList;

import javax.swing.border.BevelBorder;

/**
 * This class encapsulates a command to add a Task to a TaskList.
 * Extends Command class.
 */
public class AddTaskCommand extends Command {

    private Task task;

    /**
     * Constructor for a AddTaskCommand.
     * @param taskList TaskList from which to delete Task.
     * @param ui Ui involved in the command.
     * @param task Task to be added.
     */
    public AddTaskCommand(TaskList taskList, Storage storage, Ui ui, Task task) {
        super(taskList, storage, ui);
        this.task = task;
    }

    /**
     * Executes the Command.
     */
    @Override
    public void execute() {

        taskList.add(task);
        ui.setMessage("Sure thing. Added to list:\n" + task +
                "\nNumber of tasks in list: " + taskList.getSize());

        if (task instanceof EventTask) {
            @SuppressWarnings("unchecked")
            EventTask eTask = (EventTask) task;
            checkForClash(eTask);
        }

        saveData();
    }

    /**
     * Checks whether or not an EventTask clashes with another event in the task list.
     * Appends a warning to the UI message if there is a clash.
     * @param eventTask EventTask to be checked.
     */
    private void checkForClash(EventTask eventTask) {
        ArrayList<EventTask> clashingTasks = getClashes(eventTask);
        if (!clashingTasks.isEmpty()) {
            String events = "";
            for (EventTask e: clashingTasks) {
                events += e.toString() + "\n";
            }
            ui.appendMessage("\n\nTake note of clashing event(s):\n" + events);
        }
        
    }

    private ArrayList<EventTask> getClashes(EventTask eventTask) {
        ArrayList<EventTask> clashes = new ArrayList<>();
        for (int i = 1; i <= taskList.getSize(); i++) {
            Task t = taskList.get(i);
            if (t instanceof EventTask) {
                @SuppressWarnings("unchecked")
                EventTask other = (EventTask) t;
                if (!eventTask.equals(other) && eventTask.overlapsWith(other)) {
                    clashes.add(other);
                }
            } else {
                continue;
            }

        }

        return clashes;
    }

    private void saveData() {
        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.showError(new DukeException("Unable to Save\n" + e.getMessage()));
        }
    }

}
