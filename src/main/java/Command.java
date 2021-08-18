public class Command {
    private String input;
    private Ui ui;

    public Command(String input) {
        this.input = input;
        ui = new Ui();
    }

    public void execute() {
        switch (input) {
            case "bye":
                ui.sayBye();
                break;
            default:
                ui.echo(input);
        }
    }

    public boolean shouldExit() {
        return input.equals("bye");
    }
}
