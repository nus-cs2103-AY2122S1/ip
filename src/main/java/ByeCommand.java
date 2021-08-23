public class ByeCommand extends Command {

    public ByeCommand(String arguments) {
        super("");
    }

    @Override
    public Command of(String arguments) {
        return new ByeCommand("");
    }

    @Override
    public void execute(TaskList tl, Storage s, UI ui) {

    }

    @Override
    public String startsWith() {
        return "bye";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
