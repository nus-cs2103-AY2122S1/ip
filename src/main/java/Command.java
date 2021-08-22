/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: Level 8. Dates and Times
 *
 * Description:
 * Extends the Task Class which where it is a task that needs
 * to be done before a specific time
 *
 * @author Keith Tan
 */
public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(Tasklist list, Ui ui, Store storage) throws DukeException;

}