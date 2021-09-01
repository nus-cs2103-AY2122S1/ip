package duke;

public class DeleteCommand implements Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (idx >= tasks.size()) {
            throw new DukeException("☹ OOPS!!! That task doesn't exist.");
        }
        System.out.println(Ui.format("Noted. I've removed this task: \n\t" + tasks.get(idx) +
                "\nNow you have " + ui.formatNumTasks(tasks.size() - 1) + " in the list."));
        tasks.remove(idx);
    }

    public boolean isExit() {
        return false;
    }
}
