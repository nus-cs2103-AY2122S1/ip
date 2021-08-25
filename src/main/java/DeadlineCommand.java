public class DeadlineCommand extends DukeCommand {
    private final Deadline task;

    public DeadlineCommand(Deadline task) {
        super();
        this.task = task;
    }

    @Override
    public String run(DukeList list) {
        list.add(task);
        return "Got it. I've added this task:\n"
                + "  "
                + task.toString() + "\n"
                + "Now you have " + list.size() + " tasks in the list";
    }
}
