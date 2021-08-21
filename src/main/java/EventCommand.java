public class EventCommand extends AddCommand {
    public EventCommand(String rest) throws DukeException {
        super(new Event(rest.split(" /[a-z][a-z] ")));
    }
}
