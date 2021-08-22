public class MarkDoneCommand implements Command{
    private String command;

    public MarkDoneCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String integer = command.replaceAll("\\D+", "");
        int index;

        try {
            index = Integer.parseInt(integer) - 1;
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! You have to specify the task.");
        }

        Task task = taskList.markAsDone(index);

        ui.respondDone(task);
        storage.save(taskList.getTasks());

    }
}
