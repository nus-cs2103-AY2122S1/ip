public class EventCommand extends Command {

    private final Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(event);
        storage.appendToFile(event);
        ui.printWithLines("Got it. I've added this task:\n  " + event
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
