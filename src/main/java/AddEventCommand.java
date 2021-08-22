public class AddEventCommand extends AddCommand {

    private String desc;
    private String at;

    public AddEventCommand(String desc, String at) {
        this.desc = desc;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // tasks
        Event newEvent = new Event(desc, at);
        tasks.add(newEvent);
        // ui
        String response = respond(newEvent, tasks.size());
        ui.showResponse(response);
        // storage
        storage.save(tasks);
    }
}
