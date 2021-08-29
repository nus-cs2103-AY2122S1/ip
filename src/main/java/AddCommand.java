import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a task.
 * 
 * @author Gordon Yit
 * @Version CS2103T, Semester 2
 */
public class AddCommand extends Command{
    private String addCommand;
    private boolean isExitCommand;

    /**
     * Class constructor.
     * 
     * @param addCommand the user inputed string to add a task.
     */
    public AddCommand(String addCommand) {
        this.addCommand = addCommand;
        isExitCommand = false;
    }

    /**
     * Executes the command to add a task for Duke to keep track of.
     * 
     * @param tasks lists of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task;
            if (addCommand.contains("deadline")) {
                task = new Deadline(addCommand);
            } else if (addCommand.contains("event")) {
                task = new Event(addCommand);
            } else {
                task = new Todo(addCommand);
            }
            tasks.add(task);
            ui.showTaskAdded(task, tasks.getTasks());
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(e);
        } catch (DateTimeParseException e) {
            throw new DukeException(e);
        }
    }
    
}
