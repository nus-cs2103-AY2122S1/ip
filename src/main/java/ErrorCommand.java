/**
 * Contains the executables when the user inputs an invalid command.
 */
public class ErrorCommand extends Command {
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }
}
