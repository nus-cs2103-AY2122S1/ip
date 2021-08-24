public class DeleteCommand extends Command{
    private String response;

    public DeleteCommand(String response) {
        this.response = response;
    }

    /**
     * Returns a boolean checking whether the user input is
     * related to delete operations.
     *
     * @return Whether the input is related to delete or not.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OutOfRangeException{
        //check with the special response "delete X", where X is index of deleted item.
        int curr = Integer.parseInt(response.substring(7));
        Task shouldDelete = tasks.removeElement(curr - 1);
        storage.replace(curr - 1, null);
        ui.showDelete(shouldDelete, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
