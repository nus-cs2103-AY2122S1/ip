package duke.commands;

public class ByeCommand extends Command {
    @Override
    public void execute(int i) {
        System.out.println("Invoked ByeCommand");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
