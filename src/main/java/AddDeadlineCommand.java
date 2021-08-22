public class AddDeadlineCommand extends AddCommand {

    private String desc;
    private String by;

    public AddDeadlineCommand(String desc, String by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // tasks
        Deadline newDeadline = new Deadline(desc, by);
        tasks.add(newDeadline);
        // ui
        String response = respond(newDeadline, tasks.size());
        ui.showResponse(response);
        // storage
        storage.save(tasks);
    }
}
