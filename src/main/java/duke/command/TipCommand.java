package duke.command;

public class TipCommand extends Command {
    private String tip;

    public TipCommand(String tip) {
        this.tip = tip;
    }

    @Override
    public String execute() {
        return this.tip;
    }
}
