public class TodoCommand extends AddCommand {
    public TodoCommand(String rest) throws DukeException {
        super(new Todo(rest));
    }
}
