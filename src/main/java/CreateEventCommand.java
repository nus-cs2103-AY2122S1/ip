public class CreateEventCommand extends Command {
    private final String name;
    private final String date;

    public CreateEventCommand(String userInput) {
        this.name = userInput.split(" /at ", 2)[0].substring(6);
        this.date = userInput.split(" /at ", 2)[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsg(tasks.addToList(new Events(this.name, this.date)));
        storage.write(tasks.getSaveData());
    }
}
