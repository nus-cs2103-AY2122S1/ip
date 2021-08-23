import java.time.LocalDate;

class FindCommand extends Command {

    LocalDate date;
    TaskList tasks;
    Ui ui;

    protected FindCommand(LocalDate date, TaskList tasks, Ui ui) {
        this.date = date;
        this.ui = ui;
        this.tasks = tasks;
    }


    @Override
    protected void execute() {
        TaskList foundTasks = tasks.findByDate(this.date);
        ui.find(foundTasks);
    }
}
