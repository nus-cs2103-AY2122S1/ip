package duke.commands;

public class DoneCommand extends Command {


    @Override
    public void execute(LinkedList<Item> itemList) {
        System.out.println("Invoked DoneCommand");
    }
}
