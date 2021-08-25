public class TodoCommand extends DukeCommand {
    private final ToDo task;

    public TodoCommand(ToDo task) {
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
