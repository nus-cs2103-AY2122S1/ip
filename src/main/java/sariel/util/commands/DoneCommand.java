package sariel.util.commands;

import sariel.util.commons.Messages;
import sariel.util.tasks.Task;
import sariel.util.ui.Ui;


/**
 * Class representing the Done command.
 *
 */
public class DoneCommand implements Command {
    private final Task t;
    private final Ui ui;

    /**
     * The constructor of the DoneCommand
     *
     * @param t The task that will be done.
     * @param ui The ui to print the message.
     */
    public DoneCommand(Task t, Ui ui) {
        this.t = t;
        this.ui = ui;
    }




    @Override
    public String execute() {
        t.done();
        return String.format(Messages.TASK_COMPLETE, t.toString());

    }
}
