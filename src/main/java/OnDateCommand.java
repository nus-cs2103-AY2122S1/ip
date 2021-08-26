import java.io.IOException;

public class OnDateCommand extends Command {
    public OnDateCommand(String input) {
        super(input);
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks occurring on this date:");
        ui.showIndentedMessage(tasks.getOnDateTasks(input).toString());
        return tasks;
    }
}
