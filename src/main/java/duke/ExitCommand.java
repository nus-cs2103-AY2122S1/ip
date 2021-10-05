package duke;
import duke.Ui;

public class ExitCommand extends Command {
    /**
     * Command that tells the bot to exit.
     * @param tasks
     * @param u
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui u, Storage storage) {
        u.sayBye();
    }
}
