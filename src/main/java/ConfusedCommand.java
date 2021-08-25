public class ConfusedCommand extends Command{
    private Ui ui;

    public ConfusedCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.prettyPrinter("I'm confused... I need a raise...");
    }
}
