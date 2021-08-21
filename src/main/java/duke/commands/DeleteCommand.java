package duke.commands;

public class DeleteCommand extends Command {
    private int index;

    @Override
    public void parseArg(String args) {
        this.index = 0;
    }

    @Override
    public void execute(LinkedList<Item> itemList) {
        System.out.println("Invoked DeleteCommand");
    }
}
