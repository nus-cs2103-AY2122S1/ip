public class DateCommand extends Command {

    private String date;

    public DateCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.printTasksOnDate(this.date, ui);
    }
}
