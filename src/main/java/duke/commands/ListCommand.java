package duke.commands;

public class ListCommand extends Command {
    @Override
    public void execute(LinkedList<Item> itemList) {
        System.out.println("Invoked ListCommand");
    }
}
