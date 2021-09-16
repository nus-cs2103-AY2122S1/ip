package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class SnoozeCommand extends Command {
    public static final String COMMAND = "snooze";
    private int taskNo;
    private Task task;
    private LocalDateTime prevDate;
    private LocalDateTime currDate;

    /**
     * Constructor for Snooze Command
     *
     * @param taskNo the index of task completed
     * @param newDate the date to be updated
     *
     */
    public SnoozeCommand(int taskNo, LocalDateTime newDate) {
        this.taskNo = taskNo;
        this.currDate = newDate;
    }

    /**
     * Executes commands
     *
     * @param taskList current list
     * @param ui current ui to access print responses
     * @param storage current storage
     * @throws IOException for commands that needs to write to storage file
     */
    public void execute(TaskList taskList, Ui ui, Storage storage, History history) throws IOException {
        try {
            if (taskNo == -1 || taskNo + 1 > taskList.getList().size()) {
                throw new ArrayIndexOutOfBoundsException();
            }

            this.task = taskList.getList().get(taskNo);
            boolean isEvent = this.task instanceof Event;
            boolean isDeadline = this.task instanceof Deadline;
            if (!isDeadline && !isEvent) {
                throw new IllegalArgumentException();
            }

            String display = "";
            if (isEvent) {
                display = ((Event) this.task).snooze(currDate);
            } else if (isDeadline) {
                display = ((Deadline) this.task).snooze(currDate);
            }
            storage.writeToFile(taskList);

            ui.printSnooze(display);
            history.addHistory(this);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printError(
                    "Eh... No such task found. Cannot mark as done.",
                    "(＃￣ω￣)"
            );
        } catch (IllegalArgumentException e) {
            ui.printError("Cannot snooze a task with no date!", ">.<");
        }
    };


    /**
     * Executes commands
     *
     * @param taskList current list
     * @param rf Response Formatter
     * @param storage current storage
     * @throws IOException for commands that needs to write to storage file
     */
    public String execute(TaskList taskList, ResponseFormatter rf,
                                   Storage storage, History history) throws IOException {
        try {
            if (taskNo == -1 || taskNo + 1 > taskList.getList().size()) {
                throw new ArrayIndexOutOfBoundsException();
            }

            this.task = taskList.getList().get(taskNo);
            boolean isEvent = this.task instanceof Event;
            boolean isDeadline = this.task instanceof Deadline;
            if (!isDeadline && !isEvent) {
                throw new IllegalArgumentException();
            }

            String display = "";
            if (isEvent) {
                this.prevDate = ((Event) this.task).getAt();
                display = ((Event) this.task).snooze(currDate);
            } else if (isDeadline) {
                this.prevDate = ((Deadline) this.task).getBy();
                display = ((Deadline) this.task).snooze(currDate);
            }
            storage.writeToFile(taskList);
            history.addHistory(this);

            return rf.formatSnooze(display);
        } catch (ArrayIndexOutOfBoundsException e) {
            return rf.formatError(
                    "Eh... No such task found. Cannot mark as done.",
                    "(＃￣ω￣)"
            );
        } catch (IllegalArgumentException e) {
            return rf.formatError("Cannot snooze a task with no date!", ">.<");
        }
    };

    @Override
    public String undo(TaskList taskList, ResponseFormatter rf, Storage storage) throws IOException {
        boolean isEvent = this.task instanceof Event;
        boolean isDeadline = this.task instanceof Deadline;

        if (isEvent) {
            Event event = (Event) this.task;
            event.snooze(prevDate);
        } else if (isDeadline) {
            Deadline deadline = (Deadline) this.task;
            deadline.snooze(prevDate);
        }

        storage.writeToFile(taskList);
        return rf.formatUndo("Snooze Command");
    };
}
