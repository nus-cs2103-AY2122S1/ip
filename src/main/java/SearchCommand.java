public class SearchCommand extends Command{
    private String response;

    public SearchCommand(String response) {
        this.response = response;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String preTime = response.substring(5);
        String actualTime = Task.dateAndTime(preTime);
        TaskList currList = tasks.tasksWithDate(actualTime);
        ui.showList(currList, currList.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
