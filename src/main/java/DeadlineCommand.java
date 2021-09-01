public class DeadlineCommand extends Command {

    private String input;
    private String taskDesc;
    private String deadline;

    public DeadlineCommand(String input) {
        this.input = input;
        this.taskDesc = input.replaceFirst("^deadline", "").split(" /")[0];
        if (input.contains("/by")) {
            this.deadline = input.substring(input.indexOf("/by") + 4);
        }
    }

    @Override
    public void execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        Deadline dTask = new Deadline(taskDesc, deadline);
        ls.addTask(dTask);
        storage.rewriteFile(ls);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
