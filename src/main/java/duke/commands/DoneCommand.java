package duke.commands;

public class DoneCommand extends Command {
    @Override
    public void execute(int i) {
        System.out.println("Invoked DoneCommand");
    }
}
