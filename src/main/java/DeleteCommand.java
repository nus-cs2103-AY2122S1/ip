public class DeleteCommand extends Command{
    int index;

    public DeleteCommand (int input) {
        this.index = input - 1;
    }

    @Override
    public boolean execute( Ui ui, Storage storage) {
        try {
            ui.print("Okay! I have deleted this task from your list: \n" + storage.getTask(index));
            storage.deleteFromList(index);
            storage.save();
        } catch (IndexOutOfBoundsException e){
            ui.print("Oops, the list is not that big!");
        }
        return false;
    }
}
