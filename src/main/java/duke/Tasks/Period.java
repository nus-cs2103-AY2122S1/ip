package duke.Tasks;

import duke.Tool.Storage;
import duke.Tool.TaskList;
import duke.Ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Period extends Task {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Period(String description, LocalDateTime startDate, LocalDateTime endDate, boolean isDone) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Marks down the deadline task
     *
     * @return Deadline task
     */
    @Override
    public Period markDone() {
        super.markDone();
        return this;
    }

    /**
     * Changes the format let "X" -> 1 and record to tasks.txt version
     *
     * @return String changed format of deadline task in record
     */
    @Override
    public String changeFormat() {
        String mark = isDone ? "1" : "0";
        return "P | " + mark + " | " + this.description + " | "
                + this.startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " | " +
                this.endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Executes input deadline task
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return String the details of deadline task
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int numOfBeforeTask = tasks.size();
        tasks.add(this);
        assert tasks.size() - numOfBeforeTask == 1 : "Add period task not successful";
        storage.writeData(tasks.getTasks());
        return ui.showAddOnTask(tasks, tasks.size() - 1);
    }


    /**
     * Overrides toString method to print period task details
     *
     * @return String of Period task details
     */
    public String toString() {
        return "[P]" + super.toString()
                + " from: " + this.startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }
}
