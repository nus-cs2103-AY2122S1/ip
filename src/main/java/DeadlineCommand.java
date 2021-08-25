import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private ToDoList tdl;
    private String item;
    private LocalDateTime deadline;

    public DeadlineCommand(ToDoList tdl, String item, LocalDateTime deadline) {
        this.tdl = tdl;
        this.item = item;
        this.deadline = deadline;
    }

    @Override
    public void execute() {

    }
}
