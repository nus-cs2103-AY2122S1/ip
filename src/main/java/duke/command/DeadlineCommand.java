package duke.command;

import java.time.LocalDateTime;

import duke.util.ToDoList;

/**
 * DeadlineCommand is a Command that encapsulates the behaviour of adding a Deadline to the ToDoList.
 *
 * @author leezhixuan
 */
public class DeadlineCommand extends Command {

    private ToDoList tdl;
    private String item;
    private LocalDateTime deadline;

    /**
     * Creates an instance of Deadline.
     *
     * @param tdl Instance of ToDoList in use.
     * @param item Name of Task.
     * @param deadline Date and Time that the Task is due.
     */
    public DeadlineCommand(ToDoList tdl, String item, LocalDateTime deadline) {
        this.tdl = tdl;
        this.item = item;
        this.deadline = deadline;
    }

    @Override
    public String execute() {
        return this.tdl.addDeadline(this.item, this.deadline);
    }
}
