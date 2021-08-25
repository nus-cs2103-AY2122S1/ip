public class InvalidCommand extends DukeCommand{
    private final DukeException e;

    public InvalidCommand(DukeException e) {
        super();
        this.e = e;
    }

    @Override
    public String run(DukeList list) throws DukeException{
        throw e;
    }
}
