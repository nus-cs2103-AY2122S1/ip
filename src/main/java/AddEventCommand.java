import Tasks.Event;

public class AddEventCommand extends Command {

    private String[] keyWords;
    public AddEventCommand(String[] keywords) {
        this.keyWords = keywords;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(this.keyWords[0], this.keyWords[1]);
        tasks.addTask(event);
        String[] messages = new String[] {
                "Got it. I've added this task:",
                "    " + event.toString(),
                String.format("Now you have %d tasks in the list.", tasks.getSize())
        };
        ui.printOut(messages);
        storage.save(tasks);
    }
}
