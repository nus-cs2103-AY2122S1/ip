public class DoneCommand extends DukeCommand {
    private final int id;

    public DoneCommand(int id) {
        super();
        this.id = id;
    }

    @Override
    public String run(DukeList list) throws InvalidArgumentsException {
        try {
            Task task = list.get(id);
            task.markDone();
            return "Nice! I've marked this task as done:\n"
                    + "  "
                    + task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentsException();
        }
    }
}
