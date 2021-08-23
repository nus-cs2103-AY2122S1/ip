public class ListCommand implements Command{
    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        String listString = "";
        if (t.getSize() == 0) {
            listString = "\n Your list is empty!";
        } else {
            for (int i = 0; i < t.getSize(); i++) {
                int count = i + 1;
                listString += "\n " + count + ". " + t.get(i);
            }
        }
        ui.textFrame(" This be ye list of things to do Sire:" + listString );
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
