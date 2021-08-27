import java.time.LocalDate;

public class OccurringCommand extends Command {
    LocalDate queryDate;

    public OccurringCommand(LocalDate queryDate) {
        this.queryDate = queryDate;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showOccurringTasks(queryDate, tasks);
    }
}
