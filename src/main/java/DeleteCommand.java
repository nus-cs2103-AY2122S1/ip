public class DeleteCommand extends DukeCommand {
    private final int id;

    public DeleteCommand(int id) {
        super();
        this.id = id;
    }

    @Override
    public String run(DukeList list) throws InvalidArgumentsException {
        try {
            Task task = list.get(id);
            list.delete(id);
            return "Noted. I've removed this task:\n"
                    + "  "
                    + task.toString() + "\n"
                    + "Now you have " + list.size() + " tasks in the list";
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentsException();
        }
    }
}
