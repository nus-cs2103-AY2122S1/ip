package duke;

/**
 *Command to list the current Statistics .
 */
public class StatisticsCommand extends Command{


    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Statistics stats) throws DukeException {
        return ui.printStatistics(stats);
    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
