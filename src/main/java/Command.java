public class Command {
    private String commandName;
    private Ui ui;

    public Command(String commandName) {
        this.commandName = commandName;
        ui = new Ui();
    }

    public void execute() {
        switch (commandName) {
            case "bye":
                ui.sayBye();
                break;
            default:
                ui.echo(commandName);
        }
    }

    public boolean shouldExit() {
        return commandName.equals("bye");
    }
}
