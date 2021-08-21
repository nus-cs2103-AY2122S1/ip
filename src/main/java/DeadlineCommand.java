public class DeadlineCommand extends AddCommand {
    public DeadlineCommand(String rest) throws DukeException {
        super(new Deadline(rest.split(" /[a-z][a-z] ")));
    }
}
