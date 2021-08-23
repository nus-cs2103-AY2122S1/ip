import java.time.LocalDate;

public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String listEntry() {
        return "[T]" + super.listEntry();
    }

    @Override
    public boolean isTodayTask(LocalDate l) {
        return false;
    }
}
