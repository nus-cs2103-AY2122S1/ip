package duke.commands;

public class ByeCommand extends Command {
    @Override
    public void execute(LinkedList<Item> itemList) {
        System.out.println("Invoked ByeCommand");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
