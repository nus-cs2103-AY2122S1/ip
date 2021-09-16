package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UndoDeleteTaskCommand extends Command {
    private String command;

    public UndoDeleteTaskCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the Command accordingly.
     *
     * @param storage Storage to store changes in text file.
     * @param tasks Tasks compiled in a TaskList.
     * @return A String array containing output.
     */
    public String[] execute(Storage storage, TaskList tasks) {
        Task deletedTask = Storage.getDeleted();
        tasks.add(deletedTask);
        char type = deletedTask.toString().charAt(1);
        switch (type) {
        case ('T'):
            try {
                storage.appendToFile("data/duke.txt", "T - 0 - " + deletedTask.getName());
            } catch (IOException e) {
                return Ui.display(e.getMessage());
            }
            Storage.deleteLastCommand();
            Storage.removeDeletedTask();
            return Ui.printTaskAdded(deletedTask, tasks.size());
        case ('E'):
            Event deleted = (Event) Storage.getDeleted();
            String date = deleted.getUnformattedDate();
            String name = deleted.getName();
            try {
                storage.appendToFile("data/duke.txt", "E - 0 - " + name
                        + "- " + date);
            } catch (IOException e) {
                return Ui.display(e.getMessage());
            }
            Storage.deleteLastCommand();
            Storage.removeDeletedTask();
            return Ui.printTaskAdded(deletedTask, tasks.size());
        case ('D'):
            Deadline deadline = (Deadline) Storage.getDeleted();
            String deadlineDate = deadline.getUnformattedDate();
            String deadlineName = deadline.getName();
            try {
                storage.appendToFile("data/duke.txt", "D - 0 - " + deadlineName + "- "
                        + deadlineDate);
            } catch (IOException e) {
                return Ui.display(e.getMessage());
            }
            Storage.deleteLastCommand();
            Storage.removeDeletedTask();
            return Ui.printTaskAdded(deletedTask, tasks.size());
        default:
            return Ui.display("Unknown error has occurred");
        }
    }
}
