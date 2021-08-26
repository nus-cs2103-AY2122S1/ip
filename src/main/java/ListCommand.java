public class ListCommand extends Command{

    @Override
    public boolean execute(Ui ui, Storage storage) {
        ui.print(storage.getList());
        return false;
    }
}
