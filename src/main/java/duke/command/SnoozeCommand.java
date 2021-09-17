package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class SnoozeCommand extends Command {
    public static final String COMMAND = "snooze";
    private static final String COMMAND_TYPE = "Snooze Command";

    private static final String ARRAYOUTOFBOUNDS_MESSAGE = "Eh... No such task found. Cannot mark as done.";
    private static final String ARRAYOUTOFBOUNDS_EMOTICON = "(＃-w-)";
    private static final String ILLEGALARGUMENT_MESSAGE ="Cannot snooze a task with no date!";
    private static final String ILLEGALARGUMENT_EMOTICON = ">.<";

    private int taskNo;
    private Task task;
    private LocalDateTime prevDate;
    private LocalDateTime currDate;

    /**
     * Constructor for Snooze Command
     *
     * @param taskNo Index of task completed
     * @param newDate Date to be updated
     *
     */
    public SnoozeCommand(int taskNo, LocalDateTime newDate) {
        this.taskNo = taskNo;
        this.currDate = newDate;
    }

    /**
     * Executes Snooze command that delays/changes dates of Event and Deadline tasks.
     *
     * @param taskList Current list
     * @param rf Response Formatter
     * @param storage Current storage
     * @param history List of previous commands
     * @throws IOException For commands that needs to write to storage file
     *
     * @return Snoozed task message formatted
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
            return rf.formatError(ARRAYOUTOFBOUNDS_MESSAGE, ARRAYOUTOFBOUNDS_EMOTICON);
        } catch (IllegalArgumentException e) {
            return rf.formatError(ILLEGALARGUMENT_MESSAGE, ILLEGALARGUMENT_EMOTICON);
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
        return rf.formatUndo(COMMAND_TYPE);
    };
}
